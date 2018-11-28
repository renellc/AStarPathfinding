package views;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    public ControlPanel() {
        setLayout(new FlowLayout());

        JButton setStartNode = new JButton("Set Start");
        JButton setGoalNode = new JButton("Set Goal");
        JButton setObstacle = new JButton("Set Obstacle");
        JButton clearNode = new JButton("Clear Node");
        JButton findPath = new JButton("Find Path");
        JButton resetGrid = new JButton("Reset");

        add(setStartNode);
        add(setGoalNode);
        add(setObstacle);
        add(clearNode);
        add(findPath);
        add(resetGrid);
    }
}
