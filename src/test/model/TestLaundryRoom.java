package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class TestLaundryRoom {
    private LaundryRoom lr;
    private ArrayList<LaundryMachine> testMachines;

    @BeforeEach

    void reunBefore() {
        lr = new LaundryRoom();
        lr.initializeMachines();
        testMachines = new ArrayList<>(lr.getlaundryMachines());

    }

    @Test
    void tetsInitializeMachinesAndGetLaundryMachines() {
        assertEquals(10, testMachines.size());
        assertEquals(1, testMachines.get(0).getId());
        assertEquals(2, testMachines.get(1).getId());
        assertEquals(3, testMachines.get(2).getId());
        assertEquals(4, testMachines.get(3).getId());
        assertEquals(5, testMachines.get(4).getId());
        assertEquals(6, testMachines.get(5).getId());
        assertEquals(7, testMachines.get(6).getId());
        assertEquals(8, testMachines.get(7).getId());
        assertEquals(9, testMachines.get(8).getId());
        assertEquals(10, testMachines.get(9).getId());
        assertEquals(Status.AVAILABLE, testMachines.get(0).getStatus());
        assertFalse(testMachines.get(0).getReserved());
        assertNull(testMachines.get(0).getReservedBy());
        assertEquals(Status.AVAILABLE, testMachines.get(1).getStatus());
        assertFalse(testMachines.get(1).getReserved());
        assertNull(testMachines.get(1).getReservedBy());
        assertEquals(Status.AVAILABLE, testMachines.get(2).getStatus());
        assertFalse(testMachines.get(2).getReserved());
        assertNull(testMachines.get(2).getReservedBy());

    }

    @Test
    void testGetName() {
        assertEquals("Qelexen", lr.getName());
    }

    @Test
    void testGetAvailableLaundryMachines() {
        String available = lr.getAvailableLaundryMachines();
        assertTrue(available.contains("1"));
        assertTrue(available.contains("5"));
        assertTrue(available.contains("10"));

        lr.getlaundryMachines().get(0).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(9).setStatus(Status.RUNNING);

        String available2 = lr.getAvailableLaundryMachines();
        assertFalse(available2.contains("1"));
        assertFalse(available2.contains("10"));
        assertTrue(available2.contains("5"));

        lr.getlaundryMachines().get(0).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(1).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(2).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(3).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(4).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(5).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(6).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(7).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(8).setStatus(Status.RESERVED);
        lr.getlaundryMachines().get(9).setStatus(Status.RESERVED);
        String available3 = lr.getAvailableLaundryMachines();
        assertEquals("No Available Machines", available3);
    }

    @Test
    void testGetNumLaundryMachines() {
        assertEquals(10, lr.getNumLaundryMachines());
    }

    @Test
    void testgetMachineById() {
        LaundryMachine machine = lr.getMachineById(4);
        assertEquals(4, machine.getId());
        assertEquals(Status.AVAILABLE, machine.getStatus());
    }

    @Test
    void testGetNumMachinesRunning() {
        assertEquals(0, lr.getNumMachinesRunning());

        lr.getlaundryMachines().get(0).setStatus(Status.RUNNING);
        assertEquals(1, lr.getNumMachinesRunning());
        lr.getlaundryMachines().get(5).setStatus(Status.RUNNING);
        lr.getlaundryMachines().get(9).setStatus(Status.RUNNING);
        assertEquals(3, lr.getNumMachinesRunning());

    }

    @Test
    void testRemoveBrokenMachine() {

        lr.getlaundryMachines().get(9).setStatus(Status.AVAILABLE);
        lr.removeBrokenMachine(10);
        assertEquals(10, lr.getNumLaundryMachines());
        lr.getlaundryMachines().get(4).setStatus(Status.OUT_OF_ORDER);
        lr.removeBrokenMachine(5);
        assertEquals(9, lr.getNumLaundryMachines());
        assertNull(lr.getMachineById(5));

        lr.getlaundryMachines().get(0).setStatus(Status.OUT_OF_ORDER);
        lr.removeBrokenMachine(3);
        assertEquals(9, lr.getNumLaundryMachines());

    }

    @Test
    void testAddMachine() {
        int num1 = lr.getNumLaundryMachines();

        lr.addMachine(11);

        int num2 = lr.getNumLaundryMachines();

        assertEquals(num1 += 1, num2);

        LaundryMachine added = lr.getMachineById(11);
        assertEquals(11, added.getId());
        assertEquals(Status.AVAILABLE, added.getStatus());
        assertFalse(added.getReserved());
        assertNull(added.getReservedBy());

    }
}
