package edu.rpi.cs.csci4960.s21.javatan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class TupleTest {
    @Test
    public void basicTest() {
        Road road = new Road();
        Tuple<String, Road> tuple = new Tuple<String, Road>("test", road);
        assertEquals("test", tuple.t);
        assertEquals(road, tuple.k);
    }
}