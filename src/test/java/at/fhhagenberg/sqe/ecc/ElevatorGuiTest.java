package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ElevatorGuiTest {
	
	private ElevatorGui gui;
	
	@Start
	public void start(Stage stage) throws Exception {
		ElevatorModel model = new ElevatorModel(3, 2, 230);
    	
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIMock.fxml"));
        BorderPane content = loader.<BorderPane>load();
        
        loader.<ElevatorGui>getController().setModel(model);
        //loader.<ElevatorGui>getController().setCo(model);
        loader.<ElevatorGui>getController().InitGui();
        
        gui = loader.<ElevatorGui>getController();
    	
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.show();
	}
	
	@Test
	public void testCommittedDirection(FxRobot robot) {
		
		gui.setCommitedDirection(0, IElevator.ELEVATOR_DIRECTION_DOWN);
		
		Button btnUp = robot.lookup("#Elevator0 .btn-up").<Button>query();
		Button btnDown = robot.lookup("#Elevator0 .btn-down").<Button>query();
		
		verifyThat("#Elevator0 .btn-down", hasText("DOWN"));
		verifyThat("#Elevator0 .btn-up", hasText("UP"));
		assertEquals(Color.BLACK, btnUp.getTextFill());
		assertEquals(Color.RED, btnDown.getTextFill());
	}
	
	@Test
	public void testSetTargetFloor(FxRobot robot) {
		
		gui.setTargetFloor(1, 1);
		verifyThat("#Elevator1 .target-label", hasText("Target: 1"));
	}
	
	@Test
	public void testSetElevatorAccel(FxRobot robot) {
		
		gui.setElevatorAccel(1, 129);
		verifyThat("#Elevator1 .accel-label", hasText("Accel: 129"));
	}
	
	@Test
	public void testElevatorButton(FxRobot robot) {
		
		gui.setElevatorButton(1, 1, true);
		
		VBox floorBox = (VBox) robot.lookup("#Elevator1 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button floor = (Button) floorList.get(floorList.size()-1-1);
		
		assertEquals("1", floor.getText());
		assertEquals(Color.GREEN, floor.getTextFill());
	}
	
	@Test
	public void testSetElevatorDoorStatus(FxRobot robot) {
		
		gui.setElevatorDoorStatus(2, IElevator.ELEVATOR_DOORS_CLOSING);
		verifyThat("#Elevator2 .doors-label", hasText("Doors: CLOSING"));
	}
	
	@Test
	public void testSetElevatorFloor(FxRobot robot) {
		
		gui.setElevatorFloor(2, 0);
		verifyThat("#Elevator2 .floor-label", hasText("Floor: 0"));
	}
	
	@Test
	public void testSetElevatorPosition(FxRobot robot) {
		
		gui.setElevatorPosition(0, 120);
		verifyThat("#Elevator0 .position-label", hasText("Position: 120"));
	}
	
	@Test
	public void testSetElevatorSpeed(FxRobot robot) {
		
		gui.setElevatorSpeed(1, 56);
		verifyThat("#Elevator1 .speed-label", hasText("Speed: 56"));
	}
	
	@Test
	public void testSetElevatorWeight(FxRobot robot) {
		
		gui.setElevatorWeight(2, 489);
		verifyThat("#Elevator2 .weight-label", hasText("Weight: 489"));
	}
	
	@Test
	public void testSetElevatorCapacity(FxRobot robot) {
		gui.setElevatorCapacity(1, 12);
		verifyThat("#Elevator1 .capacity-label", hasText("Capacity: 12"));
	}
	
	@Test
	public void testSetFloorButtonUp(FxRobot robot) {
		
		gui.setFloorButtonUp(0, true);
		
		Label button = robot.lookup("#floor0 .btn-up").<Label>query();
		
		verifyThat("#floor0 .btn-up", hasText("UP"));
		assertEquals(Color.RED, button.getTextFill());
		
		gui.setFloorButtonUp(0, false);
		assertEquals(Color.BLACK, button.getTextFill());
	}

	@Test
	public void testSetFloorButtonDown(FxRobot robot) {
		
		gui.setFloorButtonDown(1, true);
		
		Label button = robot.lookup("#floor1 .btn-down").<Label>query();
		
		verifyThat("#floor1 .btn-down", hasText("DOWN"));
		assertEquals(Color.RED, button.getTextFill());
		
		gui.setFloorButtonDown(1, false);
		assertEquals(Color.BLACK, button.getTextFill());
	}
	
	@Test
	public void testSetServicesFloor(FxRobot robot) {
		
		gui.setServicesFloors(2, 0, false);
		
		VBox floorBox = (VBox) robot.lookup("#Elevator2 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button floor = (Button) floorList.get(floorList.size()-1-0);
		
		assertEquals("0", floor.getText());
		assertTrue(floor.getStyleClass().contains("disabledButton"));
	}

}


