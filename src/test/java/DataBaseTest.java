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
        DatabaseTable databaseTable = DatabaseTable.tasks();
        int startRecordsCount = databaseTable.readAllRecords("Tasks").size();
        databaseTable.insertRecords("Tasks", task.getTitle(), task.getDescription());
        assertEquals(startRecordsCount+1, databaseTable.readAllRecords("Tasks").size());
    }

    @Test
    public void testReadAllRecords() {
        DatabaseTable databaseTable = DatabaseTable.tasks();
        assertNotNull(databaseTable.readAllRecords("Tasks"));
    }

    @Test
    public void testUpdateRecord() {
        DatabaseTable databaseTable = DatabaseTable.tasks();
        databaseTable.insertRecords("Tasks", "UPDATE", "Task for update");
        int id = databaseTable.readAllRecords("Tasks").size();
        Task task = new Task(id, "UPDATE", "This is updated task");
        assertTrue(databaseTable.updateRecord(task));
    }

    @Test
    public void testDeleteById() {
        DatabaseTable databaseTable = DatabaseTable.tasks();

        databaseTable.insertRecords("Tasks", "DELETE", "Task for delete");
        int id = databaseTable.readAllRecords("Tasks").size();
        assertTrue(databaseTable.deleteById("Tasks", id));
    }

}
