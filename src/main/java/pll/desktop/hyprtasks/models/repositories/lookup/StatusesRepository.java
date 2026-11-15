package pll.desktop.hyprtasks.models.repositories.lookup;

import pll.desktop.hyprtasks.models.entities.lookup.Lookup;
import pll.desktop.hyprtasks.models.entities.lookup.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusesRepository extends LookupRepository {
    @Override
    public String tableName() {
        return "Statuses";
    }

    @Override
    public Lookup mapRow(ResultSet resultSet) {
        try {
            return new Status(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
