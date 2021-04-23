package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TileTest {
    @Test
    public void basicTest() {
        Tile hills = new Tile(TileType.HILLS, 2);
        assertEquals(TileType.HILLS, hills.getType());
        assertEquals(2, hills.getNum());
        Tile forest = new Tile(TileType.FOREST, 3);
        assertEquals(TileType.FOREST, forest.getType());
        assertEquals(3, forest.getNum());
        Tile mountains = new Tile(TileType.MOUNTAINS, 4);
        assertEquals(TileType.MOUNTAINS, mountains.getType());
        assertEquals(4, mountains.getNum());
        Tile fields = new Tile(TileType.FIELDS, 5);
        assertEquals(TileType.FIELDS, fields.getType());
        assertEquals(5, fields.getNum());
        Tile pasture = new Tile(TileType.PASTURE, 6);
        assertEquals(TileType.PASTURE, pasture.getType());
        assertEquals(6, pasture.getNum());
        Tile desert = new Tile(TileType.DESERT, 8);
        assertEquals(TileType.DESERT, desert.getType());
        assertEquals(8, desert.getNum());
    }
}