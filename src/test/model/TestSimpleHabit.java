package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSimpleHabit {
    private Habit testHabit;
    
    @BeforeEach
    void runBefore() {
        testHabit = new SimpleHabit("Study", 30, 7, "Academic Weapon!");
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
    void testRecordOneHabit() {
        testHabit.recordHabit();
        assertEquals(1, testHabit.getProgress());
        assertEquals(29, testHabit.getRemainingProgress()); 
    }

    @Test
    void testRecordMultipleHabit() {
        testHabit.recordHabit();
        testHabit.recordHabit();
        testHabit.recordHabit();
        assertEquals(3, testHabit.getProgress());
        assertEquals(27, testHabit.getRemainingProgress()); 
    }

    @Test
    void testResetProgress() {
        testHabit.recordHabit();
        testHabit.recordHabit();
        testHabit.recordHabit();
        assertEquals(3, testHabit.getProgress());
        assertEquals(27, testHabit.getRemainingProgress());
        testHabit.resetProgress();
        assertEquals(0, testHabit.getProgress());
        assertEquals(30, testHabit.getRemainingProgress());
    }

    

}
