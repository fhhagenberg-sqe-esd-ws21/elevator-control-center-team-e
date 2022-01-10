package at.fhhagenberg.sqe.ecc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import javafx.application.Platform;
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

/**
 *
 * @author Bernhard Wandl - s2010567008
 */
@ExtendWith(ApplicationExtension.class)
public class ElevatorGuiTest {
	
	private ElevatorGui gui;
	private ElevatorGuiUpdater guiUpdater;
	private ElevatorModel model;
	
	@Start
	public void start(Stage stage) throws Exception {
		model = new ElevatorModel(3, 2, 230);
    	
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIMock.fxml"));
        BorderPane content = loader.<BorderPane>load();
        
        loader.<ElevatorGui>getController().setModel(model);
        loader.<ElevatorGui>getController().InitGui();
        
        gui = loader.<ElevatorGui>getController();
		guiUpdater = new ElevatorGuiUpdater(gui);
		model.setGuiUpdater(guiUpdater);
    	
        Scene scene = new Scene(content, 1000, 800);
        stage.setScene(scene);
        stage.show();
	}
	
	@Test
	public void testCommittedDirection(FxRobot robot) {
		
		model.setCommittedDirection(0, IElevator.ELEVATOR_DIRECTION_DOWN);
		Platform.runLater(() -> gui.setCommitedDirection(0) );
		
		Button btnUp = robot.lookup("#Elevator0 .btn-up").<Button>query();
		Button btnDown = robot.lookup("#Elevator0 .btn-down").<Button>query();
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator0 .btn-down", hasText("DOWN"));
		verifyThat("#Elevator0 .btn-up", hasText("UP"));
		assertEquals(Color.BLACK, btnUp.getTextFill());
		assertEquals(Color.RED, btnDown.getTextFill());
	}
	
	@Test
	public void testSetTargetFloor(FxRobot robot) {
		
		model.setTarget(1, 1);
		Platform.runLater(() ->gui.setTargetFloor(1));
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator1 .target-label", hasText("Target: 1"));
	}
	
	@Test
	public void testSetElevatorAccel(FxRobot robot) {
		
		model.setElevatorAccel(1, 129);
		Platform.runLater(() -> gui.setElevatorAccel(1));
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator1 .accel-label", hasText("Accel: 129"));
	}
	
	@Test
	public void testElevatorButton(FxRobot robot) {
		
		model.setElevatorButton(1, 1, true);
		Platform.runLater(() -> gui.setElevatorButton(1));
		
		VBox floorBox = (VBox) robot.lookup("#Elevator1 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button floor = (Button) floorList.get(floorList.size()-1-1);
		
		WaitForAsyncUtils.waitForFxEvents();
		assertEquals("1", floor.getText());
		assertEquals(Color.GREEN, floor.getTextFill());
	}
	
	@Test
	public void testSetElevatorDoorStatus(FxRobot robot) {
		
		model.setElevatorDoorStatus(2, IElevator.ELEVATOR_DOORS_CLOSING);
		Platform.runLater(() -> gui.setElevatorDoorStatus(2));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator2 .doors-label", hasText("Doors: CLOSING"));
	}
	
	@Test
	public void testSetElevatorFloor(FxRobot robot) {
		
		model.setElevatorFloor(2, 0);
		Platform.runLater(() -> gui.setElevatorFloor(2));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator2 .floor-label", hasText("Floor: 0"));
	}
	
	@Test
	public void testSetElevatorPosition(FxRobot robot) {
		
		model.setElevatorPosition(0, 120);
		Platform.runLater(() -> gui.setElevatorPosition(0));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator0 .position-label", hasText("Position: 120"));
	}
	
	@Test
	public void testSetElevatorSpeed(FxRobot robot) {
		
		model.setElevatorSpeed(1, 56);
		Platform.runLater(() -> gui.setElevatorSpeed(1));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator1 .speed-label", hasText("Speed: 56"));
	}
	
	@Test
	public void testSetElevatorWeight(FxRobot robot) {
		
		model.setElevatorWeight(2, 489);
		Platform.runLater(() -> gui.setElevatorWeight(2));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator2 .weight-label", hasText("Weight: 489"));
	}
	
	@Test
	public void testSetElevatorCapacity(FxRobot robot) {
		
		model.setElevatorCapacity(1, 12);
		Platform.runLater(() -> gui.setElevatorCapacity(1));
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator1 .capacity-label", hasText("Capacity: 12"));
	}
	
	@Test
	public void testSetFloorButtonUp(FxRobot robot) {
		
		model.setFloorButtonUp(0, true);
		Platform.runLater(() -> gui.setFloorButtonUp(0));
		
		Label button = robot.lookup("#floor0 .btn-up").<Label>query();
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#floor0 .btn-up", hasText("UP"));
		assertEquals(Color.RED, button.getTextFill());
		
		model.setFloorButtonUp(0, false);
		Platform.runLater(() -> gui.setFloorButtonUp(0));
		
		WaitForAsyncUtils.waitForFxEvents();
		assertEquals(Color.BLACK, button.getTextFill());
	}

	@Test
	public void testSetFloorButtonDown(FxRobot robot) {
		
		model.setFloorButtonDown(1, true);
		Platform.runLater(() -> gui.setFloorButtonDown(1));
		
		Label button = robot.lookup("#floor1 .btn-down").<Label>query();
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#floor1 .btn-down", hasText("DOWN"));
		assertEquals(Color.RED, button.getTextFill());
		
		model.setFloorButtonDown(1, false);
		Platform.runLater(() -> gui.setFloorButtonDown(1));
		
		WaitForAsyncUtils.waitForFxEvents();
		assertEquals(Color.BLACK, button.getTextFill());
	}
	
	@Test
	public void testSetServicesFloor(FxRobot robot) {
		
		model.setServicesFloors(2, 0, false);
		Platform.runLater(() -> gui.setServicesFloors(2, 0));
		
		VBox floorBox = (VBox) robot.lookup("#Elevator2 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button floor = (Button) floorList.get(floorList.size()-1-0);
		
		WaitForAsyncUtils.waitForFxEvents();
		assertEquals("0", floor.getText());
		assertTrue(floor.getStyleClass().contains("disabledButton"));
	}

}


