package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

import model.HabitTracker;
import model.SimpleHabit;
import model.Habit;

//A reader that reads Habittracker from JSON data stored in file
//References and uses code from JsonReader class in JsonSerializationDemo.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads HabitTracker from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HabitTracker read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHabitTracker(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses HabitTracker from JSON object and returns it
    private HabitTracker parseHabitTracker(JSONObject jsonObject) {
        HabitTracker ht = new HabitTracker();
        addHabits(ht, jsonObject);
        return ht;

    }

    // MODIFIES: ht
    // EFFECTS: parses habits from JSON object and adds them to HabitTracker
    private void addHabits(HabitTracker ht, JSONObject jsonObject) {
        JSONArray jsonArrayAll = jsonObject.getJSONArray("habits");

        for (Object json : jsonArrayAll) {
            JSONObject nextThingy = (JSONObject) json;
            addHabit(ht, nextThingy);
        }

        JSONArray jsonArrayTodo = jsonObject.getJSONArray("todo");

        for (Object json : jsonArrayTodo) {
            JSONObject nextThingy = (JSONObject) json;
            addTodo(ht, nextThingy);
        }

        JSONArray jsonArrayCompleted = jsonObject.getJSONArray("completed");

        for (Object json : jsonArrayCompleted) {
            JSONObject nextThingy = (JSONObject) json;
            addCompleted(ht, nextThingy);
        }

    }

    // MODIFIES: ht
    // EFFECTS: parses completed from JSON object and adds them to HabitTracker
    private void addCompleted(HabitTracker ht, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int longGoal = jsonObject.getInt("longGoal");
        int shortGoal = jsonObject.getInt("shortGoal");
        int progress = jsonObject.getInt("progress");
        String reward = jsonObject.getString("reward");
        int intermediates = longGoal / shortGoal;
        Habit habit = new SimpleHabit(title, longGoal, intermediates, reward);
        habit.setProgress(progress); // todo
        ht.addJsonCompleted(habit);
    }

    // MODIFIES: ht
    // EFFECTS: parses todo from JSON object and adds them to HabitTracker
    private void addTodo(HabitTracker ht, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int longGoal = jsonObject.getInt("longGoal");
        int shortGoal = jsonObject.getInt("shortGoal");
        int progress = jsonObject.getInt("progress");
        String reward = jsonObject.getString("reward");
        int intermediates = longGoal / shortGoal;
        Habit habit = new SimpleHabit(title, longGoal, intermediates, reward);
        habit.setProgress(progress); // todo
        ht.addJsonTodo(habit);
    }

    // MODIFIES: ht
    // EFFECTS: parses Habit from JSON object and adds it to HabitTracker
    private void addHabit(HabitTracker ht, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int longGoal = jsonObject.getInt("longGoal");
        int shortGoal = jsonObject.getInt("shortGoal");
        int progress = jsonObject.getInt("progress");
        String reward = jsonObject.getString("reward");
        int intermediates = longGoal / shortGoal;
        Habit habit = new SimpleHabit(title, longGoal, intermediates, reward);
        habit.setProgress(progress); // todo
        ht.addJsonHabit(habit);
    }

}
