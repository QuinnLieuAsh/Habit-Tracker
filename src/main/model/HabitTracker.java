package model;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Tracks all a users habits, the date, and provides a list of habits that haven't been 
// completed today as well as a list of completed habits for the day. 
public class HabitTracker implements Writable {

    private ArrayList<Habit> habits;
    private ArrayList<Habit> completed;
    private ArrayList<Habit> todo;
    private LocalDate completionDate;

    public HabitTracker() {
        habits = new ArrayList<>();
        completed = new ArrayList<>();
        todo = new ArrayList<>();
        completionDate = LocalDate.now();

    }

    // MODIFIES:this
    // EFFECTS: Records a habit in the list of habits
    public void recordHab(Habit h) {
        if (todo.contains(h)) {
            h.addProgress();
            markCompleted(h);
        }
    }

    // EFFECTS: Returns a habits reward message
    public String reward(Habit h) {
        int progress = h.getProgress();
        int goal = h.getLongGoal();
        if (progress == goal) {
            EventLog.getInstance().logEvent(new Event("Reward message: " + h.getReward() + " was displayed to user."));
            return h.getReward();
        } else {
            return null;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a habit to habits, and adds it to todo
    public void addHabit(Habit h) {
        habits.add(h);
        todo.add(h);
        EventLog.getInstance().logEvent(new Event("New Habit: " + h.getTitle() + " was added to your habits."));
    }

    // MODIFIES: this
    // EFFECTS: removes habit form todo, and adds habit to completed
    public void markCompleted(Habit h) {
        todo.remove(h);
        completed.add(h);
        EventLog.getInstance().logEvent(new Event("Habit: " + h.getTitle() + " was added list of completed habits."));

    }

    // MODIFIES: this
    // EFFECTS: removes habit from completed, and adds habit to todo.
    public void markTodo(Habit h) {
        completed.remove(h);
        todo.add(h);
        EventLog.getInstance().logEvent(new Event("Habit: " + h.getTitle() + " was added list of TODO habits."));

    }

    // MODIFIRES: this
    // EFFECTS: remove a habit from all lists
    public void removeHabit(Habit h) {
        habits.remove(h);
        completed.remove(h);
        todo.remove(h);
        EventLog.getInstance().logEvent(new Event("Habit: " + h.getTitle() + " was removed from your habits."));

    }

    // MODIFIES: this
    // EFFECTS: move all the completed habits back to the todo,
    // in the order found in habits.
    public void resetCompletedHabits() {
        completed.clear();
        todo.clear();
        todo.addAll(habits);
        EventLog.getInstance().logEvent(new Event("TODO list was reset."));

    }

    // MODIFIES: this
    // EFFECTS: resets the todo list each new day
    public void dailyReset() {
        if (!completionDate.equals(LocalDate.now())) {
            EventLog.getInstance().logEvent(new Event("New day detected."));
            resetCompletedHabits();
        }
        completionDate = LocalDate.now();
    }

    public ArrayList<Habit> getAllHabits() {
        return new ArrayList<>(habits);
    }

    public ArrayList<Habit> getCompleted() {
        return new ArrayList<>(completed);
    }

    public ArrayList<Habit> getTodo() {
        return new ArrayList<>(todo);
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    // MODIFIES: this
    // EFFECTS: Decreases the completionDate by one day to simulate today being a
    // new day (For testing purposes)
    public void changeDate() {
        completionDate = completionDate.minusDays(1);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("habits", habitsToJson());
        json.put("todo", todoToJson());
        json.put("completed", completedToJson());
        json.put("completionDate", completionDate);
        return json;
    }

    // EFFECTS: returns all habits as JSON Array
    private JSONArray habitsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habit h : habits) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns all Todo habits as JSON Array
    private JSONArray todoToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habit h : todo) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns all Completed habits as JSON Array
    private JSONArray completedToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Habit h : completed) {
            jsonArray.put(h.toJson());
        }

        return jsonArray;
    }

    // MODIFIES: this
    // EFFECTS: adds a habit to completed (for json reader)
    public void addJsonCompleted(Habit h) {
        completed.add(h);
    }

    // MODIFIES: this
    // EFFECTS: adds a habit to completed (for json reader)
    public void addJsonHabit(Habit h) {
        habits.add(h);
    }

    // MODIFIES: this
    // EFFECTS: adds a habit to completed (for json reader)
    public void addJsonTodo(Habit h) {
        todo.add(h);
    }

    // MODIFIES: this
    // EFFECTS: sets the date to the a given date (for json reader)
    public void setCompletionDate(LocalDate date) {
        this.completionDate = date;
    }

}
