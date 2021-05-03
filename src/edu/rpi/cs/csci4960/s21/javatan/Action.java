package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;


/** A class that help communication between Client and Server
 * package the movement made on Client so that it can be transferred to Server
 *
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

    private Tuple tuple;

    private DevelopmentCard developmentCard;

    private ResourceCard resourceCard;

    private int dice;

    private int row;

    private int col;

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public Tuple getTuple() {
        return tuple;
    }

    public void setTuple(Tuple tuple) {
        this.tuple = tuple;
    }

    public DevelopmentCard getDevelopmentCard() {
        return developmentCard;
    }

    public void setDevelopmentCard(DevelopmentCard developmentCard) {
        this.developmentCard = developmentCard;
    }

    public ResourceCard getResourceCard() {
        return resourceCard;
    }

    public void setResourceCard(ResourceCard resourceCard) {
        this.resourceCard = resourceCard;
    }

    public int getDice() {
        return dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
