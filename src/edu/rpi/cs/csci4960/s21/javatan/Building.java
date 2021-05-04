package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;

/**
* A class to represent a building (city/settlement)
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Building implements Serializable {
    /**
    * Represents the owner of this building, PlayerColor.NONE if not yet built
    */
    private PlayerColor ownership;
    /**
    * true for settlement, false for city
    */
    private boolean isSettlement;

    /**
    * The sole constructor for the Bulding class, initializes as no ownership and marks
    * it as a settlement.
    */
    public Building() {
        ownership = PlayerColor.NONE;
        isSettlement = true;
    }

    /**
    * Sets the owner of this building
    *
    * @param newOwner the new owner of this building
    */
    public void setOwner(PlayerColor newOwner) {
        ownership = newOwner;
    }

    /**
    * Gets the color of the owner of this building
    *
    * @return the color of the owner of this building
    */
    public PlayerColor getOwner() {
        return ownership;
    }

    /**
    * Used to upgrade the building from city to a settlement if the city has an owner
    *
    * @return false if the building is already a city or has no owner, true otherwise
    */
    public boolean upgrade() {
        if (ownership != PlayerColor.NONE && isSettlement) {
            isSettlement = false;
            return true;
        }
        return false;
    }

    /**
    * Used to check if this building is a settlement or a city
    *
    * @return true if the building is a settlement, false if it is a city
    */
    public boolean isSettlement() {
        return isSettlement;
    }
}
