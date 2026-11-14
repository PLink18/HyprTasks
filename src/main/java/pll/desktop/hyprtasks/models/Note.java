package pll.desktop.hyprtasks.models;

public class Note implements Entity {

    private int id;
    private String title;
    private String content;
    private int userId;
    private int taskId;

    public Note(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.content = description;
    }

    public Note(String title, String description) {
        this.title = title;
        this.content = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "Note{id=" + id +
                ", title=" + title +
                ", description=" + content +
                ", userId=" + userId +
                ", taskId=" + taskId + "}";
    }

    @Override
    public String toStringWithoutId() {
        return "Note{title=" + title +
                ", description=" + content +
                ", userId=" + userId +
                ", taskId=" + taskId + "}";
    }
}
