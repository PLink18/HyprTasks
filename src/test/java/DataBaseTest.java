import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.DataBaseConnection;
import pll.desktop.hyprtasks.DatabaseTable;
import pll.desktop.hyprtasks.Task;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {

    @Test
    public void testConnection() {
        try(Connection connection = DataBaseConnection.getConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInsertRecords() {
        Task task = new Task(0, "Test task", "Task for test");
        DatabaseTable databaseTable = new DatabaseTable();
        int startRecordsCount = databaseTable.readAllRecords("tasks").size();
        databaseTable.insertRecords("tasks", task.getTitle(), task.getDescription());
        assertEquals(startRecordsCount+1, databaseTable.readAllRecords("tasks").size());
    }

    @Test
    public void testReadAllRecords() {
        DatabaseTable databaseTable = new DatabaseTable();
        assertNotNull(databaseTable.readAllRecords("tasks"));
    }

    @Test
    public void testUpdateRecord() {
        DatabaseTable databaseTable = new DatabaseTable();
        databaseTable.insertRecords("tasks", "UPDATE", "Task for update");
        int id = databaseTable.readAllRecords("tasks").size();
        Task task = new Task(id, "UPDATE", "This is updated task");
        assertTrue(databaseTable.updateRecord(task));
    }

    @Test
    public void testDeleteById() {
        DatabaseTable databaseTable = new DatabaseTable();

        databaseTable.insertRecords("tasks", "DELETE", "Task for delete");
        int id = databaseTable.readAllRecords("tasks").size();
        assertTrue(databaseTable.deleteById("tasks", id));
    }

}
