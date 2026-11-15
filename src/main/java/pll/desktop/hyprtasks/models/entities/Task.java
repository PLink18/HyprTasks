package pll.desktop.hyprtasks.models.entities;

import pll.desktop.hyprtasks.models.entities.lookup.Tag;

import java.util.Date;
import java.util.List;

public class Task implements Entity {

    private int id;
    private String title;
    private String description;
    private int priority;
    private int status;
    private int project;
    private Date creationDate;
    private Date updateDate;
    private Date closingDate;
    private List<Tag> tags;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Task(String title, String description) {
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + ", title=" + title + ", description=" + description + "}";
    }

    @Override
    public String toStringWithoutId() {
        return "Task{title=" + title + ", description=" + description + "}";
    }
}
