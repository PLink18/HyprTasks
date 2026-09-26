package pll.desktop.hyprtasks;

import javafx.event.ActionEvent;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

public class MainController {

    public VBox profileView;
    public TabPane mainTabs;
    public VBox notesView;

    public void relocate(ActionEvent event) {
        ToggleButton toggleButton = (ToggleButton) event.getSource();
        changeView(toggleButton.getId());
    }

    private void changeView(String id) {
        switch (id) {
            case "profileButton":
                profileView.setVisible(true);
                mainTabs.setVisible(false);
                notesView.setVisible(false);
                break;
            case "tasksButton":
                profileView.setVisible(false);
                mainTabs.setVisible(true);
                notesView.setVisible(false);
                break;
            case "notesButton":
                profileView.setVisible(false);
                mainTabs.setVisible(false);
                notesView.setVisible(true);
                break;
        }
    }


    public void addItem(ActionEvent event) {

    }

    public void addNote(ActionEvent event) {
    }

    public void saveAll(ActionEvent event) {
    }

    public void closeApp(ActionEvent event) {
    }

    public void showSettings(ActionEvent event) {
    }

    public void showProfile(ActionEvent event) {
    }

    public void showTasks(ActionEvent event) {
    }

    public void showNotes(ActionEvent event) {
    }

    public void showAbout(ActionEvent event) {
    }

    public void showFilters(ActionEvent event) {
    }

    public void refresh(ActionEvent event) {
    }

    public void resetProfile(ActionEvent event) {
    }

    public void saveProfile(ActionEvent event) {
    }

    public void deleteNote(ActionEvent event) {
    }

    public void clearNote(ActionEvent event) {
    }

    public void saveNote(ActionEvent event) {
    }

    public void resetSettings(ActionEvent event) {
    }

    public void saveSettings(ActionEvent event) {
    }
}
