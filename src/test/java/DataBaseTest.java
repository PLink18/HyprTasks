import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.DataBase;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {

    @Test
    public void testConnection() throws SQLException {
        DataBase db = new DataBase();
        try(Connection connection = db.getNewConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        }
    }

}
