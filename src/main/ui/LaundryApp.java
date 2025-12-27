package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.LaundryMachine;
import model.Status;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.LaundryRoom;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class LaundryApp {

    private LaundryRoom lr;
    private Scanner input;

    private static final String JSON_STORE = "projectStarter/src/data/testReaderGeneralLaundryRoom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the laundry manager application
    public LaundryApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        lr = new LaundryRoom();
        lr.initializeMachines();
        input = new Scanner(System.in);
        System.out.println("Welcome to the Qelexen Laundry Manager!");
        runLaundry();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLaundry() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }

        }
        System.out.println("\nGoodbye");
    }

    // MODIFIES:this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            viewAllMachines();
        } else if (command.equals("r")) {
            reserveMachine();
        } else if (command.equals("u")) {
            unreserveMachine();
        } else if (command.equals("s")) {
            setStatus();
        } else if (command.equals("a")) {
            addMachine();
        } else if (command.equals("x")) {
            removeBrokenMachine();
        } else if (command.equals("c")) {
            startCycle();
        } else if (command.equals("e")) {
            endCycle();
        } else if (command.equals("sf")) {
            saveLaundryRoom();
        } else if (command.equals("ld")) {
            loadLaundryRoom();
        } else {
            System.out.println("Invalid command.");
        }

    }

    // EFFECTS: displays the of options to user
    private void displayMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("\tv -> View All Machines");
        System.out.println("\tr -> Reserve A Machine");
        System.out.println("\tu -> Unreserve A Machine");
        System.out.println("\ts -> Set A Machine's Status");
        System.out.println("\ta -> Add A New Machine");
        System.out.println("\tx -> Remove A Broken (Out of Order) Machine");
        System.out.println("\tc -> Start A Cycle");
        System.out.println("\te -> End A Cycle");
        System.out.println("\tsf -> save LaundryRoom to file");
        System.out.println("\tld -> load LaundryRoom from file");
        System.out.println("\tq -> Quit");

    }

    // EFFECTS: prints all machines and their statuses
    private void viewAllMachines() {
        for (LaundryMachine m : lr.getlaundryMachines()) {
            System.out.println("Machine " + m.getId()
                    + " | Status: " + m.getStatus()
                    + " | Reserved: " + m.getReserved()
                    + " | ReservedBy: " + m.getReservedBy());
        }
    }

    // MODIFIES: this
    // EFFECTS: reserves a machine if available
    private void reserveMachine() {
        System.out.println("Enter Machine id To Reserve");
        int id = input.nextInt();
        LaundryMachine m = lr.getMachineById(id);

        if (m == null) {
            System.out.println("No Machine With That id");
        } else if (m.getStatus() == Status.AVAILABLE) {
            System.out.println("Enter Your Student ID");
            String user = input.next();
            m.setReserved(user);
            System.out.println(" Machine " + id + " Reserved By " + user + ".");
        } else {
            System.out.println("Machine " + id + " Is Not Available.");
        }
    }

    // MODIFIES: this
    // EFFECTS: unreseves machine if reserved
    private void unreserveMachine() {
        System.out.println("Enter Machine id To Cancel Reservation: ");
        int id = input.nextInt();
        LaundryMachine m = lr.getMachineById(id);

        if (m == null) {
            System.out.println("No Machine With That id");
        } else if (m.getStatus() == Status.RESERVED) {
            m.cancelReservation();
            System.out.println("Reservation Cancelled For Machine " + id + " . ");
        } else {
            System.out.println("Machine " + id + " Is Not Reserved.");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets a machine's status
    private void setStatus() {
        System.out.println("Enter Machine id: ");
        int id = input.nextInt();
        LaundryMachine m = lr.getMachineById(id);

        if (m == null) {
            System.out.println("No Machine With That id.");
            return;
        }

        System.out.println("Current status: " + m.getStatus());
        System.out.println("Enter New Status(AVAILABLE, RUNNING, RESERVED, OUT_OF_ORDER)");
        String status = input.next().toUpperCase();

        if (status.equals("AVAILABLE")) {
            m.setStatus(Status.AVAILABLE);
        } else if (status.equals("RUNNING")) {
            m.setStatus(Status.RUNNING);
        } else if (status.equals("RESERVED")) {
            m.setStatus(Status.RESERVED);
        } else if (status.equals("OUT_OF_ORDER")) {
            m.setStatus(Status.OUT_OF_ORDER);
        } else {
            System.out.println("Invalid status");
            return;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a new laundry machine
    private void addMachine() {
        System.out.println("Enter id For New Machine");
        int id = input.nextInt();
        lr.addMachine(id);
        System.out.println("Added Machine With id " + id + " . ");
    }

    // MODIFIES: this
    // EFFECTS: removes machine if it is OUT_OF_ORDER
    private void removeBrokenMachine() {
        System.out.println("Enter Machine id To Remove (must Be OUT_OF_ORDER)");
        int id = input.nextInt();
        lr.removeBrokenMachine(id);
        System.out.println("If Machine " + id + " Was Out Of Order, It Has Been Removed.");
    }

    // MODIFIES: this
    // EFFECTS: starts the wash cycle if machine is reserved
    private void startCycle() {
        System.out.println("Enter Machine ID To Start Cycle:");
        int id = input.nextInt();
        LaundryMachine m = lr.getMachineById(id);

        if (m == null) {
            System.out.println("No Machine With That ID.");
        } else {
            boolean success = m.startCycle();
            if (success) {
                System.out.println("Machine" + id + " Has Started Its Wash Cycle.");
            } else {
                System.out.println("Machine " + id + " Is Either Not Reserved, Already Running, Or Out Of Order.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: ends the wash cycle if running
    private void endCycle() {
        System.out.println("Enter Machine ID To End Cycle:");
        int id = input.nextInt();
        LaundryMachine m = lr.getMachineById(id);

        if (m == null) {
            System.out.println("No Machine With That ID.");
        } else {
            boolean success = m.endCycle();
            if (success) {
                System.out.println("Machine" + id + " Has Finished Its Cycle.");
            } else {
                System.out.println("Machine " + id + " Has No Active Cycle.");
            }
        }
    }

    // EFFECTS: saves the LaundryRoom to file
    private void saveLaundryRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(lr);
            jsonWriter.close();
            System.out.println("Saved " + lr.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads LaundryRoom from file
    private void loadLaundryRoom() {
        try {
            lr = jsonReader.read();
            System.out.println("Loaded " + lr.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
