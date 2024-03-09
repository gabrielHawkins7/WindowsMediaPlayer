package WMP;

import java.io.File;
import java.net.MalformedURLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainEntryController {
	public Stage s;
	
	@FXML
	public BorderPane mainView;
	
	@FXML
	public MediaView mediaView;
	
	@FXML
	public Label currentTime;
	
	
	@FXML
	public Slider progBar;
	
	@FXML
	public Button playPauseControl;
	
	public File openFile;
	
	
	public void SetMedia() throws MalformedURLException {
		//File mediaFile = new File("./testAssets/video.mp4");
		mainView.setCenter(mediaView);
        Media media = new Media(openFile.toURI().toURL().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaView.preserveRatioProperty();
        mediaView.fitWidthProperty().bind(mainView.widthProperty());
        mediaView.fitHeightProperty().bind(mainView.heightProperty());
        mainView.widthProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		        mediaView.fitWidthProperty().bind(mainView.widthProperty());
		        System.out.println(mainView.getWidth());
		        System.out.println(mainView.getWidth());
			}
        });
        mainView.heightProperty().addListener(new ChangeListener<>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		        mediaView.fitHeightProperty().bind(mainView.heightProperty());
			}
        });
        
        mediaPlayer.play();
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<>(){
			@SuppressWarnings("deprecation")
			@Override
			public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
				int sec = (int) (100 * mediaPlayer.getCurrentTime().toMinutes());
				int min = (int) (mediaPlayer.getCurrentTime().toMinutes());
				int durSec = (int) (100 * mediaPlayer.getTotalDuration().toMinutes());
				int durMin = (int) (mediaPlayer.getTotalDuration().toMinutes());
				String ct = "" + min + ":" + sec + "/" + durMin + ":" + durSec;
				currentTime.setText(ct);
				progBar.setValue(mediaPlayer.getCurrentTime().divide(mediaPlayer.getTotalDuration()).toMillis()*100.0);
			}
        });
        progBar.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(progBar.getValue()/100.0));
			}
		});
        progBar.setOnMouseDragged(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(progBar.getValue()/100.0));
			}
		});
        playPauseControl.setOnMousePressed(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if(mediaPlayer.getStatus() == Status.PLAYING) {
					mediaPlayer.pause();
					playPauseControl.setText("| |");
				}else {
					mediaPlayer.play();
					playPauseControl.setText(">");
				}
			}
		});
	}	
	
	public void chooseFile() throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		openFile = fileChooser.showOpenDialog(s);
		SetMedia();
	}
	
}
