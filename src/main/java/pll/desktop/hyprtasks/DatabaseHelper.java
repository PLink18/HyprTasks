package pll.desktop.hyprtasks;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHelper {

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (InputStream in = DatabaseHelper.class.getResourceAsStream("/db.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }
    
    public static void insertRecords(String tableName, String title, String description) {
        String sql = "INSERT INTO " + tableName + " (title, description) VALUES (?, ?)";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Task> readAllRecords(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        List<Task> records = new ArrayList<>();

        try (Connection connection = DatabaseHelper.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                records.add(mapRow(resultSet));
            }
            return records;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateRecord(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ? WHERE id = ?";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteById(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (Connection connection = DatabaseHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task mapRow(ResultSet rs) throws SQLException {
        return new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }
}
