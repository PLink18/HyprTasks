package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TasksRepository extends Repository<Task> {

    @Override
    public String tableName() {
        return "Tasks";
    }

    @Override
    public void save(Task entity) {
        String sql = "INSERT INTO Tasks (title, description) VALUES (?, ?)";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Task entity) {
        String sql = "UPDATE Tasks SET title = ?, description = ? WHERE id = ?";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getTitle());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task mapRow(ResultSet resultSet) {
        try {
            return new Task(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
