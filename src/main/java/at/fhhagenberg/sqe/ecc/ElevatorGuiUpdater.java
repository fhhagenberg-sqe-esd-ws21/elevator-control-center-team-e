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

        public void UpdateGui() {

            for(int i = 0; i < model.getNumOfElevators(); i++) {
                gui.setCommitedDirection(i, model.getCommittedDirection(i));
                gui.setTargetFloor(i, model.getTarget(i));
                gui.setElevatorAccel(i, model.getElevatorAccel(i));
                gui.setElevatorDoorStatus(i, model.getElevatorDoorStatus(i));
                gui.setElevatorFloor(i, model.getElevatorFloor(i));
                gui.setElevatorPosition(i, model.getElevatorPosition(i));
                gui.setElevatorSpeed(i, model.getElevatorSpeed(i));
                gui.setElevatorWeight(i, model.getElevatorWeight(i));
                gui.setElevatorCapacity(i, model.getElevatorCapacity(i));
                UpdateElevatorButtons(i);
                UpdateServicedFloors(i);
            }

            for(int i = 0; i < model.getNumOfFloors(); i++) {
                gui.setFloorButtonUp(i, model.getFloorButtonUp(i));
                gui.setFloorButtonDown(i, model.getFloorButtonDown(i));
            }
        }

        private void UpdateServicedFloors(int elevatorNumber) {
            for (int i = 0; i < model.getNumOfFloors(); ++i) {
                gui.setServicesFloors(elevatorNumber, i, model.getServicesFloors(elevatorNumber, i));
            }
        }

        private void UpdateElevatorButtons(int elevatorNumber) {
            for (int i = 0; i < model.getNumOfFloors(); ++i) {
                gui.setElevatorButton(elevatorNumber, i, model.getElevatorButton(elevatorNumber, i));
            }
        }
}
