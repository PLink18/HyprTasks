package pll.desktop.hyprtasks;

public class Task {

    private int id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;

    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    enum Status {
        TODO,
        IN_PROGRESS,
        DONE
    }

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task:" + id + ", Title: " + title + ", Description: " + description;
    }
}
