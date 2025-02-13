package persistence;

import model.Habit;
import model.HabitTracker;
import java.time.LocalDate;

import java.util.List;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Citation: References test class from JsonSerializationDemo
public class JSONReaderTest extends JSONTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HabitTracker ht = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHabitTracker.json");
        try {
            HabitTracker ht = reader.read();
            assertEquals(0, ht.getAllHabits().size());
            assertEquals(0, ht.getTodo().size());
            assertEquals(0, ht.getCompleted().size());
            assertEquals(LocalDate.of(2000, 1, 1), ht.getCompletionDate());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHabitTracker.json");
        try {
            HabitTracker ht = reader.read();
            List<Habit> habs = ht.getAllHabits();
            List<Habit> todo = ht.getTodo();
            List<Habit> comp = ht.getCompleted();
            assertEquals(2, habs.size());
            assertEquals(1, todo.size());
            assertEquals(1, comp.size());
            assertEquals(LocalDate.of(2000, 1, 1), ht.getCompletionDate());
            checkHabit("run", 30, 5, 7, 23, "zoom", habs.get(0));
            checkHabit("study", 30, 6, 10, 20, "yay", habs.get(1));
            checkHabit("run", 30, 5, 7, 23, "zoom", todo.get(0));
            checkHabit("study", 30, 6, 10, 20, "yay", comp.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
