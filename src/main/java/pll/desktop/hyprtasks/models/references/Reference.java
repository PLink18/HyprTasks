package pll.desktop.hyprtasks.models.references;

import pll.desktop.hyprtasks.models.Entity;

public abstract class Reference implements Entity {

    protected int id;
    protected String name;

    public Reference(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Reference(String name) {
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
