package pll.desktop.hyprtasks.repositories.references;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.references.Reference;
import pll.desktop.hyprtasks.repositories.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceRepository extends Repository<Reference> {
    @Override
    public String tableName() {
        return "Reference";
    }

    @Override
    public void save(Reference entity) {
        String sql = "INSERT INTO " + tableName() + " (name) VALUES (?)";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reference entity) {
        String sql = "UPDATE " + tableName() + " SET name = ? WHERE id = ?";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Reference mapRow(ResultSet resultSet) {
        return null;
    }
}
