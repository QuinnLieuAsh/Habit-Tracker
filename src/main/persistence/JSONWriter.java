package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;

import model.HabitTracker;

//A writer that writes JSON representation of HabitTracker to file.
//References and uses code from JsonWriter class in JsonSerializationDemo.
public class JSONWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JSONWriter(String destination) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of HabitTracker to file
    public void write(HabitTracker ht) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        //stub
    }
}
