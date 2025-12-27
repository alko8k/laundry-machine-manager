package persistence;

import org.json.JSONObject;

// citation: code modelled from CPSC210 Project Phase 2 
//           sample application: "JSonSerializationDemo"
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();

}