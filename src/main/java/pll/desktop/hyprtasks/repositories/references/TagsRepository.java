package pll.desktop.hyprtasks.repositories.references;

import pll.desktop.hyprtasks.models.references.Reference;
import pll.desktop.hyprtasks.models.references.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsRepository extends ReferenceRepository {
    @Override
    public String tableName() {
        return "Tags";
    }

    @Override
    public Reference mapRow(ResultSet resultSet) {
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
