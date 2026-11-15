package pll.desktop.hyprtasks.models.entities;

public class ProjectMember implements Entity {
    private int id;
    private int userId;
    private int projectId;
    private int roleId;

    public ProjectMember(int id, int userId, int projectId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.roleId = roleId;
    }

    public ProjectMember(int userId, int projectId, int roleId) {
        this.userId = userId;
        this.projectId = projectId;
        this.roleId = roleId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "ProjectMember{id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", roleId=" + roleId + "}";
    }

    @Override
    public String toStringWithoutId() {
        return "ProjectMember{userId=" + userId +
                ", projectId=" + projectId +
                ", roleId=" + roleId + "}";
    }
}
