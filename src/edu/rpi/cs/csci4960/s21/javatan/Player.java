package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
* This class is used to keep track of the player's cards, color, and victory points
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Player implements Serializable {
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
    * @param cardType the type of progress card to add
    */
    public void addResourceCard(ResourceCardType cardType) {
        //Chuanfeng Xiong
        this.resourceCards.add(new ResourceCard(cardType));
    }

    /**
    * Adds a given development card to the player's hand
    *
    * @param cardType the type of development card to add
    */
    public void addDevelopmentCard(DevelopmentCardType cardType) {
        //Chuanfeng Xiong
        this.developmentCards.add(new DevelopmentCard(cardType));
    }

    /**
    * Gives this player the longest road card, adding +2 to the player's victory points.
    * If the player already has the card, then nothing happens.
    */
    public void addLongestRoad() {
        if (!hasLongestRoad) {
            hasLongestRoad = true;
            victoryPoints += 2;
        }
    }

    /**
    * Gives this player the largest army card, adding +2 to the player's victory points.
    * If the player already has the card, then nothing happens.
    */
    public void addLargestArmy() {
        if (!hasLargestArmy) {
            hasLargestArmy = true;
            victoryPoints += 2;
        }
    }

    /**
    * Adds victory points to this player's victory points total
    *
    * @param points the number of points to add
    */
    public void addVictoryPoints(int points) {
        victoryPoints += points;
    }

    /**
     * Checks to see if a player can afford a road, and optionally removes them.
     * @param actuallyRemoveResources Whether to actually remove resources or not
     * @return Returns true if the player can afford a road, and false if not.
     */
    public Boolean tryBuyRoad(boolean actuallyRemoveResources) {
        // Player needs a brick and a lumber
        return tryRemoveResources(actuallyRemoveResources, ResourceCardType.BRICK, ResourceCardType.LUMBER);
    }

    /**
     * Checks to see if a player can afford a settlement, and optionally removes them. 
     * @param actuallyRemoveResources Whether to actually remove resources or not
     * @return Returns true if player can afford a settlement, and false if not.
     */
    public boolean tryBuySettlement(boolean actuallyRemoveResources) {
        return tryRemoveResources(actuallyRemoveResources, ResourceCardType.BRICK, ResourceCardType.LUMBER, 
            ResourceCardType.WOOL, ResourceCardType.GRAIN);
    }

    /**
     * Checks to see if a player can afford a city, and optionally removes them. 
     * @param actuallyRemoveResources Whether to actually remove resources or not
     * @return Returns true if player can afford a city, and false if not.
     */
    public boolean tryBuyCity(boolean actuallyRemoveResources) {
        return tryRemoveResources(actuallyRemoveResources, ResourceCardType.ORE, ResourceCardType.ORE, ResourceCardType.ORE,
            ResourceCardType.GRAIN, ResourceCardType.GRAIN);
    }

    /**
    * Removes a given resource card from the player's hand, returns to indicate if the removal
    * was successful
    *
    * @param cardType the type of resource card to remove
    * @return true if the card was successfully removed, false otherwise
    */
    public boolean removeResourceCard(ResourceCardType cardType) {
        for (int i = 0; i < resourceCards.size(); i++) {
            if (resourceCards.get(i).getType() == cardType) {
                resourceCards.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
    * Removes a given development card from the player's hand, returns to indicate if the removal
    * was successful
    *
    * @param cardType the type of development card to remove
    * @return true if the card was successfully removed, false otherwise
    */
    public boolean removeDevelopmentCard(DevelopmentCardType cardType) {
        for (int i = 0; i < developmentCards.size(); i++) {
            if (developmentCards.get(i).getType() == cardType) {
                developmentCards.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
    * Removes a random resource card from the player's hand, returns the card that was removed
    *
    * @return the card that was removed, null if the deck was empty before this function was called
    */
    public ResourceCard removeRandomCard() {
        if (resourceCards.size() == 0)
            return null;
        Random rand = new Random();
        int i = rand.nextInt(resourceCards.size());
        return resourceCards.remove(i);
    }

    /**
    * Removes the longest road card from this player's hand, removing 2 victory points.
    * If the player does not have the card, then nothing happens.
    */
    public void removeLongestRoad() {
        if (hasLongestRoad) {
            hasLongestRoad = false;
            victoryPoints -= 2;
        }
    }

    /**
    * Removes the largest army card from this player's hand, removing 2 victory points.
    * If the player does not have the card, then nothing happens.
    */
    public void removeLargestArmy() {
        if (hasLargestArmy) {
            hasLargestArmy = false;
            victoryPoints -= 2;
        }
    }

    /**
    * Checks if the player has the longest road card
    *
    * @return true if this player has the longest road card
    */
    public boolean hasLongestRoad() {
        return this.hasLongestRoad;
    }

    /**
    * Checks if the player has the largest army card
    *
    * @return true if this player has the largest army card
    */
    public boolean hasLargestArmy() {
        return this.hasLargestArmy;
    }

    /**
    * Gets the number of victory points this player has accumulated
    *
    * @return the number of victory points
    */
    public int getVictoryPoints() {
        return this.victoryPoints;
    }

    /**
    * Gets the number of resource cards this player has
    *
    * @return the number of resource cards
    */
    public int getNumResourceCards() {
        return this.resourceCards.size();
    }

    /**
    * Gets the number of development cards this player has
    *
    * @return the number of development cards
    */
    public int getNumDevelopmentCards() {
        return this.developmentCards.size();
    }

    /**
    * Gets the color of this player
    *
    * @return the color of this player
    */
    public PlayerColor getPlayerColor() {
        return this.color;
    }

    /**
     * Removes the given card types from the Player if they have them.
     * Returns true if player has cards and they are removed, and false otherwise.
     * @param cardTypesToRemove Card types to remove from player's hand
     * @return True if successfully removed, false otherwise
     */
    private Boolean tryRemoveResources(Boolean actuallyRemoveResources, ResourceCardType... cardTypesToRemove) {
        ArrayList<ResourceCardType> typesPlayerHas = new ArrayList<>();
        for (ResourceCard card : resourceCards) {
            typesPlayerHas.add(card.getType());
        }

        for (ResourceCardType cardType : cardTypesToRemove) {
            if (!typesPlayerHas.remove(cardType)) {
                return false;
            }
        }

        if (actuallyRemoveResources) {
            for (ResourceCardType cardType : cardTypesToRemove) {
                removeResourceCard(cardType);
            }
        }

        return true;
    }

}
