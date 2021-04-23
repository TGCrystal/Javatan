package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class PlayerTest {
    @Test
    public void colorTest() {
        Player playerRed = new Player(PlayerColor.RED);
        Player playerOrange = new Player(PlayerColor.ORANGE);
        Player playerWhite = new Player(PlayerColor.WHITE);
        Player playerBlue = new Player(PlayerColor.BLUE);
        assertEquals(PlayerColor.RED, playerRed.getPlayerColor());
        assertEquals(PlayerColor.ORANGE, playerOrange.getPlayerColor());
        assertEquals(PlayerColor.WHITE, playerWhite.getPlayerColor());
        assertEquals(PlayerColor.BLUE, playerBlue.getPlayerColor());
    }

    @Test
    public void resourceCardTest() {
        Player player = new Player(PlayerColor.RED);
        assertEquals(0, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.BRICK);
        assertEquals(1, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.LUMBER);
        assertEquals(2, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.ORE);
        assertEquals(3, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.GRAIN);
        assertEquals(4, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.WOOL);
        assertEquals(5, player.getNumResourceCards());
        player.addResourceCard(ResourceCardType.BRICK);
        assertEquals(6, player.getNumResourceCards());

        assertTrue(player.removeResourceCard(ResourceCardType.BRICK));
        assertEquals(5, player.getNumResourceCards());
        assertTrue(player.removeResourceCard(ResourceCardType.WOOL));
        assertEquals(4, player.getNumResourceCards());
        assertTrue(player.removeResourceCard(ResourceCardType.ORE));
        assertEquals(3, player.getNumResourceCards());
        assertTrue(player.removeResourceCard(ResourceCardType.GRAIN));
        assertEquals(2, player.getNumResourceCards());
        assertTrue(player.removeResourceCard(ResourceCardType.LUMBER));
        assertEquals(1, player.getNumResourceCards());
        assertTrue(player.removeResourceCard(ResourceCardType.BRICK));
        assertEquals(0, player.getNumResourceCards());
        assertFalse(player.removeResourceCard(ResourceCardType.WOOL));
        assertEquals(0, player.getNumResourceCards());
        assertFalse(player.removeResourceCard(ResourceCardType.ORE));
        assertEquals(0, player.getNumResourceCards());
        assertFalse(player.removeResourceCard(ResourceCardType.GRAIN));
        assertEquals(0, player.getNumResourceCards());
        assertFalse(player.removeResourceCard(ResourceCardType.LUMBER));
        assertEquals(0, player.getNumResourceCards());
        assertFalse(player.removeResourceCard(ResourceCardType.BRICK));
        assertEquals(0, player.getNumResourceCards());
    }

    @Test
    public void developmentCardTest() {
        Player player = new Player(PlayerColor.RED);
        assertEquals(0, player.getNumDevelopmentCards());
        player.addDevelopmentCard(DevelopmentCardType.KNIGHT);
        assertEquals(1, player.getNumDevelopmentCards());
        player.addDevelopmentCard(DevelopmentCardType.PROGRESS);
        assertEquals(2, player.getNumDevelopmentCards());
        player.addDevelopmentCard(DevelopmentCardType.VICTORY);
        assertEquals(3, player.getNumDevelopmentCards());
        player.addDevelopmentCard(DevelopmentCardType.VICTORY);
        assertEquals(4, player.getNumDevelopmentCards());

        assertTrue(player.removeDevelopmentCard(DevelopmentCardType.VICTORY));
        assertEquals(3, player.getNumDevelopmentCards());
        assertTrue(player.removeDevelopmentCard(DevelopmentCardType.PROGRESS));
        assertEquals(2, player.getNumDevelopmentCards());
        assertFalse(player.removeDevelopmentCard(DevelopmentCardType.PROGRESS));
        assertEquals(2, player.getNumDevelopmentCards());
        assertTrue(player.removeDevelopmentCard(DevelopmentCardType.VICTORY));
        assertEquals(1, player.getNumDevelopmentCards());
        assertTrue(player.removeDevelopmentCard(DevelopmentCardType.KNIGHT));
        assertEquals(0, player.getNumDevelopmentCards());
        assertFalse(player.removeDevelopmentCard(DevelopmentCardType.PROGRESS));
        assertEquals(0, player.getNumDevelopmentCards());
        assertFalse(player.removeDevelopmentCard(DevelopmentCardType.KNIGHT));
        assertEquals(0, player.getNumDevelopmentCards());
        assertFalse(player.removeDevelopmentCard(DevelopmentCardType.VICTORY));
        assertEquals(0, player.getNumDevelopmentCards());
    }

    @Test
    public void basicVictoryPointsTest() {
        Player player = new Player(PlayerColor.RED);
        assertEquals(0, player.getVictoryPoints());
        player.addVictoryPoints(2);
        assertEquals(2, player.getVictoryPoints());
        player.addVictoryPoints(1);
        assertEquals(3, player.getVictoryPoints());
    }

    @Test
    public void specialCardsTest() {
        Player player = new Player(PlayerColor.RED);
        assertEquals(0, player.getVictoryPoints());
        assertFalse(player.hasLongestRoad());
        assertFalse(player.hasLargestArmy());
        player.addLongestRoad();
        assertEquals(2, player.getVictoryPoints());
        assertTrue(player.hasLongestRoad());
        assertFalse(player.hasLargestArmy());
        player.addLongestRoad();
        assertEquals(2, player.getVictoryPoints());
        assertTrue(player.hasLongestRoad());
        assertFalse(player.hasLargestArmy());
        player.addLargestArmy();
        assertEquals(4, player.getVictoryPoints());
        assertTrue(player.hasLongestRoad());
        assertTrue(player.hasLargestArmy());
        player.addLargestArmy();
        assertEquals(4, player.getVictoryPoints());
        assertTrue(player.hasLongestRoad());
        assertTrue(player.hasLargestArmy());

        player.removeLongestRoad();
        assertEquals(2, player.getVictoryPoints());
        assertFalse(player.hasLongestRoad());
        assertTrue(player.hasLargestArmy());
        player.removeLongestRoad();
        assertEquals(2, player.getVictoryPoints());
        assertFalse(player.hasLongestRoad());
        assertTrue(player.hasLargestArmy());
        player.removeLargestArmy();
        assertEquals(0, player.getVictoryPoints());
        assertFalse(player.hasLongestRoad());
        assertFalse(player.hasLargestArmy());
        player.removeLargestArmy();
        assertEquals(0, player.getVictoryPoints());
        assertFalse(player.hasLongestRoad());
        assertFalse(player.hasLargestArmy());
    }

    @Test
    public void removeRandomCardTest() {
        Player player = new Player(PlayerColor.RED);
        player.addResourceCard(ResourceCardType.BRICK);
        player.addResourceCard(ResourceCardType.LUMBER);
        player.addResourceCard(ResourceCardType.ORE);
        player.addResourceCard(ResourceCardType.GRAIN);
        player.addResourceCard(ResourceCardType.WOOL);
        player.addResourceCard(ResourceCardType.BRICK);
        boolean brick1 = false;
        boolean brick2 = false;
        boolean lumber = false;
        boolean ore = false;
        boolean grain = false;
        boolean wool = false;

        assertEquals(6, player.getNumResourceCards());
        while(player.getNumResourceCards() > 0) {
            switch(player.removeRandomCard().getType()) {
                case BRICK:
                    if (!brick1)
                        brick1 = true;
                    else if (!brick2)
                        brick2 = true;
                    else
                        assertFalse(true);
                    break;
                case LUMBER:
                    if (!lumber)
                        lumber = true;
                    else
                        assertFalse(true);
                    break;
                case ORE:
                    if (!ore)
                        ore = true;
                    else
                        assertFalse(true);
                    break;
                case GRAIN:
                    if (!grain)
                        grain = true;
                    else
                        assertFalse(true);
                    break;
                case WOOL:
                    if (!wool)
                        wool = true;
                    else
                        assertFalse(true);
                    break;
                default:
                    assertFalse(true);
                    break;
            }
        }
        assertEquals(0, player.getNumResourceCards());
        assertEquals(null, player.removeRandomCard());
        assertEquals(0, player.getNumResourceCards());
    }
}