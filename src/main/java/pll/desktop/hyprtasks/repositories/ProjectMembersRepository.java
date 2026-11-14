package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.ProjectMember;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectMembersRepository extends Repository<ProjectMember> {
    @Override
    public String tableName() {
        return "Project_Members";
    }

    @Override
    public void save(ProjectMember entity) {
        String sql = "INSERT INTO " + tableName() + " (user_id, project_id, role_id) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getProjectId());
            preparedStatement.setInt(3, entity.getRoleId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ProjectMember entity) {
        String sql = "UPDATE " + tableName() + " SET user_id = ?, project_id = ?, role_id = ? WHERE id = ?";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setInt(2, entity.getProjectId());
            preparedStatement.setInt(3, entity.getRoleId());
            preparedStatement.setInt(4, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProjectMember mapRow(ResultSet resultSet) {
        try {
            return new ProjectMember(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getInt("project_id"),
                    resultSet.getInt("role_id")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
