package warstwaInterfejsu;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Klasa reprezentująca punkt wejścia do aplikacji
 */
public class Main extends Application {
	
	/**
	 * Metoda do załadowania widoku aplikacji z plików zródłowych
	 */
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
	
	/**
	 * Główna metoda main aplikacji
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
