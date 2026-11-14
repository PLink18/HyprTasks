package pll.desktop.hyprtasks.repositories.references;

import pll.desktop.hyprtasks.models.references.Priority;
import pll.desktop.hyprtasks.models.references.Reference;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrioritiesRepository extends ReferenceRepository {
    @Override
    public String tableName() {
        return "Priorities";
    }

    @Override
    public Reference mapRow(ResultSet resultSet) {
        try {
            return new Priority(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
