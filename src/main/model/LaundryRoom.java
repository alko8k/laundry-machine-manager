package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import persistence.Writable;
import org.json.JSONArray;
import model.Event;
import model.EventLog;

// Represents a laundry room in the qelexen first year residence building, this room has a list of machines, with Status
public class LaundryRoom implements Writable {

    private String name;
    private List<LaundryMachine> machines;

    // MODIFIES: this
    // EFFECTS: Creates a new laundry room with name "Qelexen"
    public LaundryRoom() {
        this.name = "Qelexen";
        this.machines = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: initializes 10 unique machines with unique ids from 1 to 10
    public void initializeMachines() {
        for (int i = 1; i <= 10; i++) {
            machines.add(new LaundryMachine(i));
        }
    }

    // EFFECTS: returns the name of the laundry room
    public String getName() {
        return name;
    }

    // EFFECTS: sets the name of the laundry room
    public void setName(String name) {
        this.name = name;
    }

    // EFFECTS: returns a list of all laundry machines regardless of the status.
    public List<LaundryMachine> getlaundryMachines() {
        return machines;
    }

    // EFFECTS: returns all the available laundry machines as a string
    public String getAvailableLaundryMachines() {
        String available = "Available_Machines: ";

        for (LaundryMachine m : machines) {
            if (m.getStatus() == Status.AVAILABLE) {
                available += m.getId() + " ";

            }
        }
        if (!available.equals("Available_Machines: ")) {
            return available;
        }
        return "No Available Machines";
    }

    // EFFECTS: returns the number of laundry machines in the list of laundry
    // machines
    public int getNumLaundryMachines() {
        return machines.size();
    }

    // EFFECTS: returns the laundry machine with the given id and the Status of the
    // machine
    public LaundryMachine getMachineById(int id) {
        for (LaundryMachine m : machines) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    // EFFECTS: returns the number of washing machines running in the list
    public int getNumMachinesRunning() {
        int count = 0;
        for (LaundryMachine m : machines) {
            if (m.getStatus() == Status.RUNNING) {
                count++;
            }
        }
        return count;
    }

    // Modifies: this
    // EFFECTS: removes washing machine with the given id from list if it is
    // OUT_OF_ORDER,
    // if not does nothing
    public void removeBrokenMachine(int id) {
        for (int i = 0; i < machines.size(); i++) {
            LaundryMachine m = machines.get(i);
            if (m.getId() == id && m.getStatus() == Status.OUT_OF_ORDER) {
                machines.remove(m);
                EventLog.getInstance().logEvent(new Event("Removed machine with ID : " + id));
                return;

            }
        }
    }

    // MODIFIES:this
    // EFFECTS: adds new laundry machine with given id to the list of laundry
    // machines when there is a new laundry machine in the residence
    public void addMachine(int id) {
        machines.add(new LaundryMachine(id));
        EventLog.getInstance().logEvent(new Event("Added a new machine with ID : " + id));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);

        JSONArray jsonMachines = new JSONArray();
        for (LaundryMachine m : machines) {
            jsonMachines.put(m.toJson());
        }
        json.put("machines", jsonMachines);

        return json;
    }

}
