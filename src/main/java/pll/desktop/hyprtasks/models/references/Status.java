package pll.desktop.hyprtasks.models.references;

public class Status extends Reference {

    public Status(int id, String name) {
        super(id, name);
    }

    public Status(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Status{id=" + id + ", name=" + name + "}";
    }

    @Override
    public String toStringWithoutId() {
        return "Status{name=" + name + "}";
    }
}
