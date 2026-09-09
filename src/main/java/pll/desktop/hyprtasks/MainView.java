package pll.desktop.hyprtasks;

import org.gnome.gtk.*;
import org.javagi.base.GErrorException;


public class MainView {

    final String APP_NAME = "pll.desktop.hyprtasks";

    public static void main(String[] args) {
        new MainView(args);
    }

    public MainView(String[] args) {
        Application app = new Application(APP_NAME);
        app.onActivate(() -> activate(app));
        app.run(args);
    }

    private void activate(Application app) {
        GtkBuilder builder = new GtkBuilder();
        try {
            builder.addFromFile("src/main/resources/main.xml");
        } catch (GErrorException e) {
            throw new RuntimeException(e);
        }

        Window window = (Window) builder.getObject("window");
        window.setApplication(app);

        Button button = (Button) builder.getObject("button1");
        button.onClicked(MainView::printHello);

        button = (Button) builder.getObject("button2");
        button.onClicked(MainView::printHello);

        button = (Button) builder.getObject("quit");
        button.onClicked(window::destroy);

        window.setVisible(true);
    }

    private static void printHello() {
        System.out.println("Hello World");
    }
}
