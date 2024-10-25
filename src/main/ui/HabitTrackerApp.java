package ui;

import model.Habit;

import model.HabitTracker;
import model.SimpleHabit;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Habit Tracker Application
public class HabitTrackerApp {
    private static final String JSON_STORE = "./data/habitTracker.json";
    private Scanner input;
    private HabitTracker habits;
    private int habitIndex = 0;
    private boolean keeprunning;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs HabitTracker App
    public HabitTrackerApp() throws FileNotFoundException {
        runHabitTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runHabitTracker() {
        int command;
        keeprunning = true;

        init();
        while (keeprunning) {
            habits.dailyReset();
            startScreen();
            command = input.nextInt();
            processMenuCommand(command);
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user commands for start screen
    @SuppressWarnings("methodlength")
    public void processMenuCommand(int decision) {
        switch (decision) {
            case 1:
                displayAllHabits();
                break;
            case 2:
                displayTodo();
                break;
            case 3:
                displayCompleted();
                break;
            case 4:
                newHabit();
                break;
            case 5:
                newDay();
                break;
            case 6:
                quitApp();
                break;
            case 7:
                saveProgress();
                break;
            case 8:
                loadProgress();
                break;
            default:
                System.out.println("Invalid input. Please selected again.");
        }
    }

    // EFFECTS: shows all options available to user
    public void startScreen() {
        System.out.println("\nWelcome to you personal habit tracker!\n" + "Please select one of the options below\n");
        System.out.println("\n\t1. View habits");
        System.out.println("\t2. View TODO habits");
        System.out.println("\t3. View completed habits");
        System.out.println("\t4. Create new habit");
        System.out.println("\t5. New day (testing purposes)");
        System.out.println("\t6. Quit");
        System.out.println("\t7. Save Habit Tracker to file");
        System.out.println("\t8. Load Habit Tracker from file");
    }

    // EFFECTS: intitializes the HabitTracker
    public void init() {
        habits = new HabitTracker();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: displays habit and the options available for one habit
    public void viewHabit(ArrayList<Habit> habitList) {
        if (habitList.isEmpty()) {
            System.out.println("You have no habits. Please create some.");
            return;
        }

        viewHabitOptions();
        int command = 0;
        Habit currentHabit;
        while (command != 5) {
            currentHabit = habitList.get(habitIndex);
            displayHabitInfo(currentHabit);
            command = input.nextInt();
            processHabitCommand(command, habitList);
        }
        habitIndex = 0;
    }

    // MODIFIES: this
    // EFFECTS: displays habit options.
    public void viewHabitOptions() {
        System.out.println("\nSelect one of the options below");
        System.out.println("\t1. RecordHabit");
        System.out.println("\t2. Next Habit");
        System.out.println("\t3. Previous Habit");
        System.out.println("\t4. Remove Habit");
        System.out.println("\t5. Quit");
    }

    // MODIFIES: this
    // EFFECTS: process users commands for habit options.
    public void processHabitCommand(int decision, ArrayList<Habit> habitList) {
        Habit currentHabit = habitList.get(habitIndex);
        switch (decision) {
            case 1:
                recordHabit(currentHabit);
                break;
            case 2:
                getNextHabit(habitList);
                break;
            case 3:
                getPreviousHabit();
                break;
            case 4:
                deleteHabit(currentHabit);
                break;
            case 5:
                System.out.println("Exiting habit list.");
                break;
            default:
                System.out.println("Invalid input. Please selected again.");
        }
    }

    // EFFECTS: displays list of all habits, one habit at a time
    public void displayAllHabits() {
        ArrayList<Habit> habitList = this.habits.getAllHabits();
        viewHabit(habitList);
    }

    // EFFECTS: displays list of completed habits
    public void displayCompleted() {
        ArrayList<Habit> completedList = this.habits.getCompleted();
        viewCompleted(completedList);
    }

    // EFFECTS: displays Completed habit and the options available for it
    public void viewCompleted(ArrayList<Habit> completedList) {
        if (completedList.isEmpty()) {
            System.out.println("Completed list is empty. Time to be productive!");
            return;
        }
        habitIndex = 0;
        viewCompletedOptions();
        int command = 0;
        Habit currentCompleted;
        while (command != 3) {
            currentCompleted = completedList.get(habitIndex);
            displayHabitInfo(currentCompleted);
            command = input.nextInt();
            processCompletedCommand(command, completedList);
        }
        habitIndex = 0;
    }

    // MODIFIES: this
    // EFFECTS: process users commands for completed list options.
    public void processCompletedCommand(int decision, ArrayList<Habit> completedList) {
        switch (decision) {
            case 1:
                getNextCompleted(completedList);
                break;
            case 2:
                getPreviousHabit();
                break;
            case 3:
                System.out.println("Exiting Completed list.");
                break;
            default:
                System.out.println("Invalid input. Please select again.");

        }
    }

    // MODIFIES: this
    // EFFECTS: Displays completed list options.
    public void viewCompletedOptions() {
        System.out.println("\nSelect one of the options below");
        System.out.println("\t1. Next Habit");
        System.out.println("\t2. Previous Habit");
        System.out.println("\t3. Quit");
    }

    // EFFECTS: displays list of todo habits
    public void displayTodo() {
        ArrayList<Habit> todoList = this.habits.getTodo();
        viewTodo(todoList);
    }

    // EFFECTS: displays todo and the options available for todo
    public void viewTodo(ArrayList<Habit> todoList) {
        if (todoList.isEmpty()) {
            System.out.println("TODO list is empty. YAY!");
            return;
        }
        habitIndex = 0;
        viewTodoOptions();
        int command = 0;
        Habit currentTodo;
        while (command != 3) {
            currentTodo = todoList.get(habitIndex);
            displayHabitInfo(currentTodo);
            command = input.nextInt();
            processTodoCommand(command, todoList);
        }
        habitIndex = 0;
    }

    // MODIFIES: this
    // EFFECTS: process users commands for habit options.
    public void processTodoCommand(int decision, ArrayList<Habit> todoList) {
        switch (decision) {
            case 1:
                getNextTodo(todoList);
                break;
            case 2:
                getPreviousHabit();
                break;
            case 3:
                System.out.println("Exiting TODO list.");
                break;
            default:
                System.out.println("Invalid input. Please select again.");

        }
    }

    // MODIFIES: this
    // EFFECTS: Displays todo options.
    public void viewTodoOptions() {
        System.out.println("\nSelect one of the options below");
        System.out.println("\t1. Next Habit");
        System.out.println("\t2. Previous Habit");
        System.out.println("\t3. Quit");
    }

    // MODIFIES: this
    // EFFECTS: Records a users completion of a habit
    public void recordHabit(Habit habit) {
        if (inCompleted(habit)) {
            System.out.println("You have completed habit today, try again tommorow.");
            return;
        }
        habit.addProgress();
        habitIndex = 0;
        int progress = habit.getProgress();
        int goal = habit.getLongGoal();
        if (progress == goal) {
            System.out.println("\n" + habit.getReward());
        }
        this.habits.markCompleted(habit);
    }

    // EFFECTS: searches completed habits and looks for match
    public boolean inCompleted(Habit habit) {
        for (Habit h : habits.getCompleted()) {
            if (compareHabit(habit, h)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: compares two habits based on main attributes
    public boolean compareHabit(Habit h1, Habit h2) {
        boolean match = h1.getTitle().equals(h2.getTitle())
                && h1.getLongGoal() == h2.getLongGoal()
                && h1.getProgress() == h2.getProgress();

        return match;
    }

    // EFFECTS: displays the title of Habit, Progress, and remaining progress
    public void displayHabitInfo(Habit habit) {
        System.out.println("\nHabit: " + habit.getTitle() + "\nProgress: " + habit.getProgress()
                + "\nDays remaining: " + habit.getRemainingProgress());
    }

    // MODIFIES: this
    // EFFECTS: gets next habit in list if it exists.
    public void getNextHabit(ArrayList<Habit> habitList) {
        if (this.habitIndex >= habits.getAllHabits().size() - 1) {
            System.out.println("No more habits to show.");
        } else {
            habitIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: gets next todo in list if it exists.
    public void getNextTodo(ArrayList<Habit> habitList) {
        if (this.habitIndex >= habits.getTodo().size() - 1) {
            System.out.println("No more habits to show.");
        } else {
            habitIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: gets next completed in list if it exists.
    public void getNextCompleted(ArrayList<Habit> habitList) {
        if (this.habitIndex >= habits.getCompleted().size() - 1) {
            System.out.println("No more habits to show.");
        } else {
            habitIndex++;
        }
    }

    // MODIFIES: this
    // EFFECTS: gets previous habit in list if it exists.
    public void getPreviousHabit() {
        if (this.habitIndex <= 0) {
            System.out.println("No more habits to show.");
        } else {
            habitIndex--;
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a new habit based on user specifications
    public void newHabit() {
        System.out.println("\nEnter how many times you wish to complete the habit.");
        int goal = input.nextInt();
        input.nextLine();
        System.out.println("\nEnter how many intermediate milestones you would like.");
        int numMilestones = input.nextInt();
        input.nextLine();
        System.out.println("Enter you new Habit's name:");
        String name = input.nextLine();

        System.out.println("Enter a reward message for when you reach goals.");
        String rewardMessage = input.nextLine();

        Habit habit = new SimpleHabit(name, goal, numMilestones, rewardMessage);
        this.habits.addHabit(habit);
    }

    // MODIFIES: this
    // EFFECTS: advances day for testing purposes
    public void newDay() {
        System.out.println("Another Day, another doller. Ready to complete some habits?");
        habits.changeDate();
    }

    // MODIFIES: this
    // EFFECTS: turns keepRunning field to false, and prints final message
    public void quitApp() {
        System.out.println("See you next time!");
        System.out.println("NOW GO GET AFTER IT!");

        keeprunning = false;
    }

    // MODIFIES: this
    // EFFECTS: removes habit from all list of habits.
    public void deleteHabit(Habit habit) {
        habits.removeHabit(habit);
        System.out.println("Removing habit. Refresh by returning to menu.");
    }

    // EFFECTS: saves the habit tracker to file
    private void saveProgress() {
        try {
            jsonWriter.open();
            jsonWriter.write(habits);
            jsonWriter.close();
            System.out.println("Saved HabitTracker to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads habit tracker from file
    private void loadProgress() {
        try {
            habits = jsonReader.read();
            System.out.println("Loaded HabitTracker from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
