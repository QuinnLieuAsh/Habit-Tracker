package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleHabitTest {
    private Habit testHabit;
    private Habit testHabit2;
    
    @BeforeEach
    void runBefore() {
        testHabit = new SimpleHabit("Study", 30, 7, "Academic Weapon!");
        testHabit2 = new SimpleHabit("Study", 1, 1, "Academic Weapon!");
    }

    @Test
    void testConstructor() {
        assertEquals("Study", testHabit.getTitle());
        assertEquals(30, testHabit.getLongGoal());
        assertEquals(4, testHabit.getShortGoal());
        assertEquals(0, testHabit.getProgress());
        assertEquals(30, testHabit.getRemainingProgress());
        assertEquals("Academic Weapon!", testHabit.getReward());

    }

    @Test
    void testAddOneProgress() {
        testHabit.addProgress();
        assertEquals(1, testHabit.getProgress());
        assertEquals(29, testHabit.getRemainingProgress()); 
    }

    @Test
    void testAddMultiProgress() {
        testHabit.addProgress();
        testHabit.addProgress();
        testHabit.addProgress();
        assertEquals(3, testHabit.getProgress());
        assertEquals(27, testHabit.getRemainingProgress()); 
    }

    @Test
    void testResetProgress() {
        testHabit.addProgress();
        testHabit.addProgress();
        testHabit.addProgress();
        assertEquals(3, testHabit.getProgress());
        assertEquals(27, testHabit.getRemainingProgress());
        testHabit.resetProgress();
        assertEquals(0, testHabit.getProgress());
        assertEquals(30, testHabit.getRemainingProgress());
    }

    @Test
    void testCompleteHabit() {
        testHabit2.addProgress();
        
        assertEquals(1, testHabit2.getProgress());
        assertEquals(0, testHabit2.getRemainingProgress());
        testHabit2.addProgress();
        assertEquals(1, testHabit2.getProgress());
        assertEquals(0, testHabit2.getRemainingProgress());
    }
    

}
