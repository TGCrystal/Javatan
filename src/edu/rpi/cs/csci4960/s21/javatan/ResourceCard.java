package edu.rpi.cs.csci4960.s21.javatan;

enum ResourceCardType {BRICK, LUMBER, ORE, GRAIN, WOOL};

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

    /**
    * Sole constructor for the ResourceCard class
    *
    * @param type the type of resource card that this object will be
    */
    public ResourceCard(ResourceCardType type) {
        this.type = type;
    }

    /**
    * Gets this resource card's type
    *
    * @return the type of resource card this resource card is
    */
    public ResourceCardType getType() {
        return this.type;
    }
}