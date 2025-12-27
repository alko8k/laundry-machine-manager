package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.LaundryMachine;
import model.Status;

// citation: adapted from CPSC 210 "JsonSerializationDemo"
@ExcludeFromJacocoGeneratedReport
public class JsonTest {

    // EFFECTS: checks that the given LaundryMachine has the expected values
    protected void checkMachine(int expectedId, Status expectedStatus,
            boolean expectedReserved, String expectedReservedBy,
            LaundryMachine actualMachine) {
        assertEquals(expectedId, actualMachine.getId());
        assertEquals(expectedStatus, actualMachine.getStatus());
        assertEquals(expectedReserved, actualMachine.getReserved());
        assertEquals(expectedReservedBy, actualMachine.getReservedBy());
    }
}
