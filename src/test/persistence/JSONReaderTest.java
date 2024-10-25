package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import model.Habit;
import model.HabitTracker;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import java.util.List;
import java.io.IOException;

import model.HabitTracker;

//Citation: References test class from JsonSerializationDemo
public class JSONReaderTest extends JSONTest { 

    @Test
    public void testReaderNonExistentFile() {
        JSONReader reader = new JSONReader("./data/noSuchFile.json");
        try {
            HabitTracker ht = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyWorkRoom() {
        JSONReader reader = new JSONReader("./data/testReaderEmptyHabitTracker.json");
        try {
            HabitTracker ht = reader.read();
            assertEquals(0, ht.getAllHabits().size());
            assertEquals(0, ht.getTodo().size());
            assertEquals(0, ht.getCompleted().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JSONReader reader = new JSONReader("./data/testReaderGeneralHabitTracker.json");
        try {
            HabitTracker ht = reader.read();
            List<Habit> habs = ht.getAllHabits();
            List<Habit> todo = ht.getTodo();
            List<Habit> comp = ht.getCompleted();
            assertEquals(2, habs.size());
            assertEquals(1, todo.size());
            assertEquals(1, comp.size()); 
            checkHabit("run", 30, 5, 7, 23, "zoom", habs.get(0)); 
            checkHabit("study", 30, 6, 10, 20, "yay", habs.get(1)); 
            checkHabit("run", 30, 5, 7, 23, "zoom", todo.get(0)); 
            checkHabit("study", 30, 6, 10, 20, "yay", comp.get(0)); 
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
