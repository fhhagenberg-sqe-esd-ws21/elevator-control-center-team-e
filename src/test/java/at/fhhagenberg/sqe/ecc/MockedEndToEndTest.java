package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Markus Lindner - s2010567018
 */
@ExtendWith(ApplicationExtension.class)
public class MockedEndToEndTest {

    private MockElevatorHardwareManager hwManager;
    private ElevatorModelFactory modelFactory;
    private ElevatorModel model;
    private ElevatorGui gui;
    private ElevatorModelUpdater modelUpdater;
    private ElevatorGuiUpdater guiUpdater;

    @Start
    public void start(Stage primaryStage) throws Exception {
        hwManager = new MockElevatorHardwareManager();
        modelFactory = new ElevatorModelFactory(hwManager);
        model = modelFactory.CreateElevatorControlCenter();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIMock.fxml"));
        BorderPane content = loader.<BorderPane>load();

        gui = loader.getController();
        gui.setModel(model);
        gui.setHwManager(hwManager);
        gui.InitGui();

        modelUpdater = new ElevatorModelUpdater(hwManager, model);
        guiUpdater = new ElevatorGuiUpdater(gui);
        model.setGuiUpdater(guiUpdater);

        Scene scene = new Scene(content, 1000, 800);
        scene.getStylesheets().add(getClass().getResource("/stylesheet.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable periodicTask = new Runnable() {
            public void run() {
                modelUpdater.UpdateModel();
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 100, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testButtonUp(FxRobot robot) {

    	Button btn = robot.lookup("#Elevator0 .btn-up").<Button>query();
        model.initialGuiUpdate();
        robot.clickOn(btn);
    	WaitForAsyncUtils.waitForFxEvents();
    	assertEquals(Color.RED, btn.getTextFill());
    	robot.clickOn(btn);
    	WaitForAsyncUtils.waitForFxEvents();
    	assertEquals(Color.BLACK, btn.getTextFill());
    }
    
    @Test
    public void testButtonDown(FxRobot robot) {

    	Button btn = robot.lookup("#Elevator1 .btn-down").<Button>query();
    	robot.clickOn(btn);
    	WaitForAsyncUtils.waitForFxEvents();
    	assertEquals(Color.RED, btn.getTextFill());
    	robot.clickOn(btn);
    	WaitForAsyncUtils.waitForFxEvents();
    	assertEquals(Color.BLACK, btn.getTextFill());
    }
    
    @Test
    public void testSetTarget(FxRobot robot) {

    	VBox floorBox = (VBox) robot.lookup("#Elevator1 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button btn = (Button) floorList.get(floorList.size()-1-2); // Floor 2
		robot.clickOn(btn);
		
		WaitForAsyncUtils.waitForFxEvents();
		verifyThat("#Elevator1 .target-label", hasText("Target: 2"));
    }

    @Test
    public void testSetServicesFloor(FxRobot robot) {

    	VBox floorBox = (VBox) robot.lookup("#Elevator2 .elevator-floors").<VBox>query();
		ObservableList<Node> floorList= floorBox.getChildren();
		Button btn = (Button) floorList.get(floorList.size()-1-4); // Floor 4
		
		robot.rightClickOn(btn);
		WaitForAsyncUtils.waitForFxEvents();
		assertFalse(btn.getStyleClass().contains("disabledButton"));
		
		robot.rightClickOn(btn);
		WaitForAsyncUtils.waitForFxEvents();
		assertTrue(btn.getStyleClass().contains("disabledButton"));
    }
    
    @Test
    public void testReconnection(FxRobot robot) {
    	hwManager.setIsElevatorReachable(false);
    	hwManager.setIsConnected(false);
    	WaitForAsyncUtils.waitForFxEvents();
    	verifyThat("#errorMsgLabel", hasText("Connection Error: Please check connection to low-level elevator system."));
    	hwManager.setIsElevatorReachable(true);
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
    	WaitForAsyncUtils.waitForFxEvents();
    	verifyThat("#errorMsgLabel", hasText("Status: OK!"));
    }


}
