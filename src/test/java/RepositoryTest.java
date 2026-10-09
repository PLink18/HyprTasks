import org.junit.jupiter.api.Test;
import pll.desktop.hyprtasks.helpers.ConnectionHelper;
import pll.desktop.hyprtasks.models.Project;
import pll.desktop.hyprtasks.models.Task;
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
    public void testFindByID() {
        assertEquals(2, Repository.tasks().findById(2).getId());
        assertEquals(2, Repository.projects().findById(2).getId());
    }

    @Test
    public void testFindAllRecords() {
        assertNotNull(Repository.tasks().findAll());
        assertNotNull(Repository.projects().findAll());
    }

    @Test
    public void testFindLastRecord() {
        Task task = new Task("LAST", "This is last record");
        Repository.tasks().save(task);
        assertEquals(task.getTitle(), Repository.tasks().findLast().getTitle());

        Project project = new Project("LAST", "This is last project");
        Repository.projects().save(project);
        assertEquals(project.getTitle(), Repository.projects().findLast().getTitle());
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
    }
}
