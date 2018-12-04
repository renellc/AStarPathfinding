package models;

import java.util.LinkedHashSet;
import java.util.PriorityQueue;

/**
 * The representation of a grid.
 */
public class Grid {
    /**
     * The grid represented by a 2D array of nodes.
     */
    private Node[][] grid;

    /**
     * The visited nodes from the A* search.
     */
    private LinkedHashSet<Node> closedNodes;

    /**
     * Creates a new grid.
     *
     * @param width  The maximum number of nodes that can be placed horizontally.
     * @param height The maximum number of nodes that can be placed vertically.
     */
    public Grid(int width, int height) {
        grid = new Node[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new Node(x, y);

                // Adds neighbors for node right to the current node.
                if (x != 0) {
                    grid[x][y].addNeighbor(grid[x - 1][y]);
                    grid[x - 1][y].addNeighbor(grid[x][y]);
                }

                // Adds neighbors for node above this node.
                if (y != 0) {
                    grid[x][y].addNeighbor(grid[x][y - 1]);
                    grid[x][y - 1].addNeighbor(grid[x][y]);
                }
            }
        }
    }

    /**
     * Performs A* search algorithm for the shortest path from a start node to and end node.
     *
     * @param start The starting point for the A* search.
     * @param goal  The node that we want to reach.
     * @return If a path is found, the path from the start node to the goal node, null otherwise.
     */
    public LinkedHashSet<Node> performAStar(Node start, Node goal) {
        // Use PriorityQueue to get node with lowest F score in O(log n) time.
        PriorityQueue<Node> openNodes = new PriorityQueue<>();
        // Use LinkedHashSet to check if node has already been checked in O(1) time and
        // to maintain ordering of inserted items.
        closedNodes = new LinkedHashSet<>();
        // Start node starts at 0 since the distance to itself is 0.
        start.setGScore(0);
        // Initial H Score is just the distance from the start to goal.
        start.setHScore(findEuclidDistance(start, goal));
        // F Score starts as 0 + distance from start to end.
        start.updateFScore();
        // Open nodes start with the initial start node.
        openNodes.add(start);

        while (!openNodes.isEmpty()) {
            // Get the node with the lowest F score.
            Node current = openNodes.poll();

            // If we reached the goal, build the path and return it.
            if (current == goal) {
                return buildPath(goal);
            }

            closedNodes.add(current);

            for (Node neighbor : current.getNeighbors()) {
                if (closedNodes.contains(neighbor) || neighbor.isObstacle()) {
                    continue;
                }

                // The G Score for the current neighbor.
                int newGScore = current.getGScore() + findEuclidDistance(current, neighbor);
                if (newGScore < neighbor.getGScore()) {
                    neighbor.setCameFrom(current);
                    neighbor.setGScore(newGScore);
                    neighbor.setHScore(findEuclidDistance(neighbor, goal));
                    neighbor.updateFScore();

                    if (!openNodes.contains(neighbor)) {
                        openNodes.add(neighbor);
                    }
                }
            }
        }

        // If we can't find a path to the end, return nothing.
        return null;
    }

    /**
     * Builds the path from the start to the end goal if the end goal is reached.
     *
     * @param goal The end goal.
     * @return The LinkedHashSet containing the path from the start to the end goal.
     */
    private LinkedHashSet<Node> buildPath(Node goal) {
        LinkedHashSet<Node> path = new LinkedHashSet<>();
        Node current = goal;
        while (current != null) {
            path.add(current);
            current = current.getCameFrom();
        }
        return path;
    }

    /**
     * Finds the Euclidean distance between two nodes.
     *
     * @param a The first node.
     * @param b The second node.
     * @return The Euclidean distance between the two nodes.
     */
    private int findEuclidDistance(Node a, Node b) {
        float xDiff = a.getX() - b.getX();
        float yDiff = a.getY() - b.getY();
        return (int) Math.round(Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)) * 10);
    }

    /**
     * Resets the node's values in the grid.
     */
    public void resetGrid() {
        if (closedNodes == null || closedNodes.isEmpty())
            return;

        for (Node node : closedNodes) {
            node.resetNode();
        }
    }

    /**
     * Gets a specified node from the grid.
     *
     * @param x The X coordinate of the node.
     * @param y The Y coordinate of the node.
     * @return The node with coordinates X and Y.
     */
    public Node getNode(int x, int y) {
        return grid[x][y];
    }

    /**
     * Gets the visited nodes during the A* search.
     *
     * @return The list of visited nodes during the A* search.
     */
    public LinkedHashSet<Node> getClosedNodes() {
        return closedNodes;
    }
}
