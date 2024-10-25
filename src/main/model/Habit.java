package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a Habit, with a title, 
//a long term goal (# of times recorded),
// a short-term goals (intermediate milestones), 
// progress (# times habit is completed), a
// remaining progress (habit recordings left til long goal is accomplished) 
// and a reward message.
public abstract class Habit implements Writable {
    protected String title;
    protected int longGoal;
    protected int shortGoal;
    protected int progress;
    protected int remaining;
    protected String reward;

    // REQUIRES: intermediates <= target
    public Habit(String habitName, int target, int intermediates, String rewardMessage) {
        title = habitName;
        longGoal = target;
        shortGoal = longGoal / intermediates;
        progress = 0;
        remaining = longGoal;
        reward = rewardMessage;
    }

    // MODIFIES: This
    // EFFECTS: increases the progress of habit
    public abstract void addProgress();

    // MODIFIES: This
    // EFFECTS: resets the habit progress to 0
    public void resetProgress() {
        progress = 0;
        remaining = longGoal;
    }

    public String getTitle() {
        return title;
    }

    public int getLongGoal() {
        return longGoal;
    }

    public int getShortGoal() {
        return shortGoal;
    }

    public int getProgress() {
        return progress;
    }

    public String getReward() {
        return reward;
    }

    public int getRemainingProgress() {
        return remaining;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("longGoal", longGoal);
        json.put("shortGoal", shortGoal);
        json.put("progress", progress);
        json.put("remaining", remaining);
        json.put("reward", reward);
        return json;
    }

    // MODIFIES: This
    // EFFECTS: sets the Progress of a habit (for data persistence uses)
    public void setProgress(int x) {
        progress = x;
        remaining = longGoal - progress;
    }

}
