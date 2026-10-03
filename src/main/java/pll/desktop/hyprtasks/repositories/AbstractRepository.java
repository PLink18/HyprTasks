package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "SELECT * FROM Tasks";

        List<T> entity = new ArrayList<>();

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                entity.add(mapRow(resultSet));
            }

            return entity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T findLast() {
        String sql = "SELECT * FROM (SELECT a.*, max(id) OVER () AS max_id FROM " + tableName() + " a) WHERE id = max_id";

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
    public void save(T entity) {

    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM " + tableName() + " WHERE id=" + id;

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T mapRow(ResultSet resultSet) {
        return null;
    }
}
