package pll.desktop.hyprtasks.models.repositories.lookup;

import pll.desktop.hyprtasks.models.entities.lookup.Priority;
import pll.desktop.hyprtasks.models.entities.lookup.Lookup;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrioritiesRepository extends LookupRepository {
    @Override
    public String tableName() {
        return "Priorities";
    }

    @Override
    public Lookup mapRow(ResultSet resultSet) {
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
