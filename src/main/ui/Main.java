package ui;

import java.io.FileNotFoundException;

import model.HabitTracker;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to my project!");
        try {
            HabitTracker tracker = new HabitTracker();
            Controller controller = new Controller(tracker);

            new GUI(controller);
            new HabitTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
