package views;

import controllers.ApplicationController;
import models.Grid;
import models.GridActions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ApplicationFrame extends JFrame implements MouseListener, MouseMotionListener {

    /**
     * The panel that draws the grid.
     */
    private GridPanel gridPanel;

    /**
     * The panel containing the controls for the grid.
     */
    private ControlPanel controlPanel;

    /**
     * The controller for the main application frame.
     */
    private ApplicationController controller;

    /**
     * The current action for the grid.
     */
    private GridActions currentAction;

    public ApplicationFrame(int width, int height, int cellSize, int heightOfControlPanel) {
        // Initialize the main frame.
        initializeFrame(width, height);
        setFocusable(true);

        // Initialize the grid panel.
        Grid grid = new Grid(width / cellSize + 1, height - heightOfControlPanel / cellSize + 1);
        gridPanel = new GridPanel(cellSize);
        gridPanel.createController(grid);
        controlPanel = InitializeControlPanel();
        controlPanel.setPreferredSize(new Dimension(width, heightOfControlPanel));

        // Initialize the controller for this frame.
        controller = new ApplicationController(gridPanel.getController(), cellSize);
        currentAction = GridActions.PLACE_START;

        gridPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        controlPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * Initializes the settings for the frame.
     *
     * @param width  The width of the frame.
     * @param height The height of the frame.
     */
    private void initializeFrame(int width, int height) {
        setTitle("A* Visualized");
        setSize(width, height);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Initializes the buttons on the control panel.
     * @return The control panel with its buttons initialized.
     */
    private ControlPanel InitializeControlPanel() {
        ControlPanel panel = new ControlPanel();

        JButton setStartNode = new JButton("Set Start");
        JButton setGoalNode = new JButton("Set Goal");
        JButton setObstacle = new JButton("Set Obstacle");
        JButton clearNode = new JButton("Clear Node");
        JButton findPath = new JButton("Find Path");
        JButton resetGrid = new JButton("Reset");

        setStartNode.addActionListener(new ControlPanelButtonListener(GridActions.PLACE_START));
        setGoalNode.addActionListener(new ControlPanelButtonListener(GridActions.PLACE_END));
        setObstacle.addActionListener(new ControlPanelButtonListener(GridActions.PLACE_OBSTACLE));
        clearNode.addActionListener(new ControlPanelButtonListener(GridActions.REMOVE));
        findPath.addActionListener(new ControlPanelButtonListener(GridActions.FIND_PATH));
        resetGrid.addActionListener(new ControlPanelButtonListener(GridActions.CLEAR_GRID));

        panel.add(setStartNode);
        panel.add(setGoalNode);
        panel.add(setObstacle);
        panel.add(clearNode);
        panel.add(findPath);
        panel.add(resetGrid);

        return panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        controller.applyGridAction(currentAction, e.getX(), e.getY());
        gridPanel.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (currentAction == GridActions.PLACE_OBSTACLE) {
            controller.applyGridAction(currentAction, e.getX(), e.getY());
            gridPanel.repaint();
        }
    }

    /**
     * The listener for the buttons in the Control Panel.
     */
    class ControlPanelButtonListener implements ActionListener {

        private GridActions action;

        ControlPanelButtonListener(GridActions action) {
            this.action = action;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (action == GridActions.FIND_PATH) {
                controller.performAStarSearch();
                gridPanel.repaint();
            } else {
                currentAction = action;
                System.out.println(currentAction);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
