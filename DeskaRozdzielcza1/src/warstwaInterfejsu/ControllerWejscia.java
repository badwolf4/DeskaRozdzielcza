package warstwaInterfejsu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Klasa reprezentująca okienko startu z wyborem interfejsu (GUI/CLI) oraz wybor skąd wczytać dane do aplikacji
 */
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
    

	boolean wybor = false;
    
	/**
	 * Metoda wywoływana przy załadowaniu widoku okienka
	 */
    @FXML
    void initialize() {
    	ToggleGroup group = new ToggleGroup();
    	radioReadFromSQL.setToggleGroup(group);
    	radioReadFromXML.setToggleGroup(group);
    	comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("GUI", "Command Line");
        comboBox.getSelectionModel().select("GUI");
        comboBox.setOnAction(event -> {

        	group.getToggles().forEach(toggle -> {
			    Node node = (Node) toggle ;
			    node.setDisable(comboBox.getValue()=="Command Line");
        	
			});     
        	
        });
        okButton.setOnAction(event -> {	
			if(comboBox.getValue()=="GUI") {
				wybor = radioReadFromSQL.isSelected();
				okButton.getScene().getWindow().hide();
				Controller controller = new Controller(wybor); 
				
				FXMLLoader loader = new FXMLLoader(); 
				loader.setLocation(getClass().getResource("/warstwaInterfejsu/DeskaRozdzielcza.fxml"));
				loader.setController(controller);
				
				try {
					loader.load();
					okButton.getScene().getWindow().setOnCloseRequest(e -> Platform.exit());
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				
				Parent root = loader.getRoot();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();
				stage.setOnCloseRequest(e -> {
					Platform.exit();
					System.exit(0);
				});
			} 
			else if (comboBox.getValue()=="Command Line") 
			{
				new CLIinterfejs().start();
				okButton.getScene().getWindow().hide();
			
			}
			});	
        cancelButton.setOnAction(event ->{
        	System.out.println("Wyjscie z programu");
        	System.exit(0);
        });
        
    }
}
