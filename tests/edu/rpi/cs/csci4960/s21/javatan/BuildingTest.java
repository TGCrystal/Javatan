package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class BuildingTest {
    @Test
    public void basicConstructorTest() {
        Building building = new Building();
        assertTrue(building.isSettlement());
        assertEquals(PlayerColor.NONE, building.getOwner());
    }

    @Test
    public void setOwnerTest() {
        Building building = new Building();
        building.setOwner(PlayerColor.ORANGE);
        assertEquals(PlayerColor.ORANGE, building.getOwner());
    }

    @Test
    public void upgradeTest() {
        Building building = new Building();
        assertFalse(building.upgrade());
        assertTrue(building.isSettlement());
        building.setOwner(PlayerColor.ORANGE);
        assertTrue(building.upgrade());
        assertFalse(building.isSettlement());
        assertFalse(building.upgrade());
        assertFalse(building.isSettlement());
    }
}