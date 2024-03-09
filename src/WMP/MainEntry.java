package WMP;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        Scene scene = new Scene(root, 600, 400);
    	primaryStage.setScene(scene);
        primaryStage.show();
        mec.SetMedia();
    }
}