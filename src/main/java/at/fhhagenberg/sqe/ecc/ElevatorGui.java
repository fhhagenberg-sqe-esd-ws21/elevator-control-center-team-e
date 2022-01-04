package at.fhhagenberg.sqe.ecc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ElevatorGui implements EventHandler {

	private ElevatorModel model;

	@FXML
	private HBox elevators;
	@FXML
	private VBox callButtons;

	@Override
	public void handle(Event event) {
		Node node = (Node) event.getSource();
		int elevator = new Scanner(node.getId()).useDelimiter("\\D+").nextInt();

		if (node.getStyleClass().contains("auto")) {
			Node btnManual = elevators.lookup("#buttonManual" + Integer.toString(elevator));
			btnManual.setDisable(false);
			node.setDisable(true);
		} else if (node.getStyleClass().contains("manual")) {
			Node btnAuto = elevators.lookup("#buttonAuto" + Integer.toString(elevator));
			btnAuto.setDisable(false);
			node.setDisable(true);
		}

	}

	private AnchorPane ConstructElevator(int number) {

		Label title = new Label("Elevator " + Integer.toString(number));
		Button buttonManual = new Button("Manual");
		buttonManual.setId("buttonManual" + Integer.toString(number));
		buttonManual.getStyleClass().add("manual");
		buttonManual.setOnAction(this);

		Button buttonAuto = new Button("Auto");
		buttonAuto.setId("buttonAuto" + Integer.toString(number));
		buttonAuto.getStyleClass().add("auto");
		buttonAuto.setOnAction(this);

		buttonManual.setDisable(true);

		VBox elevatorButtons = new VBox();
		elevatorButtons.getStyleClass().add("elevator-floors");

		for (int i = model.getNumOfFloors() - 1; i >= 0; --i) {
			elevatorButtons.getChildren().add(new Label(Integer.toString(i)));
		}

		Label labelPosition = new Label("Position: ");
		labelPosition.getStyleClass().add("position-label");
		Label labelSpeed = new Label("Speed: ");
		labelSpeed.getStyleClass().add("speed-label");
		Label labelAccel = new Label("Accel: ");
		labelAccel.getStyleClass().add("accel-label");
		Label labelDoors = new Label("Doors: ");
		labelDoors.getStyleClass().add("doors-label");
		Label labelTarget = new Label("Target: ");
		labelTarget.getStyleClass().add("target-label");
		Label labelFloor = new Label("Floor: ");
		labelTarget.getStyleClass().add("floor-label");
		Label labelWeight = new Label("Weight: ");
		labelWeight.getStyleClass().add("weight-label");
		Label labelCapacity = new Label("Capacity: ");
		labelCapacity.getStyleClass().add("capacity-label");

		VBox status = new VBox();
		status.getChildren().add(labelPosition);
		status.getChildren().add(labelSpeed);
		status.getChildren().add(labelAccel);
		status.getChildren().add(labelDoors);
		status.getChildren().add(labelTarget);
		status.getChildren().add(labelFloor);
		status.getChildren().add(labelWeight);
		status.getChildren().add(labelCapacity);

		Label labelUp = new Label("UP");
		labelUp.getStyleClass().add("btn-up");
		Label labelDown = new Label("DOWN");
		labelDown.getStyleClass().add("btn-down");

		VBox content = new VBox();
		content.getChildren().add(title);
		content.getChildren().add(buttonManual);
		content.getChildren().add(buttonAuto);
		content.getChildren().add(labelUp);
		content.getChildren().add(elevatorButtons);
		content.getChildren().add(labelDown);
		content.getChildren().add(status);

		AnchorPane pane = new AnchorPane(content);
		pane.setId("Elevator" + Integer.toString(number)); // #Elevator1

		return pane;
	}

	@FXML
	public void InitGui() {

		for (int i = model.getNumOfFloors() - 1; i >= 0; --i) {
			HBox floor = new HBox();
			floor.setId("floor" + Integer.toString(i));
			Label up = new Label("UP");
			up.getStyleClass().add("btn-up");
			up.setMinWidth(20);
			Label down = new Label("DOWN");
			down.getStyleClass().add("btn-down");
			down.setMinWidth(50);

			floor.getChildren().add(up);
			floor.getChildren().add(new Label(Integer.toString(i)));
			floor.getChildren().add(down);

			callButtons.getChildren().add(floor);
		}

		for (int i = 0; i < model.getNumOfElevators(); ++i) {
			AnchorPane pane = this.ConstructElevator(i);
			elevators.getChildren().add(pane);
		}

	}

	public void setModel(ElevatorModel model) {
		this.model = model;

	}

	public void setCommitedDirection(int elevator, int direction) {

		Node btnUp = elevators.lookup("#Elevator" + Integer.toString(elevator) + " .btn-up");
		btnUp.setStyle("-fx-text-fill: black");
		Node btnDown = elevators.lookup("#Elevator" + Integer.toString(elevator) + " .btn-down");
		btnDown.setStyle("-fx-text-fill: black");

		switch (direction) {
		case IElevator.ELEVATOR_DIRECTION_DOWN:
			btnDown.setStyle("-fx-text-fill: red");
			break;
		case IElevator.ELEVATOR_DIRECTION_UP:
			btnUp.setStyle("-fx-text-fill: red");
			break;
		default:
			break;
		}
	}

	public void setTargetFloor(int elevator, int floor) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .target-label");
		Platform.runLater(() -> {
			label.setText("Target: " + Integer.toString(floor));
		});
	}

	public void setElevatorAccel(int elevator, int value) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .accel-label");
		Platform.runLater(() -> {
			label.setText("Accel: " + Integer.toString(value));
		});
	}

	public void setElevatorButton(int elevator, int floor, boolean pressed) {
		VBox floorBox = (VBox) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .elevator-floors");
		ObservableList<Node> floorList = floorBox.getChildren();
		Label lblFloor = (Label) floorList.get(floorList.size() - 1 - floor);

		if (pressed) {
			lblFloor.setTextFill(Color.GREEN);
		} else {
			lblFloor.setTextFill(Color.BLACK);
		}
	}

	public void setElevatorDoorStatus(int elevator, int status) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .doors-label");

		final String statusText;

		switch (status) {
		case IElevator.ELEVATOR_DOORS_CLOSED:
			statusText = "CLOSED";
			break;
		case IElevator.ELEVATOR_DOORS_CLOSING:
			statusText = "CLOSING";
			break;
		case IElevator.ELEVATOR_DOORS_OPEN:
			statusText = "OPEN";
			break;
		case IElevator.ELEVATOR_DOORS_OPENING:
			statusText = "OPENING";
			break;
		default:
			statusText = "";
			break;
		}

		Platform.runLater(() -> {
			label.setText("Doors: " + statusText);
		});
	}

	public void setElevatorFloor(int elevator, int floor) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .floor-label");
		Platform.runLater(() -> {
			label.setText("Floor: " + Integer.toString(floor));
		});
	}

	public void setElevatorPosition(int elevator, int value) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .position-label");
		Platform.runLater(() -> {
			label.setText("Position: " + Integer.toString(value));
		});
	}

	public void setElevatorSpeed(int elevator, int value) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .speed-label");
		Platform.runLater(() -> {
			label.setText("Speed: " + Integer.toString(value));
		});
	}

	public void setElevatorWeight(int elevator, int value) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .weight-label");
		Platform.runLater(() -> {
			label.setText("Weight: " + Integer.toString(value));
		});
	}

	public void setElevatorCapacity(int elevator, int value) {
		Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .capacity-label");
		Platform.runLater(() -> {
			label.setText("Capacity: " + Integer.toString(value));
		});
	}

	public void setFloorButtonUp(int floor, boolean value) {
		Label btnUp = (Label) callButtons.lookup("#floor" + Integer.toString(floor) + " .btn-up");

		if (value) {
			btnUp.setTextFill(Color.RED);
		} else {
			btnUp.setTextFill(Color.BLACK);
		}
	}

	public void setFloorButtonDown(int floor, boolean value) {

		Label btnDown = (Label) callButtons.lookup("#floor" + Integer.toString(floor) + " .btn-down");
		if (value) {
			btnDown.setTextFill(Color.RED);
		} else {
			btnDown.setTextFill(Color.BLACK);
		}

	}

}
