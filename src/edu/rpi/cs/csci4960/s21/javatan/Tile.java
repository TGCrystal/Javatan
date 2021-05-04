package edu.rpi.cs.csci4960.s21.javatan;

import java.io.Serializable;

enum TileType {HILLS, FOREST, MOUNTAINS, FIELDS, PASTURE, DESERT};

/**
* Stores which type it is using TileType. Stores the number associated with it.
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Tile implements Serializable {
    /**
    * The type of resource that this tile is
    */
    private final TileType type;
    /**
    * The number that needs to be rolled for this tile to yield resources
    */
    private final int num;

    /**
    * Creates a new tile with the given type and number
    *
    * @param type the type of tile this will be
    * @param num the number that must be rolled for this tile to produce a resource
    */
    public Tile(TileType type, int num) {
        this.type = type;
        this.num = num;
    }

    /**
    * Used to get the type of this tile
    *
    * @return the type of this tile
    */
    public TileType getType() {
        return this.type;
    }

    /**
    * Used to get the number of this tile
    *
    * @return the number associated with this tile
    */
    public int getNum() {
        return this.num;
    }
}
