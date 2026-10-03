package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.models.Project;

import java.sql.ResultSet;

public class ProjectsRepository extends AbstractRepository<Project>{

    @Override
    public String tableName() {
        return "Projects";
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
