package edu.rpi.cs.csci4960.s21.javatan;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;

/**
* Used to keep track of and manage the current game
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Game {
    private final Board board;
    private PlayerColor longestRoad;
    private PlayerColor largestArmy;
    private final ArrayList<PlayerColor> players;
    private int currentPlayerIndex;

    /**
    * Constructor for the game, initializes the board, does not add any players
    */
    public Game() {
        board = new Board();
        players = new ArrayList<PlayerColor>();
        currentPlayerIndex = 0;
    }

    /**
    * Gets a reference to the internal Board representation
    *
    * @return the internal Board object used to represent the board
    */
    public Board getBoard()
    {
        return this.board;
    }

    /**
    * Adds a new player to the game, assigning them a color. 
    * Returns null if there are no more colors to assign
    *
    * @return the color of the newly added player
    */
    public PlayerColor addPlayer() {
        for (PlayerColor color : PlayerColor.values()) {
            if (!players.contains(color) && color != PlayerColor.NONE) {
                players.add(color);
                return color;
            }
        }
        return null;
    }

    private Boolean firstHouse = true;
    /**
    * Assigns a settlement to a player
    *
    * @param color the player's color to assign to the settlement
    * @param x the x position of the settlement
    * @param y the y position of the settlement
    * @return true if the building was successfully placed, false otherwise
    */
    public Boolean assignSettlement(PlayerColor color, int x, int y)
    {
        if (firstHouse)
        {
            firstHouse = false;
            return board.addBuildingInit(x, y, color);
        }
        else 
            return board.addBuilding(x, y, color);
    }

    /**
    * Gives the longest road card to the given player
    *
    * @param player the player to give the longest road to
    * @throws IllegalArgumentException if the game does not have the given player registered
    */
    public void giveLongestRoad(PlayerColor player) throws IllegalArgumentException {
        verifyPlayerExists(player);
        this.longestRoad = player;
    }

    /**
    * Gives the largest army card to the given player
    *
    * @param player the player to give the largestarmy to
    * @throws IllegalArgumentException if the game does not have the given player registered
    */
    public void giveLargestArmy(PlayerColor player) throws IllegalArgumentException {
        verifyPlayerExists(player);
        this.largestArmy = player;
    }

    /**
    * Get the current player
    *
    * @return the color indicating the player whose turn it currently is
    */
    public PlayerColor getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
    * Advances to the next players turn
    */
    public void nextTurn() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size())
            currentPlayerIndex = 0;
    }

    private void verifyPlayerExists(PlayerColor player) throws IllegalArgumentException {
        if (!players.contains(player))
            throw new IllegalArgumentException("There is no " + player + " registered in the game");
    }
}