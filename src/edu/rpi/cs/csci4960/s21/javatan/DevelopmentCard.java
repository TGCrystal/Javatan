package edu.rpi.cs.csci4960.s21.javatan;

enum DevelopmentCardType {KNIGHT, PROGRESS, VICTORY};

/**
* A development card, stores which type it is using DevelopmentCardType
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class DevelopmentCard extends Card {
    private final DevelopmentCardType type;

    /**
    * Sole constructor for the DevelopmentCard class
    *
    * @param type the type of development card that this object will be
    */
    public DevelopmentCard(DevelopmentCardType type) {
        this.type = type;
    }

    /**
    * Gets this development card's type
    *
    * @return the type of development card this development card is
    */
    public DevelopmentCardType getType() {
        return this.type;
    }
}