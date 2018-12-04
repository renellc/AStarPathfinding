import views.ApplicationFrame;

public class Application {

    public static int WINDOW_WIDTH = 1280;
    public static int WINDOW_HEIGHT = 720;
    public static int GRID_CELL_SIZE = 25;
    public static int HEIGHT_OF_CONTROL_PANEL = 65;

    public static void main(String[] args) {
        ApplicationFrame frame = new ApplicationFrame(WINDOW_WIDTH, WINDOW_HEIGHT, GRID_CELL_SIZE,
                HEIGHT_OF_CONTROL_PANEL);
        frame.setVisible(true);
    }
}
