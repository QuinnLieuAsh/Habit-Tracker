package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import model.Habit;
import model.HabitTracker;
import model.SimpleHabit;

import org.junit.Test;
import java.util.List;
import java.io.IOException;

//Citation: References test class from JsonSerializationDemo
public class JSONWriterTest extends JSONTest {
    
@Test
public void testWriterInvalidFile() {
        try {
            HabitTracker ht = new HabitTracker();
            JSONWriter writer = new JSONWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyWorkroom() {
        try {
            HabitTracker ht = new HabitTracker();
            JSONWriter writer = new JSONWriter("./data/testWriterEmptyHabitTracker.json");
            writer.open();
            writer.write(ht);
            writer.close();

            JSONReader reader = new JSONReader("./data/testWriterEmptyHabitTracker.json");
            ht = reader.read();
            assertEquals(0, ht.getAllHabits().size());
            assertEquals(0, ht.getTodo().size());
            assertEquals(0, ht.getCompleted().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralWorkroom() {
        try {
            HabitTracker ht = new HabitTracker();
            Habit h1 = new SimpleHabit("run", 30, 6, "zoom");
            Habit h2 = new SimpleHabit("study", 30, 5, "yay");
            ht.addHabit(h1);
            ht.addHabit(h2);   
            JSONWriter writer = new JSONWriter("./data/testWriterGeneralHabitTracker.json");
            writer.open();
            writer.write(ht);
            writer.close();

            JSONReader reader = new JSONReader("./data/testWriterGeneralHabitTracker.json");
            ht = reader.read();
            List<Habit> habs = ht.getAllHabits();
            List<Habit> todo = ht.getTodo();
            List<Habit> comp = ht.getCompleted();
            assertEquals(2, habs.size());
            assertEquals(1, todo.size());
            assertEquals(1, comp.size()); 
            checkHabit("run", 30, 5, 0, 30, "zoom", habs.get(0)); 
            checkHabit("study", 30, 6, 0, 30, "yay", habs.get(1)); 
            checkHabit("run", 30, 5, 0, 30, "zoom", todo.get(0)); 
            checkHabit("study", 30, 6, 0, 30, "yay", comp.get(0)); 

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
