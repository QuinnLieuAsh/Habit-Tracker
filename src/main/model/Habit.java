package model;

// Represents a Habit, with a title, a long term goal (# of times recorded), a number of shortterm goals (intermediate milestones), 
//a list of short term goals, progress (# times habit is completed), and a reward message.
public class Habit {
    private String title;
    private int longGoal;
    private int shortGoal;
    private ArrayList<shortGoal> goals;
    private int progress;
    private String reward;

    public Habit(String habitName, int target, int intermediates, String rewardMessage) {
        title = habitName;
        longGoal = target;
        shortGoal = longGoal / intermediates; 
        progress = 0;
        reward = rewardMessage;
    }

    //MODIFIES: This
    //EFFECTS: increases the progress of habit by 1
    public void recordHabit() {
        //stub
    }

    public String getTitle() {
        return null; //stub
    }

    public int getLongGoal() {
        return null; //stub
    }

    public int getShortGoal() {
        return null; //stub
    }

    public int getProgress() {
        return null; //stub
    }

    public String getReward() {
        return null; //stub
    }

    public int getRemainingProgress() {
        return null; //stub
    }

}
