package pll.desktop.hyprtasks;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.VBox;
import pll.desktop.hyprtasks.models.Task;
import pll.desktop.hyprtasks.repositories.Repository;

public class CreationController {

    public VBox addTaskPanel;

    public TextField titleField;
    public TextArea descriptionField;

    public void createTask() {
        if (isFieldsNotNull()) {
            String title = pullText(titleField);
            String description = pullText(descriptionField);
            Task task = createTask(title, description);
            Repository.tasks().save(task);
        }
        closePanel();
    }

    private boolean isFieldsNotNull() {
        return titleField != null && descriptionField != null;
    }

    private String pullText(TextInputControl source) {
        String text = source.getText();
        source.clear();
        return text;
    }

    private Task createTask(String title, String description) {
        return new Task(title, description);
    }

    public void closePanel() {
        addTaskPanel.setVisible(false);
    }
}
