package pll.desktop.hyprtasks.models.references;

public class Role extends Reference {

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
