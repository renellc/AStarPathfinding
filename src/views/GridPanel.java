package views;

import controllers.GridPanelController;
import models.Grid;
import models.Node;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    /**
     * The controller for this panel.
     */
    private GridPanelController controller;

    /**
     * The size of each cell in the grid.
     */
    private int cellSize;

    /**
     * Creates a new grid panel.
     *
     * @param cellSize The size for each cell in the grid.
     */
    public GridPanel(int cellSize) {
        this.cellSize = cellSize;
        setBackground(Color.white);
    }

    /**
     * Creates a new controller for this panel.
     *
     * @param grid The grid for the application.
     */
    public void createController(Grid grid) {
        controller = new GridPanelController(this, grid);
    }

    /**
     * Gets the controller for this panel.
     *
     * @return The controller for this panel.
     */
    public GridPanelController getController() {
        return controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();
        for (int y = 0; y < getHeight(); y += cellSize) {
            for (int x = 0; x < getWidth(); x += cellSize) {
                g2D.setColor(Color.black);
                g2D.fillRect(x, y, cellSize, cellSize);

                Node currentNode = controller.getNode(x / cellSize, y / cellSize);

                if (currentNode.isObstacle())
                    g2D.setColor(Color.darkGray);
                else if (controller.getStart() != null && controller.getStart() == currentNode)
                    g2D.setColor(Color.green);
                else if (controller.getGoal() != null && controller.getGoal() == currentNode)
                    g2D.setColor(Color.red);
                else if (controller.getPath() != null && controller.getPath().contains(currentNode))
                    g2D.setColor(Color.cyan);
                else if (controller.getVisitedNodes() != null && controller.getVisitedNodes().contains(currentNode))
                    g2D.setColor(Color.orange);
                else
                    g2D.setColor(Color.white);

                g2D.fillRect(x, y, cellSize - 1, cellSize - 1);
            }
        }
    }
}
