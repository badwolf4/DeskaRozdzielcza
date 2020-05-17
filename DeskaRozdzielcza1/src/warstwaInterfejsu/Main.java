package warstwaInterfejsu;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
	
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
		throws Exception{ 
			Parent root = FXMLLoader.load(getClass().getResource("WyborInterfejsu.fxml"));
			primaryStage.setTitle("Deska Rozdzielcza");
			
			System.out.println("Deska Rozdzielcza Samochodu");
			primaryStage.setScene(new Scene(root, 400, 200)); //900,500 dla GUI 400,200 log
			primaryStage.show();
			//primaryStage.setOnCloseRequest(e -> Platform.exit());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
