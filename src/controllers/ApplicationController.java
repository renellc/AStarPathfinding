package controllers;

import models.GridActions;
import models.Node;

public class ApplicationController {
    /**
     * The GridPanelController
     */
    private GridPanelController gridPanelController;

    /**
     * The size of each cell in the grid.
     */
    private int gridCellSize;

    /**
     * Creates a new controller for the main Application frame.
     *
     * @param gridPanelController The controller for the grid panel.
     */
    public ApplicationController(GridPanelController gridPanelController, int gridCellSize) {
        this.gridPanelController = gridPanelController;
        this.gridCellSize = gridCellSize;
    }

    /**
     * Applies a given grid action to the grid.
     *
     * @param action The grid action to apply to the grid.
     * @param x      The x coordinate of the clicked position of the mouse on the grid.
     * @param y      The y coordinate of the clicked position of the mouse on the grid.
     */
    public void applyGridAction(GridActions action, int x, int y) {
        Node currentNode = gridPanelController.getNode(x / gridCellSize, y / gridCellSize - 1);
        switch (action) {
            case PLACE_START:
                gridPanelController.setStart(currentNode);
                break;
            case PLACE_END:
                gridPanelController.setGoal(currentNode);
                break;
            case PLACE_OBSTACLE:
                gridPanelController.setObstacle(currentNode);
                break;
            case REMOVE:
                gridPanelController.resetNode(currentNode);
                break;
        }
    }

    /**
     * Performs the A* path finding search.
     */
    public void performAStarSearch() {
        gridPanelController.performAStar();
    }

    /**
     * Clears the grid so that every cell is white.
     */
    public void clearGrid() {
        gridPanelController.resetGrid();
    }
}
