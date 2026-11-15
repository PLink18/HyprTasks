package pll.desktop.hyprtasks.models.entities.lookup;

import pll.desktop.hyprtasks.models.entities.Entity;

public abstract class Lookup implements Entity {

    protected int id;
    protected String name;

    public Lookup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Lookup(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toStringWithoutId() {
        return "Reference{name="+ name + "}";
    }
}
