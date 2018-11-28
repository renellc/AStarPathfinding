package models;

import java.util.LinkedList;
import java.util.Objects;

/**
 * The representation of a node in a grid.
 */
public class Node implements Comparable {
    /**
     * The x and y coordinates of this node in a grid.
     */
    private int x, y;

    /**
     * Values used for A* path finding.
     * <p>
     * G Score indicates the euclidean distance from this node to the start node.
     * H Score indicates the euclidean distance from this node to the end node.
     * F Score is the G Score + H Score.
     */
    private int gScore, hScore, fScore;

    /**
     * The node this node came from in the A* search.
     */
    private Node cameFrom;

    /**
     * The list of neighbors to this node.
     */
    private LinkedList<Node> neighbors;

    /**
     * Is this node an obstacle?
     */
    private boolean obstacle;

    /**
     * Creates a new node for a grid.
     *
     * @param x The grid X coordinate for this node.
     * @param y The grid Y coordinate for this node.
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;

        gScore = Integer.MAX_VALUE;
        hScore = 0;
        fScore = gScore + hScore;
        neighbors = new LinkedList<>();
        obstacle = false;
    }

    /**
     * Gets the list of neighbors for this node.
     *
     * @return The neighbors for this node.
     */
    public LinkedList<Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds a neighbor to the list of neighbors for this node.
     *
     * @param node The neighbor node.
     */
    public void addNeighbor(Node node) {
        neighbors.add(node);
    }

    /**
     * Gets the node this node came from during the A* search.
     *
     * @return The node this node came from.
     */
    public Node getCameFrom() {
        return cameFrom;
    }

    /**
     * Set where this node came from during the A* search.
     *
     * @param cameFrom The node this node is coming from.
     */
    public void setCameFrom(Node cameFrom) {
        this.cameFrom = cameFrom;
    }

    /**
     * Gets the grid X coordinate for this node.
     *
     * @return The grid X coordinate for this node.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the grid Y coordinate for this node.
     *
     * @return The grid Y coordinate for this node.
     */
    public int getY() {
        return y;
    }

    /**
     * Is the node an obstacle?
     *
     * @return True if the node is an obstacle, false otherwise.
     */
    public boolean isObstacle() {
        return obstacle;
    }

    /**
     * Sets the node as an obstacle or not.
     *
     * @param obstacle True if the node will become an obstacle, false otherwise.
     */
    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * Gets the Euclidean distance from this node to the start node.
     *
     * @return The Euclidean distance from this node to the start node.
     */
    public int getGScore() {
        return gScore;
    }

    /**
     * Sets the Euclidean distance from this node to the start node.
     */
    public void setGScore(int gScore) {
        this.gScore = gScore;
    }

    /**
     * Gets the Euclidean distance from this node to the end node.
     *
     * @return The Euclidean distance from this node to the end node.
     */
    public int getHScore() {
        return hScore;
    }


    /**
     * Sets the Euclidean distance from this node to the end node.
     */
    public void setHScore(int hScore) {
        this.hScore = hScore;
    }

    /**
     * Gets the G Score + H score
     *
     * @return G Score + H Score
     */
    public int getFScore() {
        return fScore;
    }

    /**
     * Manually set the F score for this node.
     *
     * @param fScore The new F score.
     */
    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    /**
     * Update the F score node based on the current G Score and H Score.
     */
    public void updateFScore() {
        fScore = gScore + hScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y &&
                Float.compare(node.gScore, gScore) == 0 &&
                Float.compare(node.hScore, hScore) == 0 &&
                Float.compare(node.fScore, fScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, gScore, hScore, fScore);
    }

    @Override
    public String toString() {
        return "models.Node{" +
                "x=" + x +
                ", y=" + y +
                ", gScore=" + gScore +
                ", hScore=" + hScore +
                ", fScore=" + fScore +
                ", cameFrom=" + cameFrom +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Node other = (Node) o;
        return Float.compare(fScore, ((Node) o).fScore);
    }
}
