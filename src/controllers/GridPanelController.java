package controllers;

import models.Grid;
import models.Node;
import views.GridPanel;

import java.util.LinkedHashSet;

public class GridPanelController {

    /**
     * The panel that this controller controls.
     */
    private GridPanel panel;

    /**
     * The grid for the application.
     */
    private Grid grid;

    /**
     * The start node and goal node for A* search.
     */
    private Node start, goal;

    /**
     * The path from the start to the goal. If no path is found, this is null.
     */
    private LinkedHashSet<Node> path;

    /**
     * Creates a new GridPanelController.
     *
     * @param panel The panel for this controller to control.
     * @param grid  The grid for the application.
     */
    public GridPanelController(GridPanel panel, Grid grid) {
        this.panel = panel;
        this.grid = grid;
        start = grid.getNode(5, 5);
        goal = grid.getNode(10, 15);
    }

    /**
     * Starts the A* search algorithm.
     */
    public void performAStar() {
        path = grid.performAStar(start, goal);
    }

    /**
     * Resets the grid.
     */
    public void resetGrid() {
        grid.resetGrid();
        path = null;
    }

    /**
     * Gets a node from the grid.
     *
     * @param x The X coordinate of the node.
     * @param y The Y coordinate of the node.
     * @return The node found at (X, Y).
     */
    public Node getNode(int x, int y) {
        return grid.getNode(x, y);
    }

    /**
     * Gets the start node.
     *
     * @return The start node.
     */
    public Node getStart() {
        return start;
    }

    /**
     * Sets a node to be the start node.
     *
     * @param start The new start node.
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * Gets the goal node.
     *
     * @return The goal node.
     */
    public Node getGoal() {
        return goal;
    }

    /**
     * Sets a node as the new goal node.
     *
     * @param goal The new goal node.
     */
    public void setGoal(Node goal) {
        this.goal = goal;
    }

    /**
     * Sets the given node as an obstacle.
     *
     * @param node The node to be set as an obstacle.
     */
    public void setObstacle(Node node) {
        node.setObstacle(true);
    }

    /**
     * Resets the given node's information (G Score, H Score, F Score, which node it came from,
     * etc.).
     *
     * @param node The node to be reset.
     */
    public void resetNode(Node node) {
        if (node.equals(start)) {
            start = null;
        } else if (node.equals(goal)) {
            goal = null;
        }

        node.resetNode();
    }

    /**
     * Gets the visited nodes during the A* search.
     *
     * @return The A* search.
     */
    public LinkedHashSet<Node> getVisitedNodes() {
        return grid.getClosedNodes();
    }

    /**
     * Gets the path from the start to goal node.
     *
     * @return The path from the start to goal node.
     */
    public LinkedHashSet<Node> getPath() {
        return path;
    }
}
