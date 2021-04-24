package edu.rpi.cs.csci4960.s21.javatan;

import java.util.ArrayList;

/** Tile indices
           0,0    0,1    0,2
        1,0    1,1    1,2    1,3
    2,0    2,1    2,2     2,3   2,4
        3,0    3,1    3,2    3,3
           4,0    4,1    4,2
*/

/**
* Used to keep track of information about the board
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Board {
    private Tile[][] tiles;
    private Road[][] roads;
    private Building[][] buildings;
    private int robberRow;
    private int robberColumn;
    private final int numRows = 5;
    private final int numCols = 5;


    /**
    * Initializes the board, for now just default to main one found in manual
    */
    public Board() {
        tiles = new Tile[numRows][numCols];
        roads = new Road[numRows*2+2][numCols*2+2];
        buildings = new Building[numRows*2+2][numCols*2+2];

        // TODO: STILL NEED TO SET RESOURCE TYPES AND NUMBERS OF TILES FOR DEFAULT BOARD

        robberRow = -1;
        robberColumn = -1;
    }


    /**
    * Adds a road for the player to the given index. The indices works such that the progression
    * is left to right for columns, top to bottom for rows. Even rows indicate rows of
    * horizontal zig zag roads and odd rows indicated rows of vertical boards. Roads must be
    * adjacent to another road/building of the same color.
    *
    * @param roadRow the row index for where to place the road
    * @param roadColumn the column index for where to place the road
    * @param color the color of the player who is placing the road
    * @return true if the road was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addRoad(int roadRow, int roadColumn, PlayerColor color) throws
        IllegalArgumentException {
        if (!checkValidRoadPlacement(roadRow, roadColumn, color))
            return false;
        if (roads[roadRow][roadColumn].getOwner() != PlayerColor.NONE)
            return false;

        roads[roadRow][roadColumn].setOwner(color);;
        return true;
    }

    /**
    * Adds a building for the player to the given index. The indices works such that the
    * progression is left to right for columns, top to bottom for rows. The columns in a given
    * row follow the horizontal zig zag of the rows. Buildings must be adjacent to another
    * road of the same color. Buildings must be 
    *
    * @param buildingRow the row index for where to place the building
    * @param buildingColumn the column index for where to place the building
    * @param color the color of the player who is placing the building
    * @return true if the building was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addBuilding(int buildingRow, int buildingColumn, PlayerColor color) throws
        IllegalArgumentException {
        if (!checkValidBuildingPlacement(buildingRow, buildingColumn, color))
            return false;

        return addBuildingInit(buildingRow, buildingColumn, color);
    }

    /**
    * Adds a building for the player to the given index. The indices works such that the
    * progression is left to right for columns, top to bottom for rows. The columns in a given
    * row follow the horizontal zig zag of the rows. Buildings created by this function do NOT
    * have to be placed adjacent to an already placed road.
    *
    * @param buildingRow the row index for where to place the building
    * @param buildingColumn the column index for where to place the building
    * @param color the color of the player who is placing the building
    * @return true if the building was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addBuildingInit(int buildingRow, int buildingColumn, PlayerColor color) throws
        IllegalArgumentException {
        if (!buildings[buildingRow][buildingColumn].getOwner().equals(PlayerColor.NONE))
            return false;

        buildings[buildingRow][buildingColumn].setOwner(color);
        return true;
    }

    /**
    * Upgrades the building at the given index from a settlement to a city. If there isn't
    * a building placed there or the building has already been upgraded, false is returned.
    *
    * @param buildingRow the row index for where to upgrade the building
    * @param buildingColumn the column index for where to upgrade the building
    * @return true if the building was successfully upgraded, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean upgradeBuilding(int buildingRow, int buildingColumn) {
        if (buildings[buildingRow][buildingColumn].getOwner().equals(PlayerColor.NONE)
            || !buildings[buildingRow][buildingColumn].isSettlement())
            return false;
        buildings[buildingRow][buildingColumn].upgrade();
        return true;
    }

    /**
    * Processes a turn based on the given dice roll, gets list of cards and players to send them to
    *
    * @param rollValue the dice roll generated
    * @return a list resource cards and the corresponding players to give them to
    */
    public ArrayList<Tuple<ResourceCard, PlayerColor>> processTurn(int rollValue) {
        ArrayList<Tuple<ResourceCard, PlayerColor>> cardsToAward = new ArrayList<Tuple<ResourceCard, PlayerColor>>();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Tile tile = tiles[row][col];
                if (tile.getNum() == rollValue && tile.getType() != TileType.DESERT
                        && !(robberRow == row && robberColumn == col)) {
                    Building[] adjBuildings = getBuildingsAroundTile(row, col);
                    for (Building building : adjBuildings) {
                        cardsToAward.add(new Tuple<ResourceCard,PlayerColor>(
                            buildResourceCardFromTile(tile), building.getOwner()));
                    }
                }
            }
        }
        return cardsToAward;
    }

    /**
     * Returns the roads adjacent to the road at the given coordinates.
     * @param roadRow Row index of the road to get adjacent roads of 
     * @param roadCol Column index of the road to get adjacent roads of
     * @return
     */
    private Road[] getRoadsAdjacentToRoad(int roadRow, int roadCol) {
        Road[] adjacentRoads = new Road[4];
        if (roadRow % 2 == 0) {
            adjacentRoads[0] = roads[roadRow][roadCol-1];
            adjacentRoads[1] = roads[roadRow][roadCol+1];
            if (roadCol % 2 == 0) {
                adjacentRoads[2] = roads[roadRow-1][roadCol];
                adjacentRoads[3] = roads[roadRow+1][roadCol+1];
            } else {
                adjacentRoads[2] = roads[roadRow-1][roadCol+1];
                adjacentRoads[3] = roads[roadRow+1][roadCol];
            }
        } else {
            adjacentRoads[0] = roads[roadRow-1][roadCol-1];
            adjacentRoads[1] = roads[roadRow-1][roadCol];
            adjacentRoads[2] = roads[roadRow+1][roadCol];
            adjacentRoads[3] = roads[roadRow+1][roadCol-1];
        }
        return adjacentRoads;
    }

    /**
     * Returns the buildings adjacent to a given road
     * @param roadRow Row index of the road to get adjacent buildings of
     * @param roadCol Column index of the road to get adjacent buildings of 
     * @return
     */
    private Building[] getBuildingsAdjacentToRoad(int roadRow, int roadCol) {
        Building[] adjacentBuildings = new Building[2];
        if (roadRow % 2 == 0) {
            adjacentBuildings[0] = buildings[roadRow/2][roadCol];
            adjacentBuildings[1] = buildings[roadRow/2][roadCol+1];
        } else {
            // Odd row means vertical road
            int buildingRow = roadRow / 2;
            adjacentBuildings[0] = buildings[buildingRow][roadCol];
            adjacentBuildings[1] = buildings[buildingRow+1][roadCol];
        }
        return adjacentBuildings;
    }

    /**
     * Returns the roads adjacent to a given building
     * @param buildingRow Row index of the building to get adjacent roads of 
     * @param buildingCol Column index of the building to get adjacent roads of 
     * @return
     */
    private Road[] getRoadsAdjacentToBuilding(int buildingRow, int buildingCol) {
        Road[] adjacentRoads = new Road[3];

        adjacentRoads[0] = roads[buildingRow*2][buildingCol];
        adjacentRoads[1] = roads[buildingRow*2][buildingCol-1];
        if (buildingRow % 2 == buildingCol % 2) {
            adjacentRoads[3] = roads[buildingRow*2+1][buildingCol];
        } else {
            adjacentRoads[3] = roads[buildingRow*2-1][buildingCol];
        }
        return adjacentRoads;
    }

    /**
     * Returns the buildings around a given tile
     * @param tileRow Row index of the tile to adjacent buildings of
     * @param tileCol Column index of the tile to get adjacent buildings of 
     * @return
     */
    private Building[] getBuildingsAroundTile(int tileRow, int tileCol) {
        Building[] neighborBuildings = new Building[6];
        int offset = tileRow % 2 == 0 ? 0 : -1;
        
        neighborBuildings[0] = buildings[tileRow][2*tileCol+offset];
        neighborBuildings[1] = buildings[tileRow][2*tileCol+1+offset];
        neighborBuildings[2] = buildings[tileRow][2*tileCol+2+offset];
        neighborBuildings[3] = buildings[tileRow+1][2*tileCol+offset];
        neighborBuildings[4] = buildings[tileRow+1][2*tileCol+1+offset];
        neighborBuildings[5] = buildings[tileRow+1][2*tileCol+2+offset];

        return neighborBuildings;
    }

    /**
     * Checks to see whether a road is valid to place at the give coordinates. This includes 
     * checking adjacent buildings and roads to make sure there is already a structure of the 
     * same color
     * @param roadRow Row index of where the road will be placed 
     * @param roadCol Column index of where the road will be placed
     * @param color The player color to validate 
     * @return True if this placement is valid, false otherwise
     */
    private boolean checkValidRoadPlacement(int roadRow, int roadCol, PlayerColor color) {
        Building[] adjBuildings = getBuildingsAdjacentToRoad(roadRow, roadCol);
        Road[] adjRoads = getRoadsAdjacentToRoad(roadRow, roadCol);

        for (Building building : adjBuildings) {
            if (building != null && building.getOwner().equals(color)) 
                return true;
        }

        for (Road road : adjRoads) {
            if (road != null && road.getOwner().equals(color)) 
                return true;
        }

        return false;
    }

    /**
     * Checks if the given building placement is valid. Includes checking adjacent 
     * roads to make sure there is one of the same color.
     * @param buildingRow Row index of where the building will be placed
     * @param buildingCol Column index of where the building will be placed 
     * @param color The player color to validate
     * @return True if this placement is valid, false otherwise
     */
    private boolean checkValidBuildingPlacement(int buildingRow, int buildingCol, PlayerColor color) {
        Road[] adjRoads = getRoadsAdjacentToBuilding(buildingRow, buildingCol);

        for (Road road : adjRoads) {
            if (road.getOwner().equals(color))
                return true;
        }
        return false;
    }

    private ResourceCard buildResourceCardFromTile(Tile tile) {
        switch (tile.getType()) {
            case HILLS:
                return new ResourceCard(ResourceCardType.BRICK);
            case FOREST:
                return new ResourceCard(ResourceCardType.LUMBER);
            case MOUNTAINS:
                return new ResourceCard(ResourceCardType.ORE);
            case FIELDS:
                return new ResourceCard(ResourceCardType.GRAIN);
            case PASTURE:
                return new ResourceCard(ResourceCardType.WOOL);
            default:
                throw new IllegalArgumentException("Can't build a resource card for a default tile");
        }
    }
}