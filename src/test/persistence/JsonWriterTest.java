package persistence;

import model.LaundryMachine;
import model.LaundryRoom;
import model.Status;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// citation: adapted from CPSC210 "JsonSerializationDemo"
@ExcludeFromJacocoGeneratedReport
class JsonWriterTest extends JsonTest {
    // NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter
    // is to
    // write data to a file and then use the reader to read it back in and check
    // that we
    // read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("projectStarter/src/data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLaundryRoom() {
        try {
            LaundryRoom lr = new LaundryRoom();
            String filePath = "projectStarter/src/data/testWriterEmptyLaundryRoom.json";
            JsonWriter writer = new JsonWriter(filePath);
            writer.open();
            writer.write(lr);
            writer.close();

            JsonReader reader = new JsonReader(filePath);
            lr = reader.read();
            assertEquals("Qelexen", lr.getName());
            assertEquals(0, lr.getNumLaundryMachines());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLaundryRoom() {
        try {
            LaundryRoom lr = new LaundryRoom();
            LaundryMachine m1 = new LaundryMachine(1);
            LaundryMachine m2 = new LaundryMachine(2);

            m1.setStatus(Status.AVAILABLE);
            m2.setStatus(Status.RESERVED);
            m2.setReserved("28845236");

            lr.getlaundryMachines().add(m1);
            lr.getlaundryMachines().add(m2);

            String filePath = "projectStarter/src/data/testWriterGeneralLaundryRoom.json";
            JsonWriter writer = new JsonWriter(filePath);
            writer.open();
            writer.write(lr);
            writer.close();

            JsonReader reader = new JsonReader(filePath);
            lr = reader.read();
            assertEquals("Qelexen", lr.getName());
            assertEquals(2, lr.getNumLaundryMachines());
            checkMachine(1, Status.AVAILABLE, false, null, lr.getlaundryMachines().get(0));
            checkMachine(2, Status.RESERVED, true, "28845236", lr.getlaundryMachines().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}