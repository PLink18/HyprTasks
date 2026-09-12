import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.DataBaseConnection;
import pll.desktop.hyprtasks.DataBaseController;
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
        DataBaseController dataBaseController = new DataBaseController();
        int startRecordsCount = dataBaseController.readAllRecords("tasks").size();
        dataBaseController.insertRecords("tasks", task.getTitle(), task.getDescription());
        assertEquals(startRecordsCount+1, dataBaseController.readAllRecords("tasks").size());
    }

    @Test
    public void testUpdateRecord() {
        Task task = new Task(1, "UPDATE", "This is updated task");
        DataBaseController dataBaseController = new DataBaseController();
        assertTrue(dataBaseController.updateRecord(task));
    }

}
