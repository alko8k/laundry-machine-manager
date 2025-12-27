package persistence;

import model.LaundryMachine;
import model.LaundryRoom;
import model.Status;

import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
class JsonReaderTest extends JsonTest {

    @Test
    void debugJsonPath() {
        String path = "./src/data/testReaderEmptyLaundryRoom.json";
        System.out.println("Absolute path: " + Paths.get(path).toAbsolutePath());
        System.out.println("Exists? " + Files.exists(Paths.get(path)));
        if (Files.exists(Paths.get(path))) {
            try {
                System.out.println("File contents:");
                Files.lines(Paths.get(path)).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void printWorkingDirectory() {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("../data/testReaderEmptyLaundryRoom.json");

        try {
            LaundryRoom lr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLaundryRoom() {
        JsonReader reader = new JsonReader("projectStarter/src/data/testReaderEmptyLaundryRoom.json");
        try {
            LaundryRoom lr = reader.read();
            assertEquals("Qelexen", lr.getName());
            assertEquals(0, lr.getNumLaundryMachines());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLaundryRoom() {
        JsonReader reader = new JsonReader("projectStarter/src/data/testReaderGeneralLaundryRoom.json");
        try {
            LaundryRoom lr = reader.read();
            assertEquals("Qelexen", lr.getName());
            List<LaundryMachine> machines = lr.getlaundryMachines();
            assertEquals(10, machines.size());
            checkMachine(1, Status.AVAILABLE, false, null, machines.get(0));
            checkMachine(2, Status.AVAILABLE, false, null, machines.get(1));
            checkMachine(3, Status.AVAILABLE, false, null, machines.get(2));
            checkMachine(4, Status.AVAILABLE, false, null, machines.get(3));
            checkMachine(5, Status.AVAILABLE, false, null, machines.get(4));
            checkMachine(6, Status.AVAILABLE, false, null, machines.get(5));
            checkMachine(7, Status.AVAILABLE, false, null, machines.get(6));
            checkMachine(8, Status.AVAILABLE, false, null, machines.get(7));
            checkMachine(9, Status.AVAILABLE, false, null, machines.get(8));
            checkMachine(10, Status.AVAILABLE, false, null, machines.get(9));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}