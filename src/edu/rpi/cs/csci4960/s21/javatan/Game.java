package edu.rpi.cs.csci4960.s21.javatan;

import java.lang.IllegalArgumentException;
import java.util.ArrayList;

/**
* Used to keep track of and manage the current game
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Game {
    // private final Board board;
    private PlayerColor longestRoad;
    private PlayerColor largestArmy;
    // private final ArrayList<PlayerColor> players;
    private int currentPlayerIndex;

    /**
    * Constructor for the game, initializes the board, does not add any players
    */
    public Game() {

    }

    /**
    * Adds a new player to the game, assigning them a color
    *
    * @return the color of the newly added player
    */
    // public PlayerColor addPlayer() {

    // }

    /**
    * Gives the longest road card to the given player
    *
    * @param player the player to give the longest road to
    * @throws IllegalArgumentException if the game does not have the given player registered
    */
    public void giveLongestRoad(PlayerColor player) throws IllegalArgumentException {

    }

    /**
    * Gives the largest army card to the given player
    *
    * @param player the player to give the largestarmy to
    * @throws IllegalArgumentException if the game does not have the given player registered
    */
    public void giveLargestArmy(PlayerColor player) throws IllegalArgumentException {

    }

    /**
    * Get the current player
    *
    * @return the color indicating the player whose turn it currently is
    */
    // public PlayerColor getCurrentPlayer() {

    // }

    /**
    * Advances to the next players turn
    */
    public void nextTurn() {

    }
}