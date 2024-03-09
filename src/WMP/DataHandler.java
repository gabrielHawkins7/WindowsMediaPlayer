package WMP;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class DataHandler {
	public Stage primaryStage;
	public Parent root;
	public File openFile;
	public Media openMedia;
	public StartingScreenController ssc;
	public MainViewController mvc;
	
	DataHandler(Stage pStage, Parent r){
		primaryStage = pStage;
		root = r;
	}
	void chooseFile() throws IOException {
		FileChooser fileChooser = new FileChooser();
		openFile = fileChooser.showOpenDialog(primaryStage);
		loadPlayer();
	}
	void loadPlayer() throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
    	root = loader.load();
    	mvc = loader.getController();
    	mvc.initialize(this);
    	mvc.openFile();
        Scene scene = new Scene(root, 600, 400);
    	primaryStage.setScene(scene);
        primaryStage.show();
	}
}
