package controllers;

import models.Grid;
import models.GridActions;
import views.ControlPanel;

public class ControlPanelController {

    /**
     * The panel that this controller controls.
     */
    private ControlPanel panel;

    /**
     * The grid for the application.
     */
    private Grid grid;

    /**
     * The current grid action selected.
     */
    private GridActions action;

    public ControlPanelController(ControlPanel panel, Grid grid) {
        this.panel = panel;
        this.grid = grid;
    }

    /**
     * Switches the current action selected for a new one.
     *
     * @param action The new action.
     */
    public void switchAction(GridActions action) {
        this.action = action;
        System.out.println("Now performing " + action);
    }
}
