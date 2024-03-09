package WMP;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.FileChooser;

public class StartingScreenController {
	private boolean isInit;
	private DataHandler data;
	
	
	void initialize(DataHandler d) {
		data = d;
		isInit = true;
	}
	
    @FXML
    void chooseFile(ActionEvent event) throws Exception {
    	if(isInit = true) {
    		data.chooseFile();
    	}
    	else {
    		throw new Exception("Starting Screen Controller Not Initialized");
    	}
    }
}
