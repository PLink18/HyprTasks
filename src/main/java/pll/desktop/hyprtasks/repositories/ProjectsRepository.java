package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsRepository extends AbstractRepository<Project>{

    @Override
    public String tableName() {
        return "Projects";
    }

    @Override
    public List<Project> findAll() {
        String sql = "SELECT * FROM Projects";

        List<Project> projects = new ArrayList<>();

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                projects.add(mapRow(resultSet));
            }

            return projects;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Project findLast() {
        return null;
    }

    @Override
    public void save(Project entity) {

    }

    @Override
    public void update(Project entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Project mapRow(ResultSet resultSet) {
        return null;
    }
}
