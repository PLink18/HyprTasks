package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.repositories.references.PrioritiesRepository;
import pll.desktop.hyprtasks.repositories.references.RolesRepository;
import pll.desktop.hyprtasks.repositories.references.StatusesRepository;
import pll.desktop.hyprtasks.repositories.references.TagsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<T> implements CrudRepository<T>{

    public abstract String tableName();

    public static TasksRepository tasks() {
        return new TasksRepository();
    }
    public static ProjectsRepository projects() {
        return new ProjectsRepository();
    }
    public static UsersRepository users() {
        return new UsersRepository();
    }
    public static PrioritiesRepository priorities() {
        return new PrioritiesRepository();
    }
    public static RolesRepository roles() {
        return new RolesRepository();
    }
    public static StatusesRepository statuses() {
        return new StatusesRepository();
    }
    public static TagsRepository tags() {
        return new TagsRepository();
    }
    public static NotesRepository notes() {
        return new NotesRepository();
    }
    public static ProjectMembersRepository projectMembers() {
        return new ProjectMembersRepository();
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
        String sql = "SELECT * FROM " + tableName();

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
    public abstract void save(T entity);

    @Override
    public abstract void update(T entity);

    @Override
    public abstract T mapRow(ResultSet resultSet);
}
