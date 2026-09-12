package pll.desktop.hyprtasks;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnection {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (InputStream in = DataBaseConnection.class.getResourceAsStream("/db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
}
