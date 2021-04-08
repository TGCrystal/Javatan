package edu.rpi.cs.csci4960.s21.javatan;

enum ResourceCardType = {BRICK, LUMBER, ORE, GRAIN, WOOL};

/**
* A resource card, stores which type it is using ResourceCardType
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class ResourceCard extends Card {
    private final ResourceCardType type;

    public ResourceCard(ResourceCardType type) {
        this.type = type;
    }

    public ResourceCardType getType() {
        return this.type;
    }
}