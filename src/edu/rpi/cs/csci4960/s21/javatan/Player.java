package edu.rpi.cs.csci4960.s21.javatan;

import java.util.ArrayList;

/**
* This class is used to keep track of the player's cards, color, and victory points
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Player {
    private final ArrayList<ResourceCard> resourceCards;
    private final ArrayList<DevelopmentCard> developmentCards;
    private boolean hasLongestRoad;
    private boolean hasLargestArmy;
    private int victoryPoints;
    private final PlayerColor color;

    /**
    * The sole constructor for the player class, takes in the color to assign to this player,
    * initializes victoryPoints to 0
    *
    * @param color this player's color
    */
    public Player(PlayerColor color) {
        this.color = color;
        this.hasLargestArmy = false;
        this.hasLongestRoad = false;
        this.victoryPoints = 0;
        this.resourceCards = new ArrayList<ResourceCard>();
        this.developmentCards = new ArrayList<DevelopmentCard>();
    }

    /**
    * Adds a given progress card to the player's hand
    *
    * @param card the progress card to add
    */
    public void addResourceCard(ResourceCard card) {
        //Chuanfeng Xiong
        this.resourceCards.add(card);
    }

    /**
    * Adds a given development card to the player's hand
    *
    * @param card the development card to add
    */
    public void addDevelopmentCard(DevelopmentCard card) {
        //Chuanfeng Xiong
        this.developmentCards.add(card);
    }

    /**
    * Gives this player the longest road card, adding +2 to the player's victory points.
    * If the player already has the card, then nothing happens.
    */
    public void addLongestRoad() {

    }

    /**
    * Gives this player the largest army card, adding +2 to the player's victory points.
    * If the player already has the card, then nothing happens.
    */
    public void addLargestArmy() {

    }

    /**
    * Adds victory points to this player's victory points total
    *
    * @param points the number of points to add
    */
    public void addVictoryPoints(int points) {

    }

    /**
    * Removes a given resource card from the player's hand, returns to indicate if the removal
    * was successful
    *
    * @return true if the card was successfully removed, false otherwise
    */
    public boolean removeResourceCard(ResourceCard card) {

    }

    /**
    * Removes a given development card from the player's hand, returns to indicate if the removal
    * was successful
    *
    * @return true if the card was successfully removed, false otherwise
    */
    public boolean removeDevelopmentCard(DevelopmentCard card) {

    }

    /**
    * Removes a random resource card from the player's hand, returns the card that was removed
    *
    * @return the card that was removed, null if the deck was empty before this function was called
    */
    public ResourceCard removeRandomCard() {

    }

    /**
    * Removes the longest road card from this player's hand, removing 2 victory points.
    * If the player does not have the card, then nothing happens.
    */
    public void removeLongestRoad() {

    }

    /**
    * Removes the largest army card from this player's hand, removing 2 victory points.
    * If the player does not have the card, then nothing happens.
    */
    public void removeLargestArmy() {

    }

    /**
    * Gets the number of victory points this player has accumulated
    *
    * @return the number of victory points
    */
    public int getVictoryPoints() {

    }

    /**
    * Gets the number of resource cards this player has
    *
    * @return the number of resource cards
    */
    public int getNumResourceCards() {

    }

    /**
    * Gets the number of development cards this player has
    *
    * @return the number of development cards
    */
    public int getNumDevelopmentCards() {

    }

    /**
    * Gets the color of this player
    *
    * @return the color of this player
    */
    public PlayerColor getPlayerColor() {

    }

}
