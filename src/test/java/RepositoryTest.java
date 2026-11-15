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

    static Stream<Arguments> provideEntitiesAndRepositories() {
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

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testFindByID(Entity entity, Repository<Entity> repository) {
        assertEquals(2, repository.findById(2).getId());
    }

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testFindAllRecords(Entity entity, Repository<Entity> repository) {
        assertNotNull(repository.findAll());
    }

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testFindLastRecord(Entity entity, Repository<Entity> repository) {
        repository.save(entity);
        assertEquals(entity.toStringWithoutId(), repository.findLast().toStringWithoutId());
    }

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testSaveRecords(Entity entity, Repository<Entity> repository) {
        Entity oldEntity = repository.findLast();
        repository.save(entity);
        assertNotEquals(oldEntity.toString(), repository.findLast().toString());
    }

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testUpdateRecord(Entity entity, Repository<Entity> repository) {
        int id = repository.findLast().getId();
        entity.setId(id);
        repository.update(entity);
        assertEquals(entity.toString(), repository.findLast().toString());
    }

    @ParameterizedTest
    @MethodSource("provideEntitiesAndRepositories")
    public void testDeleteById(Entity entity, Repository<Entity> repository) {
        int id = repository.findLast().getId();
        repository.delete(id);
        assertNull(repository.findById(id));
    }
}
