package pll.desktop.hyprtasks;

import org.gnome.gtk.*;
import org.javagi.base.GErrorException;


public class MainView {

    final String APP_NAME = "pll.desktop.hyprtasks";

    static void main(String[] args) {
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

        Overlay overlay = (Overlay) builder.getObject("overlay");

        window.setVisible(true);
    }

    private static void printHello() {
        System.out.println("Hello World");
    }
}
