package ui;

import model.HabitTracker;

import java.util.Scanner;

//Habit Tracker Application
public class HabitTrackerApp {
    private Scanner input;
    private HabitTracker habits;
    private int habitIndex;
    private boolean keeprunning = true;

    //EFFECTS: Runs HabitTracker App
    public HabitTrackerApp() {
        runHabitTracker();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    public void runHabitTracker() {
        int command = 0;

        init();
        while (keeprunning) {
        startScreen();
        command = input.nextInt();
        processMenuCommand(command);
        }
        
        
    }

    //MODIFIES: this
    //EFFECTS: processes user commands for start screen
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
                displayOverallProgress();
                break;
            case 6:
                newDay();
                break;
            case 7:
                quitApp();
                break;    
            default:
                System.out.println("Invalid input. Please selected again.");
        }
    }

    //EFFECTS: shows all options available to user
    public void startScreen() {
        System.out.println("\nWelcome to you personal habit tracker!\\n" + "Please select one of the options below\n");
        System.out.println("\n\t1. View habits");
        System.out.println("\t2. View TODO habits");
        System.out.println("\t3. View completed habits");
        System.out.println("\t4. Create new habit");
        System.out.println("\t5. Get progress report");
        System.out.println("\t6. New day (testing purposes)");
        System.out.println("\t7. Quit");
    }

    //EFFECTS: intitializes the HabitTracker
    public void init() {
        habits = new HabitTracker();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }

    //EFFECTS: displays the options available for one habit 
    public void habitOptions() {
        //stub
        //TODO: Options: 1. Record Habit 2. Edit Habit       
    }

    //MODIFIES: this
    //EFFECTS: process users commands for habit options.
    public void processHabitCommand() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: displays a habits editable attributes
    public void showEditAttributes() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: user edits a habits attributes
    public void processEditCommands() {
        //stub
    }

    //EFFECTS: shows a players overall progress on their habits.
    public void displayOverallProgress() {
        //stub
    }

    //EFFECTS: displays list of all habits, one habit at a time
    public void displayAllHabits() {
        //stub
    }

    //EFFECTS: displays list of completed habits
    public void displayCompleted() {
        //stub
    }


    //EFFECTS: displays list of todo habits
    public void displayTodo() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: Records a users completion of a habit
    public void recordHabit() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: creates a new habit based on user specifications
    public void newHabit() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: advances day for testing purposes
    public void newDay() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: turns keepRunning field to false, and prints final message
    public void quitApp() {
        //stub
    }
    
}
