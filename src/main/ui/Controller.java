package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Habit;
import model.HabitTracker;
import model.SimpleHabit;
import persistence.JsonReader;
import persistence.JsonWriter;

// A controller to comunicate between Model classes and GUI
public class Controller {
    private static final String JSON_STORE = "./data/habitTracker.json";
    private HabitTracker tracker;
    private Habit selected;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Controller(HabitTracker tracker) {
        this.tracker = tracker;
        selected = null;
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: calls AddHabit in habitTracker
    public void addHabit(String habitName, int target, String rewardMessage) {
        Habit newHab = new SimpleHabit(habitName, target, 1, rewardMessage);
        tracker.addHabit(newHab);
    }

    //EFFECTS: returns the selected habit
    public Habit getSelected() {
        return selected;
    }

    // MODIFIES: this
    // EFFECTS: calls AddHabit in habitTracker
    public void recordHab() {
        tracker.recordHab(selected);
    }

    // EFFECTS: calls AddHabit in habitTracker
    public String reward() {
        return tracker.reward(selected);
    }

    // EFFECTS: Checks if selected has reached full progress
    public boolean checkProgress() {
        int progress = selected.getProgress();
        int goal = selected.getLongGoal();
        return progress == goal;
    }

    // MODIFIRES: this
    // EFFECTS: calls removeHabit in habitTracker
    public void removeHabit() {
        tracker.removeHabit(selected);
    }

    // MODIFIES: this
    // EFFECTS: calls dailyReset in habitTracker
    public void dailyReset() {
        tracker.dailyReset();
    }

    // EFFECTS: calls getAllHabits in habitTracker
    public ArrayList<Habit> getAllHabits() {
        return tracker.getAllHabits(); //stub
    }

    // EFFECTS: calls getTodo in habitTracker
    public ArrayList<Habit> getTodo() {
        return tracker.getTodo(); //stub
    }

    // MODIFIES: this
    // EFFECTS: calls toJson in habitTracker
    public void select(Habit h) {
        selected = h;
    }

    // MODIFIES: this
    // EFFECTS: saves the habit tracker to file
    public void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(tracker);
            jsonWriter.close();
            System.out.println("Saved HabitTracker to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads habit tracker from file
    public void loadProgress() {
        try {
            tracker = jsonReader.read();
            System.out.println("Loaded HabitTracker from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

