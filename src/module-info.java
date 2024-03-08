module WindowsMediaPlayer {
	requires javafx.controls;
	
	opens WMP to javafx.graphics, javafx.fxml;
}
