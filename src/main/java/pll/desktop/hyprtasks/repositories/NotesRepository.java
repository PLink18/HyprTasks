package pll.desktop.hyprtasks.repositories;

import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotesRepository extends Repository<Note> {
    @Override
    public String tableName() {
        return "Notes";
    }

    @Override
    public void save(Note entity) {
        String sql = "INSERT INTO " + tableName() + " (title, content) VALUES (?, ?)";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getContent());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Note entity) {
        String sql = "UPDATE " + tableName() + " SET title = ?, content = ? WHERE id = ?";
        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getContent());
            preparedStatement.setInt(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Note mapRow(ResultSet resultSet) {
        try {
            return new Note(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("content")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
