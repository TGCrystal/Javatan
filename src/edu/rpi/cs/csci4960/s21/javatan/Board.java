package edu.rpi.cs.csci4960.s21.javatan;

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


    /**
    * Initializes the board, for now just default to main one found in manual
    */
    public Board() {
        tiles = buildDefaultBoard();
        roads = new Road[11][10];

        robberRow = -1;
        robberColumn = -1;
    }

    /**
     * Sets up the tiles for the default board given in the manual
     */
    private Tile[][] buildDefaultBoard() {
        Tile[][] tiles = new Tile[4][4];

        // Row 0
        tiles[0][0] = new Tile(TileType.MOUNTAINS, 10);
        tiles[0][1] = new Tile(TileType.PASTURE, 2);
        tiles[0][2] = new Tile(TileType.FOREST, 9);

        // Row 1
        tiles[1][0] = new Tile(TileType.FIELDS, 12);
        tiles[1][1] = new Tile(TileType.HILLS, 6);
        tiles[1][2] = new Tile(TileType.PASTURE, 4);
        tiles[1][3] = new Tile(TileType.HILLS, 10);
        
        // Row 2
        tiles[2][0] = new Tile(TileType.FIELDS, 9);
        tiles[2][1] = new Tile(TileType.FOREST, 11);
        tiles[2][2] = new Tile(TileType.DESERT, -1);
        tiles[2][3] = new Tile(TileType.FOREST, 3);
        tiles[2][4] = new Tile(TileType.MOUNTAINS, 8);

        // Row 3
        tiles[3][0] = new Tile(TileType.FOREST, 8);
        tiles[3][1] = new Tile(TileType.MOUNTAINS, 3);
        tiles[3][2] = new Tile(TileType.FIELDS, 4);
        tiles[3][3] = new Tile(TileType.PASTURE, 5);

        // Row 4
        tiles[4][0] = new Tile(TileType.HILLS, 5);
        tiles[4][1] = new Tile(TileType.FIELDS, 6);
        tiles[4][2] = new Tile(TileType.PASTURE, 11);
        return tiles;
    }

    /**
    * Adds a road for the player to the given index. The indices works such that the progression
    * is left to right for columns, top to bottom for rows. Even rows indicate rows of
    * horizontal zig zag roads and odd rows indicated rows of vertical boards. Roads must be
    * adjacent to another road/building of the same color.
    *
    * @param row the row index for where to place the road
    * @param column the column index for where to place the road
    * @param color the color of the player who is placing the road
    * @return true if the road was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addRoad(int row, int column, PlayerColor color) throws
        IllegalArgumentException {
            
    }

    /**
    * Adds a building for the player to the given index. The indices works such that the
    * progression is left to right for columns, top to bottom for rows. The columns in a given
    * row follow the horizontal zig zag of the rows. Buildings must be adjacent to another
    * road of the same color. Buildings must be 
    *
    * @param row the row index for where to place the building
    * @param column the column index for where to place the building
    * @param color the color of the player who is placing the building
    * @return true if the building was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addBuilding(int row, int column, PlayerColor color) throws
        IllegalArgumentException {

    }

    /**
    * Adds a building for the player to the given index. The indices works such that the
    * progression is left to right for columns, top to bottom for rows. The columns in a given
    * row follow the horizontal zig zag of the rows. Buildings created by this function do NOT
    * have to be placed adjacent to an already placed road.
    *
    * @param row the row index for where to place the building
    * @param column the column index for where to place the building
    * @param color the color of the player who is placing the building
    * @return true if the building was successfully placed, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean addBuildingInit(int row, int column, PlayerColor color) throws
        IllegalArgumentException {

    }

    /**
    * Upgrades the building at the given index from a settlement to a city. If there isn't
    * a building placed there or the building has already been upgraded, false is returned.
    *
    * @param row the row index for where to upgrade the building
    * @param column the column index for where to upgrade the building
    * @return true if the building was successfully upgraded, false otherwise
    *   (assuming no errors are thrown)
    * @throws IllegalArgumentException row or column are outside of the range of valid indices
    */
    public boolean upgradeBuilding(int row, int column) {

    }

    /**
    * Processes a turn based on the given dice roll, gets list of cards and players to send them to
    *
    * @param rollValue the dice roll generated
    * @return a list resource cards and the corresponding players to give them to
    */
    public ArrayList<Tuple<ResourceCard, PlayerColor>> processTurn(int rollValue) {

    }
}