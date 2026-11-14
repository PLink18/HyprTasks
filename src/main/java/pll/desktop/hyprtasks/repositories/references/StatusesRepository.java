package pll.desktop.hyprtasks.repositories.references;

import pll.desktop.hyprtasks.models.references.Reference;
import pll.desktop.hyprtasks.models.references.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusesRepository extends ReferenceRepository {
    @Override
    public String tableName() {
        return "Statuses";
    }

    @Override
    public Reference mapRow(ResultSet resultSet) {
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
