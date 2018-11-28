package views;

import controllers.GridPanelController;
import models.Grid;
import models.Node;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private GridPanelController controller;
    private int cellSize;

    public GridPanel(int cellSize) {
        this.cellSize = cellSize;
        setBackground(Color.white);
        Grid grid = new Grid(1262 / cellSize + 1, 636 / cellSize + 1);
        controller = new GridPanelController(this, grid);
        controller.performAStar();
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
