package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;

/**
 * Updates the GUI asynchronously.
 *
 * @author Bernhard Wandl - s2010567008
 */
public class ElevatorGuiUpdater {
    private ElevatorGui gui;

    /**
     * Generates a new updater.
     *
     * @param gui The gui to update.
     */
    ElevatorGuiUpdater(ElevatorGui gui) {
        this.gui = gui;
    }

    /**
     * Updates the commited direction in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateCommittedDirection(int elevator) {
        Platform.runLater(() -> gui.setCommitedDirection(elevator));
    }

    /**
     * Updates the target floor in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateTargetFloor(int elevator) {
        Platform.runLater(() -> gui.setTargetFloor(elevator));
    }

    /**
     * Updates the acceleration in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorAccel(int elevator) {
        Platform.runLater(() -> gui.setElevatorAccel(elevator));
    }

    /**
     * Updates the door status in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorDoorStatus(int elevator) {
        Platform.runLater(() -> gui.setElevatorDoorStatus(elevator));
    }

    /**
     * Updates the floor in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorFloor(int elevator) {
        Platform.runLater(() -> gui.setElevatorFloor(elevator));
    }

    /**
     * Updates the current position in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorPosition(int elevator) {
        Platform.runLater(() -> gui.setElevatorPosition(elevator));
    }

    /**
     * Updates the current speed in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorSpeed(int elevator) {
        Platform.runLater(() -> gui.setElevatorSpeed(elevator));
    }

    /**
     * Updates the current weight in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorWeight(int elevator) {
        Platform.runLater(() -> gui.setElevatorWeight(elevator));
    }

    /**
     * Updates the capacity in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorCapacity(int elevator) {
        Platform.runLater(() -> gui.setElevatorCapacity(elevator));
    }

    /**
     * Updates the serviced floors in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateServicedFloors(int elevator, int floor) {
        Platform.runLater(() -> gui.setServicesFloors(elevator, floor));
    }

    /**
     * Updates the status of the up button for a given floor in the gui.
     *
     * @param floor The floor to update the value for.
     */
    public void updateFloorButtonUp(int floor) {
        Platform.runLater(() -> gui.setFloorButtonUp(floor));
    }

    /**
     * Updates the status of the down button for a given floor in the gui.
     *
     * @param floor The floor to update the value for.
     */
    public void updateFloorButtonDown(int floor) {
        Platform.runLater(() -> gui.setFloorButtonDown(floor));
    }

    /**
     * Updates the status of the buttons for a given elevator in the gui.
     *
     * @param elevator The elevator to update the value for.
     */
    public void updateElevatorButton(int elevator) {
        Platform.runLater(() -> gui.setElevatorButton(elevator));
    }


    /**
     * Updates the error message shown in the gui.
     */
    public void updateErrorMessage() {
        Platform.runLater(() -> gui.setErrorMessage());
    }
}
