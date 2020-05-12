package warstwaInterfejsu;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
	
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
		throws Exception{ 
			Parent root = FXMLLoader.load(getClass().getResource("WyborInterfejsu.fxml"));
			primaryStage.setTitle("Deska Rozdzielcza");
			
			System.out.println("Deska Rozdzielcza Samochodu");
			primaryStage.setScene(new Scene(root, 400, 200)); //900,500 dla GUI 400,200 log
			primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
