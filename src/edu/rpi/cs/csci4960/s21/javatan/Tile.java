package edu.rpi.cs.csci4960.s21.javatan;

enum TileType {HILLS, FOREST, MOUNTAINS, FIELDS, PASTURE, DESERT};

/**
* Stores which type it is using TileType. Stores the number associated with it.
*
* @author Chuanfeng Xiong
* @author Chris Lamberston
* @author Ruben McWilliams
* @author Trevor Crystal
*/
public class Tile {
    private final TileType type;
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