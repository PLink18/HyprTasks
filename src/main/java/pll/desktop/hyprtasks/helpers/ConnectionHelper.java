package pll.desktop.hyprtasks.helpers;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {

    private static final HikariDataSource dataSource;

    static {
        Properties properties = new Properties();

        try (InputStream in = ConnectionHelper.class.getClassLoader().getResourceAsStream("properties/db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));

        config.setMaximumPoolSize(5);
        config.setMinimumIdle(1);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
