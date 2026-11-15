package pll.desktop.hyprtasks.models.entities.lookup;

public class Role extends Lookup {

    public Role(int id, String name) {
        super(id, name);
    }

    public Role(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Role{id=" + id + ", name='" + name + "'}";
    }

    @Override
    public String toStringWithoutId() {
        return "Role{name=" + name + "}";
    }
}
