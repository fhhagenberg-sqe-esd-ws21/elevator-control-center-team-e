package at.fhhagenberg.sqe.ecc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

public class ElevatorGui implements EventHandler{
	
	private ElevatorModel model;
	
	@FXML private HBox elevators;
	@FXML private VBox elevatorButtons;
	@FXML private VBox callButtons;
	
	@Override
	public void handle(Event event) {
		Node node = (Node) event.getSource();
		int elevator = new Scanner(node.getId()).useDelimiter("\\D+").nextInt();
		
		if (node.getStyleClass().contains("auto")) {
			Node btnManual = elevators.lookup("#buttonManual" + Integer.toString(elevator));
			btnManual.setDisable(true);
		}
		else if (node.getStyleClass().contains("manual")) {
			System.out.println("Test2");
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
		
		VBox elevatorButtons = new VBox();
		
		for(int i = model.getNumOfFloors()-1; i >= 0; --i) {
			elevatorButtons.getChildren().add(new Label(Integer.toString(i)));
        }
		
		Label labelPosition = new Label("Position: ");
		Label labelSpeed = new Label("Speed: ");
		Label labelPayload = new Label("Payload: ");
		Label labelDoors = new Label("Doors: ");
		Label labelTarget = new Label("Target: ");
		
		VBox status = new VBox();
		status.getChildren().add(labelPosition);
		status.getChildren().add(labelSpeed);
		status.getChildren().add(labelPayload);
		status.getChildren().add(labelDoors);
		status.getChildren().add(labelTarget);
		
		Label labelUp = new Label("UP");
		Label labelDown = new Label("DOWN");
		
		
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
		
		for(int i = model.getNumOfFloors()-1; i >= 0; --i) {
        	callButtons.getChildren().add(new Label(Integer.toString(i)));
        }
		
		for(int i = 0; i < model.getNumOfElevators(); ++i) {
        	AnchorPane pane = this.ConstructElevator(i);
        	elevators.getChildren().add(pane);
        }
	}

	public void setModel(ElevatorModel model) {
		this.model = model;
		
	}

}
