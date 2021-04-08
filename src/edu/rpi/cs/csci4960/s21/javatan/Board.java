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