package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.HabitTracker;

//A reader that reads Habittracker from JSON data stored in file
//References and uses code from JsonReader class in JsonSerializationDemo.
public class JSONReader {
     private String source;

    // EFFECTS: constructs reader to read from source file
    public JSONReader(String source) {
        //stub
    }

    // EFFECTS: reads HabitTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HabitTracker read() throws IOException {
        return null;//stub
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return null;//stub
    }

    // EFFECTS: parses HabitTracker from JSON object and returns it
    private HabitTracker parseWorkRoom(JSONObject jsonObject) {
        return null;//stub

    }

    // MODIFIES: ht
    // EFFECTS: parses habits from JSON object and adds them to HabitTracker
    private void addHabits(HabitTracker ht, JSONObject jsonObject) {
        //stub
    }

    // MODIFIES: ht
    // EFFECTS: parses completed from JSON object and adds them to HabitTracker
    private void addCompleted(HabitTracker ht, JSONObject jsonObject) {
        //stub
    }

    // MODIFIES: ht
    // EFFECTS: parses todo from JSON object and adds them to HabitTracker
    private void addTodo(HabitTracker ht, JSONObject jsonObject) {
        //stub
    }

    // MODIFIES: ht
    // EFFECTS: parses Habit from JSON object and adds it to HabitTracker
    private void addHabit(HabitTracker ht, JSONObject jsonObject) {
        //stub
    }

}
