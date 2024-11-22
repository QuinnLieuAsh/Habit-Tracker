package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Habit;
import model.HabitTracker;
import model.SimpleHabit;

// A controller to comunicate between Model classes and GUI
public class Controller {
    private HabitTracker tracker;
    private Habit selected;

    public Controller(HabitTracker tracker) {
        this.tracker = tracker;
        selected = null;
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
    public String reward(Habit h) {
        return null; //stub
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
    public void removeHabit() {
        tracker.removeHabit(selected);
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
        return tracker.getAllHabits(); //stub
    }

    // EFFECTS: calls getCompleted in habitTracker
    public ArrayList<Habit> getCompleted() {
        return null; //stub
    }

    // EFFECTS: calls getTodo in habitTracker
    public ArrayList<Habit> getTodo() {
        return tracker.getTodo(); //stub
    }

    // EFFECTS: calls getCompletionDate in habitTracker
    public LocalDate getCompletionDate() {
        return null; //stub
    }

    // EFFECTS: calls toJson in habitTracker
    public JSONObject toJson() {
        return null; //stub
    }

    // EFFECTS: calls toJson in habitTracker
    public void select(Habit h) {
        selected = h;
    }

    
}
