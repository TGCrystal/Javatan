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

    public DevelopmentCard(DevelopmentCardType type) {
        this.type = type;
    }

    public DevelopmentCardType getType() {
        return this.type;
    }
}