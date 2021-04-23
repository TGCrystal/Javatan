package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DevelopmentCardTest {
    @Test
    public void basicTest() {
        DevelopmentCard knight = new DevelopmentCard(DevelopmentCardType.KNIGHT);
        DevelopmentCard progress = new DevelopmentCard(DevelopmentCardType.PROGRESS);
        DevelopmentCard victory = new DevelopmentCard(DevelopmentCardType.VICTORY);
        assertEquals(DevelopmentCardType.KNIGHT, knight.getType());
        assertEquals(DevelopmentCardType.PROGRESS, progress.getType());
        assertEquals(DevelopmentCardType.VICTORY, victory.getType());
    }
}