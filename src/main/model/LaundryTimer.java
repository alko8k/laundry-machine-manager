package model;

//Represents a timer for a laundry machine that tracks the remaining cycle time.
public class LaundryTimer {
    private int timeRemaining;

    // EFFECTS: Creates a new timer with 0 minutes left
    public LaundryTimer() {
        this.timeRemaining = 0;
    }

    // MODIFIES: this
    // EFFECTS: sets the time to the given value
    public void setTime(int time) {
        this.timeRemaining = time;
    }

    // EFFECTS: returns the current time left
    public int getTime() {
        return timeRemaining;
    }

    // MODIFIES: this
    // EFFECTS: starts the timer with the given duration in mins
    public void start(int duration) {
        this.timeRemaining = duration;
    }

    // MODIFIES: this
    // EFFECTS: resets timer to timeRemaining
    public void reset() {
        this.timeRemaining = 0;
    }

    // EFFECTS: returns true if the timer has finished
    public boolean isDone() {
        if (timeRemaining == 0) {
            return true;
        } else {
            return false;
        }

    }
}