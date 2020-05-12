package warstwaInterfejsu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class ControllerWejscia {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private RadioButton radioReadFromXML;

    @FXML
    private RadioButton radioReadFromSQL;

    @FXML
    private Button okButton;

    @FXML
    private ComboBox<String> comboBox;
    
  
    
    @FXML
    void initialize() {
    	
    	comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("GUI", "Command Line");
        comboBox.getSelectionModel().select("GUI");
    
    	
		okButton.setOnAction(event -> {
			System.out.println("OK pressed");
			//переход между окнами
			okButton.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(); 
			loader.setLocation(getClass().getResource("/warstwaInterfejsu/DeskaRozdzielcza.fxml"));
		
			try {
				loader.load();
		}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
			
		});
		
		
    	/*
		 * assert cancelButton != null :
		 * "fx:id=\"cancelButton\" was not injected: check your FXML file 'WyborInterfejsu.fxml'."
		 * ; assert radioReadFromXML != null :
		 * "fx:id=\"radioReadFromXML\" was not injected: check your FXML file 'WyborInterfejsu.fxml'."
		 * ; assert radioReadFromSQL != null :
		 * "fx:id=\"radioReadFromSQL\" was not injected: check your FXML file 'WyborInterfejsu.fxml'."
		 * ; assert okButton != null :
		 * "fx:id=\"okButton\" was not injected: check your FXML file 'WyborInterfejsu.fxml'."
		 * ; assert comboBox != null :
		 * "fx:id=\"comboBox\" was not injected: check your FXML file 'WyborInterfejsu.fxml'."
		 * ;
		 */
    }
}
