package at.fhhagenberg.sqe.ecc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ElevatorMain extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	ElevatorModel model = new ElevatorModel(3, 2, 230);
    	
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIMock.fxml"));
        BorderPane content = loader.<BorderPane>load();
        
        loader.<ElevatorGui>getController().setModel(model);
        //loader.<ElevatorGui>getController().setCo(model);
        loader.<ElevatorGui>getController().InitGui();
    	
        Scene scene = new Scene(content);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}