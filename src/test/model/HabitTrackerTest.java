package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HabitTrackerTest {
    private HabitTracker testTracker;
    private Habit habit1;
    private Habit habit2;
    private Habit habit3;
    private Habit habit4;


    @BeforeEach
    void runBefore() {
        testTracker = new HabitTracker();
        habit1 = new SimpleHabit("study", 30, 2, "yay");
        habit2 = new SimpleHabit("run", 30, 2, "speedy");
        habit3 = new SimpleHabit("sleep", 30, 2, "zzz");
        habit4 = new SimpleHabit("sleep", 1, 1, "ya");

    }

    @Test 
    void testConstructor() {
        assertEquals(0, testTracker.getAllHabits().size());
        assertEquals(0, testTracker.getCompleted().size());
        assertEquals(0, testTracker.getTodo().size());
    }

    @Test 
    void testReward() {
        testTracker.addHabit(habit4);
        assertNull(testTracker.reward(habit4));
        testTracker.recordHab(habit4);
        assertEquals("ya", testTracker.reward(habit4));
    }

    @Test
    void testRecordHabit() {
        testTracker.addHabit(habit1);
        assertEquals(1, testTracker.getAllHabits().size());
        assertEquals(1, testTracker.getTodo().size());
        testTracker.recordHab(habit1);
        assertEquals(1, testTracker.getCompleted().size());
        assertEquals(1, habit1.getProgress());
        testTracker.recordHab(habit1);
        assertEquals(1, habit1.getProgress());
    }

    @Test 
    void testAddOneHabit() { 
        testTracker.addHabit(habit1);
        assertEquals(1, testTracker.getAllHabits().size());
        assertEquals(1, testTracker.getTodo().size());
        assertEquals(habit1, testTracker.getAllHabits().get(0));
        assertEquals(habit1, testTracker.getTodo().get(0));
    }

    @Test 
    void testAddMultipleHabits() { 
        testTracker.addHabit(habit1);
        assertEquals(1, testTracker.getAllHabits().size());
        assertEquals(1, testTracker.getTodo().size());
        assertEquals(habit1, testTracker.getAllHabits().get(0));
        assertEquals(habit1, testTracker.getTodo().get(0));
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        assertEquals(3, testTracker.getAllHabits().size());
        assertEquals(3, testTracker.getTodo().size());
        assertEquals(habit2, testTracker.getAllHabits().get(1));
        assertEquals(habit2, testTracker.getTodo().get(1));
        assertEquals(habit3, testTracker.getAllHabits().get(2));
        assertEquals(habit3, testTracker.getTodo().get(2));
    }

    @Test
    void testMarkOneCompleted() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit1);
        assertEquals(2, testTracker.getTodo().size());
        assertEquals(1, testTracker.getCompleted().size());
        assertEquals(habit1, testTracker.getCompleted().get(0));
        assertEquals(habit2, testTracker.getTodo().get(0));
        assertEquals(habit3, testTracker.getTodo().get(1));

    }

    @Test
    void testMarkMultipleCompleted() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit3);
        assertEquals(2, testTracker.getTodo().size());
        assertEquals(1, testTracker.getCompleted().size());
        assertEquals(habit3, testTracker.getCompleted().get(0));
        assertEquals(habit1, testTracker.getTodo().get(0));
        assertEquals(habit2, testTracker.getTodo().get(1));
        testTracker.markCompleted(habit2);
        assertEquals(1, testTracker.getTodo().size());
        assertEquals(2, testTracker.getCompleted().size());
        assertEquals(habit3, testTracker.getCompleted().get(0));
        assertEquals(habit2, testTracker.getCompleted().get(1));
        assertEquals(habit1, testTracker.getTodo().get(0));
        testTracker.markCompleted(habit1);
        assertTrue(testTracker.getTodo().isEmpty());
        assertEquals(3, testTracker.getCompleted().size());
        assertEquals(habit3, testTracker.getCompleted().get(0));
        assertEquals(habit2, testTracker.getCompleted().get(1));
        assertEquals(habit1, testTracker.getCompleted().get(2));
    }


    @Test
    void testMarkOneTodo() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit1);
        testTracker.markTodo(habit1);
        assertEquals(3, testTracker.getTodo().size());
        assertEquals(0, testTracker.getCompleted().size());
        assertEquals(habit2, testTracker.getTodo().get(0));
        assertEquals(habit3, testTracker.getTodo().get(1));
        assertEquals(habit1, testTracker.getTodo().get(2));
    }

    @Test
    void testMarkMultipleTodo() {   
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit1);
        testTracker.markCompleted(habit2);
        testTracker.markCompleted(habit3);
        testTracker.markTodo(habit2);
        testTracker.markTodo(habit1);
        assertEquals(2, testTracker.getTodo().size());
        assertEquals(1, testTracker.getCompleted().size());
        assertEquals(habit3, testTracker.getCompleted().get(0));
        assertEquals(habit2, testTracker.getTodo().get(0));
        assertEquals(habit1, testTracker.getTodo().get(1));
        testTracker.markTodo(habit3);
        assertEquals(3, testTracker.getTodo().size());
        assertEquals(0, testTracker.getCompleted().size());
        assertEquals(habit3, testTracker.getTodo().get(2));
    }

    @Test
    void testRemoveHabit() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit2);
        testTracker.removeHabit(habit1);
        assertEquals(2, testTracker.getAllHabits().size());
        assertEquals(1, testTracker.getCompleted().size());
        assertEquals(1, testTracker.getTodo().size());
        testTracker.removeHabit(habit2);
        assertEquals(1, testTracker.getAllHabits().size());
        assertEquals(0, testTracker.getCompleted().size());
        assertEquals(1, testTracker.getTodo().size());
        testTracker.removeHabit(habit3);
        assertTrue(testTracker.getAllHabits().isEmpty());
    }

    @Test 
    void testResetCompletedHabits() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit2);
        testTracker.markCompleted(habit1);
        testTracker.markCompleted(habit3);
        testTracker.resetCompletedHabits();
        assertEquals(3, testTracker.getTodo().size());
        assertEquals(habit1, testTracker.getTodo().get(0));
        assertEquals(habit2, testTracker.getTodo().get(1));
        assertEquals(habit3, testTracker.getTodo().get(2));
    }


    @Test
    void testDailyResetNewDay() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit2);
        testTracker.markCompleted(habit1);
        testTracker.markCompleted(habit3);
        testTracker.changeDate();
        testTracker.dailyReset();
        assertEquals(3, testTracker.getTodo().size());
        assertEquals(habit1, testTracker.getTodo().get(0));
        assertEquals(habit2, testTracker.getTodo().get(1));
        assertEquals(habit3, testTracker.getTodo().get(2));
        assertEquals(LocalDate.now(), testTracker.getCompletionDate());
        }

        @Test
        void testDailyResetSameDay() {
        testTracker.addHabit(habit1);
        testTracker.addHabit(habit2);
        testTracker.addHabit(habit3);
        testTracker.markCompleted(habit2);
        testTracker.markCompleted(habit1);
        testTracker.markCompleted(habit3);
        testTracker.dailyReset();
        assertEquals(0, testTracker.getTodo().size());
        assertEquals(habit2, testTracker.getCompleted().get(0));
        assertEquals(habit1, testTracker.getCompleted().get(1));
        assertEquals(habit3, testTracker.getCompleted().get(2));
        assertEquals(LocalDate.now(), testTracker.getCompletionDate());
        }
}
