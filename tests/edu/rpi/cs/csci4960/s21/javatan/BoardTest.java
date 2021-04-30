package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.Test;

public class BoardTest {
    @Test
    public void addRoadErrorNoConnection() {
        Board board = new Board();
        assertFalse("Fail adding a road not connected to anything",
            board.addRoad(4, 5, PlayerColor.BLUE));
    }

    @Test
    public void addBuildingErrorNoConnection() {
        Board board = new Board();
        assertFalse("Fail adding a building not connected to anything",
            board.addBuilding(1, 5, PlayerColor.BLUE));
    }

    @Test
    public void addBuildingInitSuccessNoConnection() {
        Board board = new Board();
        assertTrue("Succeed adding a building w/ init not connected to anything",
            board.addBuildingInit(1, 5, PlayerColor.BLUE));
    }

    @Test
    public void addRoadSuccessful() {
        Board board = new Board();
        assertTrue("Add initial building", 
            board.addBuildingInit(2, 5, PlayerColor.RED));
        assertTrue("Add road next to initial building succeeds", 
            board.addRoad(3, 5, PlayerColor.RED));
        assertTrue("Successfully add another road next to old one",
            board.addRoad(2, 4, PlayerColor.RED));
    }

    @Test
    public void addRoadWrongColor() {
        Board board = new Board();
        assertTrue("Add initial building", 
            board.addBuildingInit(2, 5, PlayerColor.RED));
        assertFalse("Add wrong color building fails", 
            board.addRoad(3, 5, PlayerColor.BLUE));
    }

    @Test
    public void processTurnSuccess() {
        Board board = new Board();

        assertTrue("Add blue building",
            board.addBuildingInit(1, 3, PlayerColor.BLUE));
        assertTrue("Add second blue building",
            board.addBuildingInit(1, 5, PlayerColor.BLUE));

        assertTrue("Add red building",
            board.addBuildingInit(2, 4, PlayerColor.RED));
        assertTrue("Upgrade red building",
            board.upgradeBuilding(2, 4));

        ArrayList<Tuple<ResourceCard, PlayerColor>> resCards = board.processTurn(6);

        // All the cards should be bricks (on default board)
        for (Tuple<ResourceCard,PlayerColor> tuple : resCards) {
            assertEquals("All cards here should be Brick", ResourceCardType.BRICK, tuple.t.getType());
        }
        
        // We expect 4 cards. 2 blue and 2 red.
        assertEquals("Should be 4 cards", 4, resCards.size());

        // Sort red and blue into lists
        ArrayList<Tuple<ResourceCard, PlayerColor>> blueCardsList = new ArrayList<>(resCards);
        ArrayList<Tuple<ResourceCard, PlayerColor>> redCardsList = new ArrayList<>(resCards);
        blueCardsList.removeIf(tup -> !tup.k.equals(PlayerColor.BLUE));
        redCardsList.removeIf(tup -> !tup.k.equals(PlayerColor.RED));

        assertEquals("2 blue cards", 2, blueCardsList.size());
        assertEquals("2 red cards", 2, redCardsList.size());
    }
}