package pll.desktop.hyprtasks.models.entities.lookup;

public class Priority extends Lookup {

    public Priority(int id, String name) {
        super(id, name);
    }

    public Priority(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Priority{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public String toStringWithoutId() {
        return "Priority{name=" + name + "}";
    }
}
