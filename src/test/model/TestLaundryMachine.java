package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class TestLaundryMachine {

    private LaundryMachine tlm;

    @BeforeEach
    void runBefore() {
        tlm = new LaundryMachine(1);
    }

    @Test
    void testLaundryMachine() {
        assertEquals(1, tlm.getId());
        assertEquals(Status.AVAILABLE, tlm.getStatus());
        assertFalse(tlm.getReserved());
        assertEquals(null, tlm.getReservedBy());
    }

    @Test
    void testSetStatus() {
        tlm.setStatus(Status.RUNNING);
        assertEquals(Status.RUNNING, tlm.getStatus());

        tlm.setStatus(Status.AVAILABLE);
        assertEquals(Status.AVAILABLE, tlm.getStatus());

        tlm.setStatus(Status.RESERVED);
        assertEquals(Status.RESERVED, tlm.getStatus());
    }

    @Test
    void testSetReserved() {
        tlm.setStatus(Status.AVAILABLE);
        tlm.setReserved("28845246");
        assertTrue(tlm.getReserved());
        assertEquals("28845246", tlm.getReservedBy());
        assertEquals(Status.RESERVED, tlm.getStatus());
    }

    @Test
    void testSetReservedWhenNotAvailable() {
        tlm.setStatus(Status.RUNNING);
        tlm.setReserved("28845246");
        assertTrue(tlm.getReserved());
        assertEquals("28845246", tlm.getReservedBy());
    }

    @Test
    void testCancelReserved() {
        tlm.setStatus(Status.AVAILABLE);
        tlm.setReserved("28845246");
        tlm.cancelReservation();
        assertEquals(Status.AVAILABLE, tlm.getStatus());
        assertFalse(tlm.getReserved());
        assertNull(tlm.getReservedBy());
    }

    @Test
    void testCancelReservedWhenNotReserved() {
        tlm.setStatus(Status.RUNNING);
        tlm.cancelReservation();
        assertEquals(Status.RUNNING, tlm.getStatus());
    }

    @Test
    void testStartCycle() {
        tlm.setStatus(Status.RESERVED);
        tlm.startCycle();
        assertEquals(Status.RUNNING, tlm.getStatus());

        tlm.setStatus(Status.AVAILABLE);
        tlm.startCycle();
        assertEquals(Status.AVAILABLE, tlm.getStatus());
    }

    @Test
    void testEndCycle() {
        tlm.setStatus(Status.RUNNING);
        tlm.endCycle();
        assertEquals(Status.AVAILABLE, tlm.getStatus());

        tlm.setStatus(Status.AVAILABLE);
        tlm.endCycle();
        assertEquals(Status.AVAILABLE, tlm.getStatus());
    }
}
