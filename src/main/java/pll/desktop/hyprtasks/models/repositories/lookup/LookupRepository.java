package pll.desktop.hyprtasks.models.repositories.lookup;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.entities.lookup.Lookup;
import pll.desktop.hyprtasks.models.repositories.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class LookupRepository extends Repository<Lookup> {
    @Override
    public String tableName() {
        return "Reference";
    }

    @Override
    public void save(Lookup entity) {
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
    public void update(Lookup entity) {
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
    public Lookup mapRow(ResultSet resultSet) {
        return null;
    }
}
