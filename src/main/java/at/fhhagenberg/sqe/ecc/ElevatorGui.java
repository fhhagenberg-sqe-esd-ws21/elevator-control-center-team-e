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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ElevatorGui implements EventHandler<MouseEvent> {

	private ElevatorModel model;

	@FXML
	private HBox elevators;
	@FXML
	private VBox callButtons;

	@Override
	public void handle(MouseEvent event) {
		Button b = (Button) event.getSource();

		if (b.getStyleClass().contains("auto")) {
			int elevator = new Scanner(b.getId()).useDelimiter("\\D+").nextInt();
			Node btnManual = elevators.lookup("#buttonManual" + Integer.toString(elevator));
			btnManual.setDisable(false);
			b.setDisable(true);
		} else if (b.getStyleClass().contains("manual")) {
			int elevator = new Scanner(b.getId()).useDelimiter("\\D+").nextInt();
			Node btnAuto = elevators.lookup("#buttonAuto" + Integer.toString(elevator));
			btnAuto.setDisable(false);
			b.setDisable(true);
		} else if (b.getStyleClass().contains("floor-button")) {
			VBox floorList = (VBox) b.getParent();
			int elevator = new Scanner(floorList.getId()).useDelimiter("\\D+").nextInt();
			int floor = floorList.getChildren().size() - 1 - floorList.getChildren().indexOf(b);
			
			if(event.getButton().equals(MouseButton.PRIMARY)) {
				// set Target
			} else if (event.getButton().equals(MouseButton.SECONDARY)){
				// set Services Floors
			}
			System.out.println(Integer.toString(elevator) + ", " + Integer.toString(floor));
			
		} else if(b.getStyleClass().contains("btn-up")) {
			int elevator = new Scanner(b.getId()).useDelimiter("\\D+").nextInt();
					
			if (model.getCommittedDirection(elevator) == IElevator.ELEVATOR_DIRECTION_UP) {
				// set direction uncommited
			} else {
				// set direction up
			}
			
			System.out.println(Integer.toString(elevator));
			
		} else if(b.getStyleClass().contains("btn-up")) {
			int elevator = new Scanner(b.getId()).useDelimiter("\\D+").nextInt();
			
			if (model.getCommittedDirection(elevator) == IElevator.ELEVATOR_DIRECTION_DOWN) {
				// set direction uncommited
			} else {
				// set direction down
			}
			
			System.out.println(Integer.toString(elevator));
		}
			
	}

	private AnchorPane ConstructElevator(int number) {

		Label title = new Label("Elevator " + Integer.toString(number));
		title.getStyleClass().add("elevator-title");
		
		Button buttonManual = new Button("Manual");
		buttonManual.setId("buttonManual" + Integer.toString(number));
		buttonManual.getStyleClass().add("manual");
		buttonManual.setOnMouseClicked(this);

		Button buttonAuto = new Button("Auto");
		buttonAuto.setId("buttonAuto" + Integer.toString(number));
		buttonAuto.getStyleClass().add("auto");
		buttonAuto.setOnMouseClicked(this);

		buttonManual.setDisable(true);

		VBox elevatorButtons = new VBox();
		elevatorButtons.setId("elevatorFloors" + Integer.toString(number));
		elevatorButtons.getStyleClass().add("elevator-floors");

		for (int i = model.getNumOfFloors() - 1; i >= 0; --i) {
			Button b = new Button(Integer.toString(i));
			b.getStyleClass().add("floor-button");
			b.setOnMouseClicked(this);
			elevatorButtons.getChildren().add(b);
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

		Button btnUp = new Button("UP");
		btnUp.getStyleClass().add("btn-up");
		Button btnDown = new Button("DOWN");
		btnDown.getStyleClass().add("btn-down");
		btnUp.setId("buttonUp" + Integer.toString(number));
		btnDown.setId("buttonDown" + Integer.toString(number));
		btnUp.setOnMouseClicked(this);
		btnDown.setOnMouseClicked(this);

		VBox content = new VBox();
		content.getChildren().add(title);
		content.getChildren().add(buttonManual);
		content.getChildren().add(buttonAuto);
		content.getChildren().add(btnUp);
		content.getChildren().add(elevatorButtons);
		content.getChildren().add(btnDown);
		content.getChildren().add(status);

		AnchorPane pane = new AnchorPane(content);
		pane.setId("Elevator" + Integer.toString(number)); // #Elevator1
		content.getStyleClass().add("elevator");

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
			
			Label floorLabel = new Label(Integer.toString(i));
			
			down.getStyleClass().add("call-button-label");
			up.getStyleClass().add("call-button-label");
			floorLabel.getStyleClass().add("call-button-label");
			

			floor.getChildren().add(up);
			floor.getChildren().add(floorLabel);
			floor.getChildren().add(down);
			floor.getStyleClass().add("call-button-floor-box");

			callButtons.getChildren().add(floor);
		}
		
		elevators.getStyleClass().add("elevator-box");

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
		Button b = (Button) floorList.get(floorList.size() - 1 - floor);

		if (pressed) {
			b.setTextFill(Color.GREEN);
		} else {
			b.setTextFill(Color.BLACK);
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
	
	public void setServicesFloors(int elevator, int floor, boolean service) {
		VBox floorBox = (VBox) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .elevator-floors");
		ObservableList<Node> floorList = floorBox.getChildren();
		Button b = (Button) floorList.get(floorList.size() - 1 - floor);

		if (service) {
			b.getStyleClass().remove("disabledButton");
		} else {
			b.getStyleClass().add("disabledButton");
		}
	}

}
