package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractRepository<T> implements CrudRepository<T>{

    public String tableName() {
        return null;
    }

    @Override
    public T findById(int id) {
        String sql = "SELECT * FROM " + tableName() + " WHERE id=" + id;

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
    public List<T> findAll() {
        return List.of();
    }

    @Override
    public T findLast() {
        return null;
    }

    @Override
    public void save(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public T mapRow(ResultSet resultSet) {
        return null;
    }
}
