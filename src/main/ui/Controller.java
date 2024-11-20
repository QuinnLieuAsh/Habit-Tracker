package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Habit;
import model.HabitTracker;

// A controller to comunicate between Model classes and GUI
public class Controller {
    private HabitTracker tracker;

    // MODIFIES: this
    // EFFECTS: calls AddHabit in habitTracker
    public void addHabit(Habit h) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: calls markCompleted in habitTracker
    public void markCompleted(Habit h) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: calls markTodo in habitTracker
    public void markTodo(Habit h) {
        //stub
    }

    // MODIFIRES: this
    // EFFECTS: calls removeHabit in habitTracker
    public void removeHabit(Habit h) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: calls resetCompletedHabits in habitTracker
    public void resetCompletedHabits() {
       //stub
    }

    // MODIFIES: this
    // EFFECTS: calls dailyReset in habitTracker
    public void dailyReset() {
        //stub
    }

    // EFFECTS: calls getAllHabits in habitTracker
    public ArrayList<Habit> getAllHabits() {
        return null; //stub
    }

    // EFFECTS: calls getCompleted in habitTracker
    public ArrayList<Habit> getCompleted() {
        return null; //stub
    }

    // EFFECTS: calls getTodo in habitTracker
    public ArrayList<Habit> getTodo() {
        return null; //stub
    }

    // EFFECTS: calls getCompletionDate in habitTracker
    public LocalDate getCompletionDate() {
        return null; //stub
    }

    // EFFECTS: calls toJson in habitTracker
    public JSONObject toJson() {
        return null; //stub
    }
}
