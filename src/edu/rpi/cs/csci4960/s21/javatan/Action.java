package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;


/** 
 * A class that help communication between Client and Server
 * packages the movement made on Client so that it can be transferred to Server
 *
 * @author Chuanfeng Xiong
 * @author Chris Lamberston
 * @author Ruben McWilliams
 * @author Trevor Crystal
 */
public class Action implements Serializable {

    /**
     *    1 for Building
     *    2 for Upgrade
     *    3 for Dice
     *    4 for AddRoad
     *    5 for exchange card with Bank
     *    6 for using development card
     *    8 for using Knight card
     *    0 for End my turn
     */
    private String actionCode;

    /**
    * A tuple of data to include in this action
    */
    private Tuple tuple;

    /**
    * A development card to include in this action
    */
    private DevelopmentCard developmentCard;

    /**
    * A resource card to include in this action
    */
    private ResourceCard resourceCard;

    /**
    * A dice roll sum to include in this action
    */
    private int dice;

    /**
    * An affected row to include in this action
    */
    private int row;

    /**
    * An affected col to include in this action
    */
    private int col;

    /**
    * Gets the action code associated with this Action
    *
    * @return the action code associated with this action
    */
    public String getActionCode() {
        return actionCode;
    }

    /**
    * Sets this Actions action code
    *
    * @param actionCode the action code to assign to this Action
    */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    /**
    * Gets the tuple of data for this Action
    *
    * @return the Tuple associated with this action
    */
    public Tuple getTuple() {
        return tuple;
    }

    /**
    * Sets the tuple of data for this Action
    *
    * @param tuple the Tuple to associate with this action
    */
    public void setTuple(Tuple tuple) {
        this.tuple = tuple;
    }

    /**
    * Gets the development card for this Action
    *
    * @return the development card associated with this action
    */
    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    /**
    * Sets the development card for this Action
    *
    * @param developmentCard the development card to associate with this action
    */
    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    /**
    * Gets the resource card for this Action
    *
    * @return the resource card associated with this action
    */
    public ResourceCard getResourceCard() {
        return resourceCard;
    }

    /**
    * Sets the resource card for this Action
    *
    * @param resourceCard the resource card to associate with this action
    */
    public void setResourceCard(ResourceCard resourceCard) {
        this.resourceCard = resourceCard;
    }

    /**
    * Gets the dice roll sum for this Action
    *
    * @return the dice roll sum associated with this action
    */
    public int getDice() {
        return dice;
    }

    /**
    * Sets the dice roll sum for this Action
    *
    * @param dice the dice roll sum to associate with this action
    */
    public void setDice(int dice) {
        this.dice = dice;
    }

    /**
    * Gets the row for this Action
    *
    * @return the row associated with this action
    */
    public int getRow() {
        return row;
    }

    /**
    * Sets the row for this Action
    *
    * @param row the row to associate with this action
    */
    public void setRow(int row) {
        this.row = row;
    }

    /**
    * Gets the column for this Action
    *
    * @return the column associated with this action
    */
    public int getCol() {
        return col;
    }

    /**
    * Sets the column for this Action
    *
    * @param col the column to associate with this action
    */
    public void setCol(int col) {
        this.col = col;
    }
}
