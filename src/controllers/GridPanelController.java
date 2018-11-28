package controllers;

import models.Grid;
import models.Node;
import views.GridPanel;

import java.util.LinkedHashSet;

public class GridPanelController {

    private GridPanel panel;
    private Grid grid;

    private Node start, goal;
    private LinkedHashSet<Node> path;

    public GridPanelController(GridPanel panel, Grid grid) {
        this.panel = panel;
        this.grid = grid;
        start = grid.getNode(5, 5);
        goal = grid.getNode(10, 15);
    }

    public void performAStar() {
        path = grid.performAStar(start, goal);
    }

    public void resetGrid() {
        grid.resetGrid();
        path = null;
    }

    public Node getNode(int x, int y) {
        return grid.getNode(x, y);
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getGoal() {
        return goal;
    }

    public void setGoal(Node goal) {
        this.goal = goal;
    }

    public LinkedHashSet<Node> getVisitedNodes() {
        return grid.getClosedNodes();
    }

    public LinkedHashSet<Node> getPath() {
        return path;
    }
}
