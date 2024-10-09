package model;

// Represents a Habit, with a title, a long term goal (# of times recorded), a short-term goals (intermediate milestones), 
// progress (# times habit is completed), a remaining progress (habit recordings left til long goal is accomplished) 
// and a reward message.
public abstract class Habit {
    protected String title;
    protected int longGoal;
    protected int shortGoal;
    protected int progress;
    protected int remaining;
    protected String reward;

    public Habit(String habitName, int target, int intermediates, String rewardMessage) {
        title = habitName;
        longGoal = target;
        shortGoal = longGoal / intermediates; 
        progress = 0;
        remaining = longGoal - progress;
        reward = rewardMessage;
    }

    //MODIFIES: This
    //EFFECTS: increases the progress of habit
    public abstract void recordHabit();

    //MODIFIES: This
    //EFFECTS: resets the habit progress to 0
    public void resetProgress() {
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
