package persistence;

import model.Habit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONTest {
    protected void checkHabit(String title, int longGoal, int shortGoal, int progress, int remaining, String reward, Habit habit) {
        assertEquals(title, habit.getTitle());
        assertEquals(longGoal, habit.getLongGoal());
        assertEquals(shortGoal, habit.getShortGoal());
        assertEquals(progress, habit.getProgress());
        assertEquals(remaining, habit.getRemainingProgress());
        assertEquals(reward, habit.getReward());
    }

}