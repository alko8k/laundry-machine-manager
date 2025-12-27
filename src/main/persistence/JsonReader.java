package persistence;

import model.Event;
import model.EventLog;
import model.LaundryMachine;
import model.LaundryRoom;
import model.Status;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// citation: code modelled from CPSC210 Project Phase 2 
//           sample application: "JSonSerializationDemo"
// Represents a reader that reads LaundryRoom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads LaundryRoom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LaundryRoom read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Loaded laundry room from file"));
        return parseLaundryRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses LaundryRoom from JSON object and returns it
    private LaundryRoom parseLaundryRoom(JSONObject jsonObject) {
        LaundryRoom lr = new LaundryRoom();
        lr.setName(jsonObject.getString("name"));
        addMachines(lr, jsonObject);
        return lr;
    }

    // MODIFIES: lr
    // EFFECTS: parses thingies from JSON object and adds them to LaundryRoom
    private void addMachines(LaundryRoom lr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("machines");
        for (Object json : jsonArray) {
            JSONObject nextMachine = (JSONObject) json;
            addMachine(lr, nextMachine);
        }
    }

    // MODIFIES: lr
    // EFFECTS: parses thingy from JSON object and adds it to LaundryRoom
    private void addMachine(LaundryRoom lr, JSONObject jsonObject) {
        int id = jsonObject.getInt("id");
        Status status = Status.valueOf(jsonObject.getString("status"));
        boolean reserved = jsonObject.getBoolean("reserved");
        String reservedBy = jsonObject.optString("reservedBy", null);

        LaundryMachine machine = new LaundryMachine(id);
        machine.setStatus(status);
        if (reserved && reservedBy != null) {
            machine.setReserved(reservedBy);
        }

        lr.getlaundryMachines().add(machine);
    }

}
