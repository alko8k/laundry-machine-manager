package model;

import org.json.JSONObject;
import persistence.Writable;
import model.EventLog;
import model.Event;

// represents a single washing machine with a id, a status,
// a true or false value for reserved, who it was reserved by, and a timer.
public class LaundryMachine implements Writable {
    private int id;
    private Status status;
    private boolean reserved;
    private String reservedBy;
    LaundryTimer timer;

    // EFFECTS: Creates a new laundry machine with id,
    // sets status to AVAILABLE, sets time to 0,
    // not reserved, no user.
    public LaundryMachine(int id) {
        this.id = id;
        this.status = Status.AVAILABLE;
        this.reserved = false;
        this.reservedBy = null;
        this.timer = new LaundryTimer();

    }

    // EFFECTS: returns the id of the current machine
    public int getId() {
        return id;
    }

    // EFFECTS: returns the current status of the machine
    public Status getStatus() {
        return status;
    }

    // EFFECTS: returns the student id of the student who reserved the machine
    public String getReservedBy() {
        return reservedBy;
    }

    // EFFECTS: returns the reserved value
    public boolean getReserved() {
        return reserved;
    }

    // MODIFIES: this
    // EFFECTS: sets the status to the new status given
    public void setStatus(Status status) {
        this.status = status;
        EventLog.getInstance().logEvent(new Event("Machine " + id + " status set to " + status));
    }

    // REQUIRES: the status of this machine is AVAILABLE
    // MODIFIES: this
    // EFFECTS: changes the value of reserved, sets reserved by to user
    public void setReserved(String user) {
        reserved = true;
        reservedBy = user;
        status = Status.RESERVED;
        EventLog.getInstance().logEvent(new Event("Machine " + id + " reserved by " + user));
    }

    // REQUIRES: this machine is reserved
    // MODIFIES: this
    // EFFECTS: cancels the reservation and resets status if currently reserved
    public void cancelReservation() {
        if (status == Status.RESERVED) {
            status = Status.AVAILABLE;
            reserved = false;
            reservedBy = null;
            EventLog.getInstance().logEvent(new Event("Reservation cancelled for machine " + id));
        }

    }

    // REQUIRES: status of this machine to be RESERVED
    // MODIFIES: this
    // EFFECTS: starts the wash cycle if machine is reserved, sets status to RUNNING
    public boolean startCycle() {
        if (status == Status.RESERVED) {
            status = Status.RUNNING;
            reserved = false;
            reservedBy = null;
            EventLog.getInstance().logEvent(new Event("Machine " + id + " started wash cycle"));
            return true;

        }
        return false;
    }

    // REQUIRES: status this machine is RUNNING
    // MODIFIES: this
    // EFFECTS: ends the wash cycle, resets timer, sets status to AVAILABLE
    public boolean endCycle() {
        if (status == Status.RUNNING) {
            status = Status.AVAILABLE;
            EventLog.getInstance().logEvent(new Event("Machine " + id + " ended wash cycle"));
            return true;
        }
        return false;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("status", status);
        json.put("reserved", reserved);
        json.put("reservedBy", reservedBy);
        return json;
    }
}
