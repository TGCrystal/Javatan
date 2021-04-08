package edu.rpi.cs.csci4960.s21.javatan;

/**
* A class to represent a building (city/settlement)
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Building {
    private PlayerColor ownership;
    // True for settlement, false for city
    private boolean isSettlement;

    /**
    * The sole constructor for the Bulding class, initializes as no ownership and marks
    * it as a settlement.
    */
    public Building() {

    }

    /**
    * Sets the owner of this building
    *
    * @param newOwner the new owner of this building
    */
    public void setOwner(PlayerColor newOwner) {

    }

    /**
    * Gets the color of the owner of this building
    *
    * @return the color of the owner of this building
    */
    public PlayerColor getOwner() {

    }

    /**
    * Used to upgrade the building from city to a settlement
    *
    * @return false if the building is already a city or has no owner, true otherwise
    */
    public boolean upgrade() {

    }

    /**
    * Used to check if this building is a settlement or a city
    *
    * @return true if the building is a settlement, false if it is a city
    */
    public boolean isSettlement() {

    }
}