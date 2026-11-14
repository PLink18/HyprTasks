import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Project;
import pll.desktop.hyprtasks.models.Task;
import pll.desktop.hyprtasks.models.User;
import pll.desktop.hyprtasks.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {

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

    @Test
    public void testFindLastRecord() {
        Task task = new Task("LAST", "This is last record");
        Repository.tasks().save(task);
        assertEquals(task.getTitle(), Repository.tasks().findLast().getTitle());

        Project project = new Project("LAST", "This is last project");
        Repository.projects().save(project);
        assertEquals(project.getTitle(), Repository.projects().findLast().getTitle());

        User user = new User("LAST", "12345678", "lastuser@gmail.com");
        Repository.users().save(user);
        assertEquals(user.getUsername(), Repository.users().findLast().getUsername());
    }

    @Test
    public void testSaveRecords() {
        Task oldLastTask = Repository.tasks().findLast();
        Task task = new Task("Test task", "Task for test");
        Repository.tasks().save(task);

        assertNotEquals(oldLastTask, Repository.tasks().findLast());

        Project oldLastProject = Repository.projects().findLast();
        Project project = new Project("Test project", "Project for test");
        Repository.projects().save(project);

        assertNotEquals(oldLastProject, Repository.projects().findLast());

        User oldLastUser = Repository.users().findLast();
        User user = new User("newLastUser", "1111", "imnewlastuser@vot.ru");
        Repository.users().save(user);

        assertNotEquals(oldLastUser, Repository.users().findLast());
    }

    @Test
    public void testUpdateRecord() {
        int id;
        id = Repository.tasks().findLast().getId();
        Task task = new Task(id, "Update", "Updated task");
        Repository.tasks().update(task);

        assertEquals(task.toString(), Repository.tasks().findLast().toString());

        id = Repository.projects().findLast().getId();
        Project project = new Project(id, "Update", "Updated project");
        Repository.projects().update(project);

        assertEquals(project.toString(), Repository.projects().findLast().toString());

        id = Repository.users().findLast().getId();
        User user = new User(id, "updatedUser", "coolPassword", "imupdated@da.su");
        Repository.users().update(user);

        assertEquals(user.toString(), Repository.users().findLast().toString());
    }

    @Test
    public void testDeleteById() {
        int id;

        id = Repository.tasks().findLast().getId();
        Repository.tasks().delete(id);
        assertNotEquals(id, Repository.tasks().findLast().getId());

        id = Repository.projects().findLast().getId();
        Repository.projects().delete(id);
        assertNotEquals(id, Repository.projects().findLast().getId());

        id = Repository.users().findLast().getId();
        Repository.users().delete(id);
        assertNotEquals(id, Repository.users().findLast().getId());
    }
}
