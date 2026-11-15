package pll.desktop.hyprtasks.models.entities.lookup;

public class Tag extends Lookup {

    public Tag(int id, String name) {
        super(id, name);
    }

    public Tag(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Tag{id=" + id + ", name=" + name + "}";
    }

    @Override
    public String toStringWithoutId() {
        return "Tag{name=" + name + "}";
    }
}
