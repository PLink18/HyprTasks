package pll.desktop.hyprtasks.models.entities.lookup;

public class Status extends Lookup {

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
