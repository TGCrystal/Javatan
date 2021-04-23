package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class RoadTest {
    @Test
    public void basicConstructorTest() {
        Road road = new Road();
        assertEquals(PlayerColor.NONE, road.getOwner());
        assertEquals(PortType.NONE, road.getPort());
    }

    @Test 
    public void portTest() {
        Road none = new Road(PortType.NONE);
        assertEquals(PlayerColor.NONE, none.getOwner());
        assertEquals(PortType.NONE, none.getPort());
        Road brick = new Road(PortType.BRICK);
        assertEquals(PlayerColor.NONE, brick.getOwner());
        assertEquals(PortType.BRICK, brick.getPort());
        Road lumber = new Road(PortType.LUMBER);
        assertEquals(PlayerColor.NONE, lumber.getOwner());
        assertEquals(PortType.LUMBER, lumber.getPort());
        Road ore = new Road(PortType.ORE);
        assertEquals(PlayerColor.NONE, ore.getOwner());
        assertEquals(PortType.ORE, ore.getPort());
        Road grain = new Road(PortType.GRAIN);
        assertEquals(PlayerColor.NONE, grain.getOwner());
        assertEquals(PortType.GRAIN, grain.getPort());
        Road wool = new Road(PortType.WOOL);
        assertEquals(PlayerColor.NONE, wool.getOwner());
        assertEquals(PortType.WOOL, wool.getPort());
        Road three = new Road(PortType.THREE);
        assertEquals(PlayerColor.NONE, three.getOwner());
        assertEquals(PortType.THREE, three.getPort());
    }

    @Test 
    public void ownerTest() {
        Road road1 = new Road();
        assertEquals(PlayerColor.NONE, road1.getOwner());
        road1.setOwner(PlayerColor.ORANGE);
        assertEquals(PlayerColor.ORANGE, road1.getOwner());
        Road road2 = new Road(PortType.BRICK);
        assertEquals(PlayerColor.NONE, road2.getOwner());
        road2.setOwner(PlayerColor.RED);
        assertEquals(PlayerColor.RED, road2.getOwner());
    }
}