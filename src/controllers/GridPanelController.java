package controllers;

import models.Grid;
import models.Node;
import views.GridPanel;

import javax.swing.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;

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
     * The node obstacles in the grid.
     */
    private LinkedList<Node> obstacles;

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
        obstacles = new LinkedList<>();
    }

    /**
     * Starts the A* search algorithm.
     */
    public void performAStar() {
        if (start == null && goal == null) {
            JOptionPane.showMessageDialog(panel, "Error! The start and goal node hasn't been set.");
            return;
        }

        if (start == null) {
            JOptionPane.showMessageDialog(panel, "Error! The start node hasn't been set.");
            return;
        }

        if (goal == null) {
            JOptionPane.showMessageDialog(panel, "Error! The goal node hasn't been set.");
            return;
        }

        path = grid.performAStar(start, goal);
    }

    /**
     * Resets the grid.
     */
    public void resetGrid() {
        grid.resetGrid();

        if (path != null) {
            for (Node node : path) {
                node.resetNode();
            }
        }

        if (!obstacles.isEmpty()) {
            for (Node node : obstacles) {
                node.resetNode();
            }
        }

        path = null;
        obstacles = new LinkedList<>();
        start = goal = null;
        panel.repaint();
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
        obstacles.add(node);
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
