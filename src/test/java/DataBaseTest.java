import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.DatabaseHelper;
import pll.desktop.hyprtasks.Task;

import java.sql.Connection;
import java.sql.SQLException;

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
        assertNotNull(DatabaseHelper.readAllRecords("Tasks"));
    }

    @Test
    public void testInsertRecords() {
        Task task = new Task(0, "Test task", "Task for test");
        int startRecordsCount = DatabaseHelper.readAllRecords("Tasks").size();
        DatabaseHelper.insertRecords("Tasks", task.getTitle(), task.getDescription());
        assertEquals(startRecordsCount+1, DatabaseHelper.readAllRecords("Tasks").size());
    }

    @Test
    public void testUpdateRecord() {
        DatabaseHelper.insertRecords("Tasks", "UPDATE", "Task for update");
        int id = DatabaseHelper.readAllRecords("Tasks").size();
        Task task = new Task(id, "UPDATE", "This is updated task");
        assertTrue(DatabaseHelper.updateRecord(task));
    }

    @Test
    public void testDeleteById() {
        DatabaseHelper.insertRecords("Tasks", "DELETE", "Task for delete");
        int id = DatabaseHelper.readAllRecords("Tasks").size();
        assertTrue(DatabaseHelper.deleteById("Tasks", id));
    }
}
