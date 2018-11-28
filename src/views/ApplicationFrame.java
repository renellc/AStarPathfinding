package views;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    private GridPanel gridPanel = new GridPanel(25);
    private ControlPanel controlPanel = new ControlPanel();

    public ApplicationFrame(int width, int height) {
        initializeFrame(width, height);

        gridPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void initializeFrame(int width, int height) {
        setTitle("A* Visualized");
        setSize(width, height);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
