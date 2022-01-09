package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.rmi.RemoteException;

public class ElevatorGuiUpdater {

        private ElevatorModel model;
        private ElevatorGui gui;

        ElevatorGuiUpdater(ElevatorModel model, ElevatorGui gui) {
            this.model = model;
            this.gui = gui;
        }

        public void updateCommittedDirection(int elevator) {
            Platform.runLater(() -> gui.setServicesFloors(elevator));
        }

        public void updateSetTargetFloor(int elevator) {
            Platform.runLater(() -> gui.setTargetFloor(elevator));
        }

        public void updateSetElevatorAccel(int elevator) {
            Platform.runLater(() -> gui.setElevatorAccel(elevator));
        }

        public void setElevatorDoorStatus(int elevator) {
            Platform.runLater(() -> gui.setElevatorDoorStatus(elevator));
        }

        public void setElevatorFloor(int elevator) {
            Platform.runLater(() -> gui.setElevatorFloor(elevator));
        }

        public void setElevatorPosition(int elevator) {
            Platform.runLater(() -> gui.setElevatorPosition(elevator));
        }

        public void setElevatorSpeed(int elevator) {
            Platform.runLater(() -> gui.setElevatorSpeed(elevator));
        }

        public void setElevatorWeight(int elevator) {
            Platform.runLater(() -> gui.setElevatorWeight(elevator));
        }

        public void setElevatorCapacity(int elevator) {
            Platform.runLater(() -> gui.setElevatorCapacity(elevator));
        }

        public void setServicedFloors(int elevator, int floor) {
            Platform.runLater(() -> gui.setServicesFloors(elevator, floor));
        }

        public void setFloorButtonUp(int floor) {
            Platform.runLater(() -> gui.setFloorButtonUp(floor));
        }

        public void setFloorButtonDown(int floor) {
            Platform.runLater(() -> gui.setFloorButtonDown(floor));
        }

        public void setElevatorButton(int elevator) {
            Platform.runLater(() -> gui.setElevatorButton(elevator));
        }
}
