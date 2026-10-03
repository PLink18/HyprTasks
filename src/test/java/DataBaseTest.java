import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.DatabaseHelper;
import pll.desktop.hyprtasks.Task;
import pll.desktop.hyprtasks.TaskRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {

    @Test
    public void testConnection() {
        try(Connection connection = DatabaseHelper.getConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadAllRecords() {
        TaskRepository repository = new TaskRepository();
        List<Task> tasks = repository.findAll();
        assertNotNull(tasks);
    }

    @Test
    public void testSaveRecords() {
        TaskRepository repository = new TaskRepository();
        Task startLastTask = repository.findLast();
        Task task = new Task("Test task", "Task for test");
        repository.save(task);

        assertNotEquals(startLastTask, repository.findLast());
    }

    @Test
    public void testUpdateRecord() {
        TaskRepository repository = new TaskRepository();
        int id = repository.findLast().getId();
        Task task = new Task(id, "Update", "Updated task");
        repository.update(task);

        assertEquals(task.toString(), repository.findLast().toString());
    }

    @Test
    public void testDeleteById() {
        TaskRepository repository = new TaskRepository();
        int id = repository.findAll().size()+1;
        Task task = new Task("DELETE", "Task for delete");
        repository.save(task);
        repository.delete(id);
        assertTrue(repository.findAll().size() < id);
    }
}
