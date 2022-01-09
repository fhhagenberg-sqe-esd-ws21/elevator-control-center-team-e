package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ElevatorMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        IElevatorHardwareManager hwManager = new MockElevatorHardwareManager();
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
                // Invoke method(s) to do the work
                modelUpdater.UpdateModel();
                //guiUpdater.UpdateGui();
            }
        };
        executor.scheduleAtFixedRate(periodicTask, 0, 100, TimeUnit.MILLISECONDS);
    }
}