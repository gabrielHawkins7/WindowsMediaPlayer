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
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainEntry.fxml"));
    	Parent root = loader.load();
    	MainEntryController mec = loader.getController();
        mec.s = primaryStage;
        Scene scene = new Scene(root, 600, 400);
    	primaryStage.setScene(scene);
        primaryStage.show();
        
        
        Label l = new Label("No Media Loaded");
        l.setTextFill(Color.WHITE);
        mec.mainView.setCenter(l);
        //mec.SetMedia();
    }
}