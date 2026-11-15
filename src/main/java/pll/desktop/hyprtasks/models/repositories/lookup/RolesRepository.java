package pll.desktop.hyprtasks.models.repositories.lookup;

import pll.desktop.hyprtasks.models.entities.lookup.Lookup;
import pll.desktop.hyprtasks.models.entities.lookup.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesRepository extends LookupRepository {
    @Override
    public String tableName() {
        return "Roles";
    }

    @Override
    public Lookup mapRow(ResultSet resultSet) {
        try {
            return new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
