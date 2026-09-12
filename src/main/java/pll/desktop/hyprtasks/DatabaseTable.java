package pll.desktop.hyprtasks;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

    public DatabaseTable() {
        createTables();
    }

    public void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR, " +
                "description VARCHAR, " +
                "deadline DATE, " +
                "priority INT, " +
                "status INT, " +
                "tags INT )";
        try {
            Connection connection = DataBaseConnection.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRecords(String tableName, String title, String description) {
        String sql = "INSERT INTO " + tableName + " (title, description) VALUES (?, ?)";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
            System.out.println("Record inserted successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> readAllRecords(String tableName) {
        String sql = "SELECT * FROM " + tableName;
        List<Task> records = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection()){
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

    public boolean updateRecord(Task task) {
        String sql = "UPDATE tasks SET title = ?, description = ? WHERE id = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Task mapRow(ResultSet rs) throws SQLException {
        return new Task(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }
}
