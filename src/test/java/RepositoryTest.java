import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.*;
import pll.desktop.hyprtasks.models.references.Priority;
import pll.desktop.hyprtasks.models.references.Role;
import pll.desktop.hyprtasks.models.references.Status;
import pll.desktop.hyprtasks.models.references.Tag;
import pll.desktop.hyprtasks.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

    static Stream<Arguments> provideRepositoriesAndEntities() {
        return Stream.of(
                Arguments.of(
                        new Task("TEST_TASK", "TASK FOR TEST"),
                        Repository.tasks()
                ),
                Arguments.of(
                        new Project("TEST_PROJECT", "PROJECT FOR TEST"),
                        Repository.projects()
                ),
                Arguments.of(
                        new User("TEST_USER", "PASSWORD", "EMAIL"),
                        Repository.users()
                ),
                Arguments.of(
                        new Priority("TEST_PRIORITY"),
                        Repository.priorities()
                ),
                Arguments.of(
                        new Role("TEST_ROLE"),
                        Repository.roles()
                ),
                Arguments.of(
                        new Status("TEST_STATUS"),
                        Repository.statuses()
                ),
                Arguments.of(
                        new Tag("TEST_TAG"),
                        Repository.tags()
                ),
                Arguments.of(
                        new Note("TEST_NOTE", "NOTE FOR TEST"),
                        Repository.notes()
                ),
                Arguments.of(
                        new ProjectMember(1, 1, 1),
                        Repository.projectMembers()
                )
        );
    }

    @Test
    public void testConnection() {
        try(Connection connection = ConnectionHelper.getConnection()) {
            assertTrue(connection.isValid(1));
            assertFalse(connection.isClosed());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testTableNames() {
        assertEquals("Tasks", Repository.tasks().tableName());
        assertEquals("Projects", Repository.projects().tableName());
        assertEquals("Users", Repository.users().tableName());

        assertNotEquals(Repository.tasks().tableName(), Repository.projects().tableName());
    }

    @Test
    public void testFindByID() {
        assertEquals(2, Repository.tasks().findById(2).getId());
        assertEquals(2, Repository.projects().findById(2).getId());
        assertEquals(2, Repository.users().findById(2).getId());
    }

    @Test
    public void testFindAllRecords() {
        assertNotNull(Repository.tasks().findAll());
        assertNotNull(Repository.projects().findAll());
        assertNotNull(Repository.users().findAll());
    }

    @ParameterizedTest
    @MethodSource("provideRepositoriesAndEntities")
    public void testFindLastRecord(Entity entity, Repository<Entity> repository) {
        repository.save(entity);
        assertEquals(entity.toStringWithoutId(), repository.findLast().toStringWithoutId());
    }

    @ParameterizedTest
    @MethodSource("provideRepositoriesAndEntities")
    public void testSaveRecords(Entity entity, Repository<Entity> repository) {
        Entity oldEntity = repository.findLast();
        repository.save(entity);
        assertNotEquals(oldEntity.toString(), repository.findLast().toString());
    }

    @ParameterizedTest
    @MethodSource("provideRepositoriesAndEntities")
    public void testUpdateRecord(Entity entity, Repository<Entity> repository) {
        int id = repository.findLast().getId();
        entity.setId(id);
        repository.update(entity);
        assertEquals(entity.toString(), repository.findLast().toString());
    }

    @ParameterizedTest
    @MethodSource("provideRepositoriesAndEntities")
    public void testDeleteById(Entity entity, Repository<Entity> repository) {
        int id = repository.findLast().getId();
        repository.delete(id);
        assertNull(repository.findById(id));
    }
}
