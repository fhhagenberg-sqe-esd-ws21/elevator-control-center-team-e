package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;

public class ElevatorGuiUpdater {
        private ElevatorGui gui;

        ElevatorGuiUpdater(ElevatorGui gui) {
            this.gui = gui;
        }

        public void updateCommittedDirection(int elevator) {
            Platform.runLater(() -> gui.setCommitedDirection(elevator));
        }

        public void updateTargetFloor(int elevator) {
            Platform.runLater(() -> gui.setTargetFloor(elevator));
        }

        public void updateElevatorAccel(int elevator) {
            Platform.runLater(() -> gui.setElevatorAccel(elevator));
        }

        public void updateElevatorDoorStatus(int elevator) {
            Platform.runLater(() -> gui.setElevatorDoorStatus(elevator));
        }

        public void updateElevatorFloor(int elevator) {
            Platform.runLater(() -> gui.setElevatorFloor(elevator));
        }

        public void updateElevatorPosition(int elevator) {
            Platform.runLater(() -> gui.setElevatorPosition(elevator));
        }

        public void updateElevatorSpeed(int elevator) {
            Platform.runLater(() -> gui.setElevatorSpeed(elevator));
        }

        public void updateElevatorWeight(int elevator) {
            Platform.runLater(() -> gui.setElevatorWeight(elevator));
        }

        public void updateElevatorCapacity(int elevator) {
            Platform.runLater(() -> gui.setElevatorCapacity(elevator));
        }

        public void updateServicedFloors(int elevator, int floor) {
            Platform.runLater(() -> gui.setServicesFloors(elevator, floor));
        }

        public void updateFloorButtonUp(int floor) {
            Platform.runLater(() -> gui.setFloorButtonUp(floor));
        }

        public void updateFloorButtonDown(int floor) {
            Platform.runLater(() -> gui.setFloorButtonDown(floor));
        }

        public void updateElevatorButton(int elevator) {
            Platform.runLater(() -> gui.setElevatorButton(elevator));
        }
}
