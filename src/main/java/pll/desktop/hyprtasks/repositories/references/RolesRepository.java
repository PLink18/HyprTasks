package pll.desktop.hyprtasks.repositories.references;

import pll.desktop.hyprtasks.models.references.Reference;
import pll.desktop.hyprtasks.models.references.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesRepository extends ReferenceRepository {
    @Override
    public String tableName() {
        return "Roles";
    }

    @Override
    public Reference mapRow(ResultSet resultSet) {
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
