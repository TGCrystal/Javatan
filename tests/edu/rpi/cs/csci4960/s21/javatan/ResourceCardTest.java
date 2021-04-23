package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ResourceCardTest {
    @Test
    public void basicTest() {
        ResourceCard brick = new ResourceCard(ResourceCardType.BRICK);
        assertEquals(ResourceCardType.BRICK, brick.getType());
        ResourceCard lumber = new ResourceCard(ResourceCardType.LUMBER);
        assertEquals(ResourceCardType.LUMBER, lumber.getType());
        ResourceCard ore = new ResourceCard(ResourceCardType.ORE);
        assertEquals(ResourceCardType.ORE, ore.getType());
        ResourceCard grain = new ResourceCard(ResourceCardType.GRAIN);
        assertEquals(ResourceCardType.GRAIN, grain.getType());
        ResourceCard wool = new ResourceCard(ResourceCardType.WOOL);
        assertEquals(ResourceCardType.WOOL, wool.getType());
    }
}