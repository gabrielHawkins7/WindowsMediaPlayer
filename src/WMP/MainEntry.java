package WMP;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainEntry extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
    	//FXML Setup and Start
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("StartingScreen.fxml"));
    	Parent root = loader.load();
        
        //Initialize Controllers
        DataHandler data = new DataHandler(primaryStage, root);
        data.ssc = loader.getController();
        data.ssc.initialize(data);
        
        Scene scene = new Scene(data.root, 600, 400);
    	primaryStage.setScene(scene);
        primaryStage.show();
    }
}