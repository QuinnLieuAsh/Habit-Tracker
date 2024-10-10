package model;

public class SimpleHabit extends Habit {


    public SimpleHabit(String habitName, int target, int intermediates, String rewardMessage) {
        super(habitName, target, intermediates, rewardMessage);
    }

    @Override 
    //MODIFIES: This
    //EFFECTS: increases the progress of habit
    public void recordHabit() {
        progress++;
        remaining--;
    }


}
