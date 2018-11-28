package views;

import controllers.ControlPanelController;
import models.Grid;
import models.GridActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    /**
     * The controller for this panel.
     */
    public ControlPanelController controller;

    public ControlPanel() {
        setLayout(new FlowLayout());

        JButton setStartNode = new JButton("Set Start");
        JButton setGoalNode = new JButton("Set Goal");
        JButton setObstacle = new JButton("Set Obstacle");
        JButton clearNode = new JButton("Clear Node");
        JButton findPath = new JButton("Find Path");
        JButton resetGrid = new JButton("Reset");

        setStartNode.addActionListener(new GridControlButtonListener(GridActions.PLACE_START));
        setGoalNode.addActionListener(new GridControlButtonListener(GridActions.PLACE_END));
        setObstacle.addActionListener(new GridControlButtonListener(GridActions.PLACE_OBSTACLE));
        clearNode.addActionListener(new GridControlButtonListener(GridActions.REMOVE));

        add(setStartNode);
        add(setGoalNode);
        add(setObstacle);
        add(clearNode);
        add(findPath);
        add(resetGrid);
    }

    /**
     * Creates a controller for this panel.
     *
     * @param grid The grid for the application.
     */
    public void createController(Grid grid) {
        controller = new ControlPanelController(this, grid);
    }

    /**
     * Gets the controller for this panel.
     *
     * @return The ControlPanel controller.
     */
    public ControlPanelController getController() {
        return controller;
    }

    /**
     * An ActionListener used for the controls found within the ControlPanel.
     */
    class GridControlButtonListener implements ActionListener {

        /**
         * The action associated with this listener.
         */
        private GridActions action;

        /**
         * Creates a new GridControlButtonListener.
         *
         * @param action The action associated with this listener.
         */
        public GridControlButtonListener(GridActions action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.switchAction(action);
        }
    }
}
