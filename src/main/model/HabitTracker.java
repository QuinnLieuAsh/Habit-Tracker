package model;

import java.time.LocalDate;
import java.util.ArrayList;

// Tracks all a users habits, the date, and provides a list of habits that haven't been 
// completed today as well as a list of completed habits for the day. 
public class HabitTracker {

    private ArrayList<Habit> habits;
    private ArrayList<Habit> completed;
    private ArrayList<Habit> todo;
    private LocalDate date;


    public HabitTracker() {
        habits = new ArrayList<>();
        completed = new ArrayList<>();
        todo = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a habit to habits, and adds it to todo
    public void addHabit(Habit h) {
        habits.add(h);
        todo.add(h);
    }

    //MODIFIES: this
    //EFFECTS: removes habit form todo, and adds habit to completed
    public void markCompleted(Habit h) {
        todo.remove(h);
        completed.add(h);
    }

    //MODIFIES: this 
    //EFFECTS: removes habit from completed, and adds habit to todo.
    public void markTodo(Habit h) {
        completed.remove(h);
        todo.add(h);
    }
    
    //MODIFIRES: this
    //EFFECTS: remove a habit from all lists
    public void removeHabit(Habit h) {
        habits.remove(h);
        completed.remove(h);
        todo.remove(h);
    }
    //MODIFIES: this
    //EFFECTS: move all the completed habits back to the todo,
    // in the order found in habits. 
    public void resetCompletedHabits() {
        //stub
    }
    
    //MODIFIES: this
    //EFFECTS: resets the todo list each day
    public void dailyReset() {
        //stub
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



}
