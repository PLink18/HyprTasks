package pll.desktop.hyprtasks.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.ToggleButton;

import java.util.function.Consumer;

public class NavigationController {

    private Consumer<String> onViewChanged;

    public void setOnViewChanged(Consumer<String> action) {
        this.onViewChanged = action;
    }

    public void relocate(ActionEvent event) {
        if (onViewChanged != null) {
            ToggleButton button = (ToggleButton) event.getSource();
            onViewChanged.accept(button.getId());
        }

    }
}
