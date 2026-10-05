package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectsRepository extends Repository<Project> {

    @Override
    public String tableName() {
        return "Projects";
    }

    @Override
    public void save(Project entity) {
        String sql = "INSERT INTO Projects (title, description) VALUES (?, ?)";
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
    public void update(Project entity) {
        String sql = "UPDATE Projects SET title = ?, description = ? WHERE id = ?";
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
    public Project mapRow(ResultSet resultSet) {
        try {
            return new Project(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("description")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
