package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements CrudRepository<Task> {

    @Override
    public Task findById(int id) {
        String sql = "SELECT * FROM Tasks WHERE id=" + id;

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> findAll() {
        String sql = "SELECT * FROM Tasks";

        List<Task> tasks = new ArrayList<>();

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                tasks.add(mapRow(resultSet));
            }

            return tasks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task findLast() {
        String sql = "SELECT * FROM (SELECT a.*, max(id) OVER () AS max_id FROM Tasks a) WHERE id = max_id";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                return mapRow(resultSet);
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void delete(int id) {
        String sql = "DELETE FROM Tasks WHERE id=" + id;

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

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
