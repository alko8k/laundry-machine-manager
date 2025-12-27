package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExcludeFromJacocoGeneratedReport
public class TestLaundryTimer {

    private LaundryTimer time;

    @BeforeEach
    void runBefore() {
        time = new LaundryTimer();
    }

    @Test
    void testLaundryTimer() {
        assertEquals(0, time.getTime());
        time.start(30);
        assertEquals(30, time.getTime());
        time.reset();
        assertEquals(0, time.getTime());
    }

    @Test
    void testSetTime() {
        time.setTime(30);
        assertEquals(30, time.getTime());
    }

    @Test
    void testIsDone() {
        time.setTime(30);
        assertFalse(time.isDone());
        time.reset();
        assertTrue(time.isDone());
    }
}