package WMP;

import java.io.IOException;
import java.net.MalformedURLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class MainViewController {
	 	@FXML
	    private Label currentTime;

	    @FXML
	    private BorderPane mainView;

	    @FXML
	    private MediaView mediaView;

	    @FXML
	    private Button playPauseControl;

	    @FXML
	    private Slider progBar;
	    
	    private boolean isInit;
		private DataHandler data;
		private MediaPlayer mediaPlayer;
		
		
		void initialize(DataHandler d) {
			data = d;
			isInit = true;
		}
		
		void openFile() throws MalformedURLException {
			if(data.openFile != null && isInit == true) {
				data.openMedia = new Media(data.openFile.toURI().toURL().toString());
				initPlayer();
			}
		}
		
		void initPlayer() {
			mediaView = new MediaView();
			mainView.setCenter(mediaView);
			mediaPlayer = new MediaPlayer(data.openMedia);
	        mediaView.setMediaPlayer(mediaPlayer);
	        mediaPlayer.play();
	        mediaScale();
	        timelineController();
	        playPauseController();
		}
		void mediaScale() {
			mediaView.preserveRatioProperty();
	        mediaView.fitWidthProperty().bind(mainView.widthProperty());
	        mediaView.fitHeightProperty().bind(mainView.heightProperty());
	        mainView.widthProperty().addListener(new ChangeListener<>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			        mediaView.fitWidthProperty().bind(mainView.widthProperty());
				}
	        });
	        mainView.heightProperty().addListener(new ChangeListener<>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			        mediaView.fitHeightProperty().bind(mainView.heightProperty());
				}
	        });
		}
		void timelineController() {
			mediaPlayer.currentTimeProperty().addListener(new ChangeListener<>(){
				@SuppressWarnings("deprecation")
				@Override
				public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
					int sec = (int) (100 * mediaPlayer.getCurrentTime().toMinutes()) % 60;
					int min = (int) (mediaPlayer.getCurrentTime().toMinutes()) % 60;
					int hrs = (int) mediaPlayer.getCurrentTime().toMinutes() / 60;
					int durSec = (int) (100 * mediaPlayer.getTotalDuration().toMinutes()) % 60;
					int durMin = (int) (mediaPlayer.getTotalDuration().toMinutes()) % 60;
					int durHrs = (int) (mediaPlayer.getTotalDuration().toMinutes()) / 60;
					String ct = hrs + ":" + min + ":" + sec + "/" + durHrs + ":" + durMin + ":" + durSec;
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
		}
		void playPauseController() {
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
	    

	    @FXML
	    void chooseFile(ActionEvent event) throws IOException {
	    	data.chooseFile();
	    }
}
