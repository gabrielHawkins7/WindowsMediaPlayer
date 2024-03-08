package WMP;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class MainEntry extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws IOException {
    	//FXML Setup and Start
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("File:./src/WMP/MainEntry.fxml"));
        VBox vbox = new VBox(loader.<VBox>load());
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        File mediaFile = new File("./testAssets/video.mp4");
        MainEntryController mec = new MainEntryController();

    }
}