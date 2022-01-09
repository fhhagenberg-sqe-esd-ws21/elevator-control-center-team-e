package at.fhhagenberg.sqe.ecc;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.mock;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Markus Lindner - s2010567018
 */
public class MockedEndToEndTest {

    private IElevatorHardwareManager hwManager;
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

        Scene scene = new Scene(content);
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
    public void test_end_to_end(/*FxRobot robot*/) {

//        robot.rightClickOn("BUTTON??")
//
//        Platform.runLater(() -> gui.setElevatorAccel(1));
//        WaitForAsyncUtils.waitForFxEvents();
//
//        model.setTarget(0, 1);
        //Platform.runLater(() ->gui.setTargetFloor(1));
        //WaitForAsyncUtils.waitForFxEvents();
        //verifyThat("#Elevator1 .target-label", hasText("Target: 1"));
        System.out.println("End-To-End-Mock-Test!");
    }

}
