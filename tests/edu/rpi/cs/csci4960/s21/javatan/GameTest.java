package edu.rpi.cs.csci4960.s21.javatan;

import java.util.ArrayList;
import java.lang.IllegalArgumentException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameTest {
    @Test
    public void addPlayerWorks()
    {
        Game game = new Game();
        // Should be able to add 4 players, RED ORANGE WHITE BLUE
        ArrayList<PlayerColor> playerColors = new ArrayList<>();
        playerColors.add(game.addPlayer());
        playerColors.add(game.addPlayer());
        playerColors.add(game.addPlayer());
        playerColors.add(game.addPlayer());

        assertEquals("Should be 4 players", 4, playerColors.size());
        assertTrue("Should be a red player", playerColors.contains(PlayerColor.RED));
        assertTrue("Should be a orange player", playerColors.contains(PlayerColor.ORANGE));
        assertTrue("Should be a white player", playerColors.contains(PlayerColor.WHITE));
        assertTrue("Should be a blue player", playerColors.contains(PlayerColor.BLUE));

        assertEquals("Adding any more player should return null", null, game.addPlayer());
    }

    @Test
    public void giveLongestRoadInvalidPlayerThrowsError() {
        Game game = new Game();
        assertThrows(IllegalArgumentException.class, () -> game.giveLongestRoad(PlayerColor.BLUE));
    }

    @Test
    public void giveLargestArmyInvalidPlayerThrowsError() {
        Game game = new Game();
        assertThrows(IllegalArgumentException.class, () -> game.giveLargestArmy(PlayerColor.BLUE));
    }

    @Test 
    public void getCurrentPlayerReturnsCurrentPlayer() {
        Game game = new Game();
        PlayerColor player1 = game.addPlayer();
        PlayerColor player2 = game.addPlayer();

        assertEquals("Current player is player 1", player1, game.getCurrentPlayer());
        game.nextTurn();
        assertEquals("Current player is now player 2", player2, game.getCurrentPlayer());
        game.nextTurn();
        assertEquals("Current player is player 1 again", player1, game.getCurrentPlayer());
    }
}