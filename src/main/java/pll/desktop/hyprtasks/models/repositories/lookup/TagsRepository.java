package pll.desktop.hyprtasks.models.repositories.lookup;

import pll.desktop.hyprtasks.models.entities.lookup.Lookup;
import pll.desktop.hyprtasks.models.entities.lookup.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsRepository extends LookupRepository {
    @Override
    public String tableName() {
        return "Tags";
    }

    @Override
    public Lookup mapRow(ResultSet resultSet) {
        try {
            return new Tag(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
