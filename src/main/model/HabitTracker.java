package model;

import java.util.ArrayList;

// Tracks all a users habits, and provides a list of habits that haven't been 
// completed today as well as a list of completed habits for the day. 
public class HabitTracker {

    private ArrayList<Habit> habits;
    private ArrayList<Habit> completed;
    private ArrayList<Habit> todo;


    public HabitTracker() {
        habits = new ArrayList<>();
        completed = new ArrayList<>();
        todo = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a habit to habits, and adds it to todo
    public void addHabit() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: removes habit form todo, and adds habit to completed
    public void markCompleted() {
        //stub
    }

    //MODIFIES: this 
    //EFFECTS: removes habit from completed, and adds habit to todo.
    public void markTodo() {
        //stub
    }
    
    //MODIFIRES: this
    //EFFECTS: remove a habit from all lists
    public void removeHabit() {
        //stub
    }

    public ArrayList<Habit> getAllHabits() {
        return new ArrayList<>();//stub
    }

    public ArrayList<Habit> getCompleted() {
        return new ArrayList<>();//stub
    }

    public ArrayList<Habit> getTodo() {
        return new ArrayList<>();//stub
    }

    

}
