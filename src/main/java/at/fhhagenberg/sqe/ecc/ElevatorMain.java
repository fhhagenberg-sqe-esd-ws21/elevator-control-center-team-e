package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Main Class.
 * @author Alexander Kemptner - s2010567016
 */
public class ElevatorMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            IElevatorHardwareManager hwManager = new ElevatorHardwareManager(ElevatorConnectionManager.ElevatorConnectionSetup());

            ElevatorModelFactory modelFactory = new ElevatorModelFactory(hwManager);
            ElevatorModel model = modelFactory.CreateElevatorControlCenter();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/GUIMock.fxml"));
            BorderPane content = loader.<BorderPane>load();

            ElevatorGui gui = loader.getController();
            gui.setModel(model);
            gui.setHwManager(hwManager);
            gui.InitGui();

            ElevatorModelUpdater modelUpdater = new ElevatorModelUpdater(hwManager, model);
            ElevatorGuiUpdater guiUpdater = new ElevatorGuiUpdater(gui);
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
            model.initialGuiUpdate();
        }catch(Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Internal error occured. Please try to restart the application.");
            a.setContentText(e.toString());
            a.show();
        }
    }
}