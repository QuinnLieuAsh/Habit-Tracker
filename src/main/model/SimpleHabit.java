package model;

public class SimpleHabit extends Habit {


    public Habit(String habitName, int target, int intermediates, String rewardMessage) {
        super(habitName, target, intermediates, rewardMessage);
    }

    @Override 
    //MODIFIES: This
    //EFFECTS: increases the progress of habit
    public void recordHabit() {
        this.progress++;
    }


}
