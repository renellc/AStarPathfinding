package views;

import models.Grid;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {

    /**
     * The panel that draws the grid.
     */
    private GridPanel gridPanel;

    /**
     * The panel containing the controls for the grid.
     */
    private ControlPanel controlPanel;

    public ApplicationFrame(int width, int height) {
        initializeFrame(width, height);
        Grid grid = new Grid(1262 / 25 + 1, 636 / 25 + 1);
        gridPanel = new GridPanel(25);
        controlPanel = new ControlPanel();
        gridPanel.createController(grid);
        controlPanel.createController(grid);

        gridPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    /**
     * Initializes the settings for the frame.
     *
     * @param width  The width of the frame.
     * @param height The height of the frame.
     */
    private void initializeFrame(int width, int height) {
        setTitle("A* Visualized");
        setSize(width, height);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
