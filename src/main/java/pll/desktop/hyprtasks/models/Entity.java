package pll.desktop.hyprtasks.models;

public interface Entity {
    int getId();
    void setId(int id);
    String toString();
    String toStringWithoutId();
}
