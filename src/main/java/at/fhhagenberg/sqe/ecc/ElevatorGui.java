package at.fhhagenberg.sqe.ecc;

import java.rmi.RemoteException;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sqelevator.IElevator;

/**
 *
 * @author Bernhard Wandl - s2010567008
 */
public class ElevatorGui implements EventHandler<MouseEvent> {

	private ElevatorModel model;
	private IElevatorHardwareManager hwManager;

	@FXML
	private HBox elevators;
	@FXML
	private VBox callButtons;
	@FXML
	private Label errorMsgLabel;
	
	private int ScanElevator(Node node) {
		Scanner s = new Scanner(node.getId());
		int elevator = 0;
		try {
			elevator = s.useDelimiter("\\D+").nextInt();
		}finally {
			s.close();
		}
		return elevator;
	}

	@Override
	public void handle(MouseEvent event) {
		Button b = (Button) event.getSource();

		try {


			if (b.getStyleClass().contains("auto")) {
				int elevator = ScanElevator(b);
				Node btnManual = elevators.lookup("#buttonManual" + Integer.toString(elevator));
				btnManual.setDisable(false);
				b.setDisable(true);
			} else if (b.getStyleClass().contains("manual")) {
				int elevator = ScanElevator(b);
				Node btnAuto = elevators.lookup("#buttonAuto" + Integer.toString(elevator));
				btnAuto.setDisable(false);
				b.setDisable(true);
			} else if (b.getStyleClass().contains("floor-button")) {
				VBox floorList = (VBox) b.getParent();
				int elevator = ScanElevator(floorList);
				int floor = floorList.getChildren().size() - 1 - floorList.getChildren().indexOf(b);

				if (event.getButton().equals(MouseButton.PRIMARY)) {
					hwManager.setTarget(elevator, floor);
				} else if (event.getButton().equals(MouseButton.SECONDARY)) {
					hwManager.setServicesFloors(elevator, floor, !model.getServicesFloors(elevator, floor));
				}

			} else if (b.getStyleClass().contains("btn-up")) {
				int elevator = ScanElevator(b);

				if (model.getCommittedDirection(elevator) == IElevator.ELEVATOR_DIRECTION_UP) {
					// set direction uncommited
					hwManager.setCommittedDirection(elevator, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
				} else {
					// set direction up
					hwManager.setCommittedDirection(elevator, IElevator.ELEVATOR_DIRECTION_UP);
				}

			} else if (b.getStyleClass().contains("btn-down")) {
				int elevator = ScanElevator(b);

				if (model.getCommittedDirection(elevator) == IElevator.ELEVATOR_DIRECTION_DOWN) {
					// set direction uncommited
					hwManager.setCommittedDirection(elevator, IElevator.ELEVATOR_DIRECTION_UNCOMMITTED);
				} else {
					// set direction down
					hwManager.setCommittedDirection(elevator, IElevator.ELEVATOR_DIRECTION_DOWN);
				}
			}
		}catch(RemoteException e) {
			model.setErrorMessage("Connection Error: Please check connection to low-level elevator system.");
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
		labelFloor.getStyleClass().add("floor-label");
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

	public void setHwManager(IElevatorHardwareManager hwManager) {
		this.hwManager = hwManager;
	}

	public void setCommitedDirection(int elevator) {
			Node btnUp = elevators.lookup("#Elevator" + Integer.toString(elevator) + " .btn-up");
			btnUp.setStyle("-fx-text-fill: black");
			Node btnDown = elevators.lookup("#Elevator" + Integer.toString(elevator) + " .btn-down");
			btnDown.setStyle("-fx-text-fill: black");
	
			switch (model.getCommittedDirection(elevator)) {
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

	public void setTargetFloor(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .target-label");
			label.setText("Target: " + Integer.toString(model.getTarget(elevator)));
	}

	public void setElevatorAccel(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .accel-label");
			label.setText("Accel: " + Integer.toString(model.getElevatorAccel(elevator)));
	}

	public void setElevatorButton(int elevator) {
			VBox floorBox = (VBox) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .elevator-floors");
			ObservableList<Node> floorList = floorBox.getChildren();
			for(int i = 0; i < floorList.size(); ++i)
			{
				Button b = (Button) floorList.get(floorList.size() - 1 - i);
				
				if (model.getElevatorButton(elevator, i)) {
					b.setTextFill(Color.GREEN);
				} else {
					b.setTextFill(Color.BLACK);
				}
			}
	}

	public void setElevatorDoorStatus(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .doors-label");

			final String statusText;

			switch (model.getElevatorDoorStatus(elevator)) {
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
			label.setText("Doors: " + statusText);
	}

	public void setElevatorFloor(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .floor-label");
			label.setText("Floor: " + Integer.toString(model.getElevatorFloor(elevator)));
	}

	public void setElevatorPosition(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .position-label");
			label.setText("Position: " + Integer.toString(model.getElevatorPosition(elevator)));
	}

	public void setElevatorSpeed(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .speed-label");
			label.setText("Speed: " + Integer.toString(model.getElevatorSpeed(elevator)));
	}

	public void setElevatorWeight(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .weight-label");
			label.setText("Weight: " + Integer.toString(model.getElevatorWeight(elevator)));
	}

	public void setElevatorCapacity(int elevator) {
			Label label = (Label) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .capacity-label");
			label.setText("Capacity: " + Integer.toString(model.getElevatorCapacity(elevator)));
	}

	public void setFloorButtonUp(int floor) {
			Label btnUp = (Label) callButtons.lookup("#floor" + Integer.toString(floor) + " .btn-up");
	
			if (model.getFloorButtonUp(floor)) {
				btnUp.setTextFill(Color.RED);
			} else {
				btnUp.setTextFill(Color.BLACK);
			}
	}

	public void setFloorButtonDown(int floor) {
			Label btnDown = (Label) callButtons.lookup("#floor" + Integer.toString(floor) + " .btn-down");
			if (model.getFloorButtonDown(floor)) {
				btnDown.setTextFill(Color.RED);
			} else {
				btnDown.setTextFill(Color.BLACK);
			}
	}
	
	public void setServicesFloors(int elevator, int floor) {
			VBox floorBox = (VBox) elevators.lookup("#Elevator" + Integer.toString(elevator) + " .elevator-floors");
			ObservableList<Node> floorList = floorBox.getChildren();
			Button b = (Button) floorList.get(floorList.size() - 1 - floor);
	
			if (model.getServicesFloors(elevator, floor)) {
				b.getStyleClass().remove("disabledButton");
			} else {
				b.getStyleClass().add("disabledButton");
			}
	}

	public void setErrorMessage() {
		errorMsgLabel.setText(model.getErrorMessage());
	}
}
