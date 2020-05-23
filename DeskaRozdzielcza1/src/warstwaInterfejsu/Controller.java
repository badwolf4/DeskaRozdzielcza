package warstwaInterfejsu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import warstwaLogiki.DatabaseHandler;
import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;
import warstwaLogiki.XMLReaderWriter;

/**
 * Klasa służąca do zarządzania widokiem głównego interfejsu graficznego aplikacji (GUI)
 */
public class Controller  {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Circle swiatloDrogowe;

	@FXML
	private Circle swiatloMijania;

	@FXML
	private Circle swiatloPrzod;

	@FXML
	private Circle swiatloTyl;

	@FXML
	private Circle swiatloPozycyjne;

	@FXML
	private Polyline lewaStrzalka;

	@FXML
	private Polyline prawaStrzalka;

	@FXML
	private TextField przebiegCalkowity;

	@FXML
	private TextField przebiegDzienny;

	@FXML
	private TextField czasPodrorzy;

	@FXML
	private TextField dystans;

	@FXML
	private TextField spalanie;

	@FXML
	private TextField srednia;

	@FXML
	private TextField maksymalna;

	@FXML
	private TextField predkosc;

	@FXML
	private MenuBar deskaMenuBar;
	
	@FXML private MenuItem menuBarClose;
	
	@FXML private MenuItem menuBarMenu;

	@FXML
	private Button zapiszDoBDButton;
	
	@FXML
	private Button zapiszDoXMLButton;
	
	@FXML
	private MenuItem TEST;
	
	@FXML
	private MenuItem zapiszDoXMLItem;
	
	@FXML
	private MenuItem AboutItem;
	
	
	private boolean lewyWlaczony = false;
	private boolean prawyWlaczony = false;
	private DeskaRozdzielcza deska = null;
	private Timer t;
	private boolean flag;

	/**
	 * Tworzenie nowej instancji klasy Controller
	 * @param flag parametr przekazany od startowego okienka sygnalizująca wybór użytkownika skąd wczytać dane
	 */
	public Controller(boolean flag) {
		this.flag = flag;
		this.deska = new DeskaRozdzielcza();
	}
	
	/**
	 * Metoda wywoływana podczas startu widoku. Służy do ustawienia parametrów dla elementów widoku:
	 *  obsługa przycisków, start wątku odświeżającego interfejsc co sekundę. Wczytanie początkowego stanu widoku
	 */
	
	@FXML
	void initialize() {
		XMLReaderWriter xmlInterpretor = new XMLReaderWriter();
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		//wczytywanie przy starcie okna
		if(flag) {
			 deska = dbHandler.wczytajZBD();
			 System.out.println("czytam z sql");
		} else {
			 deska = xmlInterpretor.odczytaj("state.xml");
			 System.out.println("czytam z xml");
		}
		
		//obsluga klawiszow do zapisu danych		

		TEST.setOnAction(event ->{
			  dbHandler.usunZBD();
			  dbHandler.zapisacDoBD(deska);
		System.out.println("Information have been saved"); });
		
		AboutItem.setOnAction(event ->{
			 Label secondLabel = new Label("Klawisze: \n"
			 		+ "Q - wlacz/wylacz kontrolke swiatel drogowych \n"
					+ "E - wlacz/wylacz kontrolke swiatel mijania \n"
			 		+ "Z - wlacz/wylacz kontrolke swiatel przeciwmgielnych przod \n"
					+ "X - wlacz/wylacz kontrolke swiatel przeciwmgielnych tyl \n"
			 		+ "C - wlacz/wylacz kontrolke swiatel pozycyjnych \n"
					+ "U - wyzeruj licznik przebiegu dziennego \n"
					+ "Gora - przyszpiesz \n"
			 		+ "Dol - zwolnij \n"
					+ "Lewo - wlacz/wylacz kontrolke lewego kierunkowskazu \n"
			 		+ "Prawo - wlacz/wylacz kontrolke prawego kierunkowskazu"
					);
			 StackPane secondaryLayout = new StackPane();
	         secondaryLayout.getChildren().add(secondLabel);
	         Scene secondScene = new Scene(secondaryLayout, 320, 200);
	         Stage newWindow = new Stage();
	         newWindow.setTitle("Second Stage");
	         newWindow.setScene(secondScene);
	         newWindow.show();
		 });
		
		zapiszDoBDButton.setOnAction(event ->{
				//TEST.setOnAction(event ->{
					  dbHandler.usunZBD();
					  dbHandler.zapisacDoBD(deska);
				System.out.println("Information have been saved"); });
				
		//XML zapiszDoXMLItem
		zapiszDoXMLButton.setOnAction(event->{
			try {
				xmlInterpretor.zapisz(deska);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Nie udalo sie zapisac do XML. Prosze o skorzystanie sie z innej opcji.");
			}
			finally {
				System.out.println("Stan zostal pomyslnie zapisany do XML");
			}
		});
		
		
		menuBarClose.setOnAction(event->{
			System.exit(0);
		});
		
		
		zapiszDoXMLItem.setOnAction(event->{
			try {
				xmlInterpretor.zapisz(deska);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Nie udalo sie zapisac do XML. Prosze o skorzystanie sie z innej opcji.");
			}
			finally {
				System.out.println("Stan zostal pomyslnie zapisany do XML");
			}
		});
		
		//zmiana niektorych artyrubow pol tekstowych
		przebiegCalkowity.setEditable(false);
		przebiegCalkowity.setDisable(true);
		czasPodrorzy.setEditable(false);
		czasPodrorzy.setDisable(true);
		przebiegDzienny.setEditable(false);
		przebiegDzienny.setDisable(true);
		dystans.setEditable(false);
		dystans.setDisable(true);
		spalanie.setEditable(false);
		spalanie.setDisable(true);
		srednia.setEditable(false);
		srednia.setDisable(true);
		maksymalna.setEditable(false);
		maksymalna.setDisable(true);
		predkosc.setEditable(false);
		predkosc.setDisable(true);

		//wstepne ladowanie widoku
		refreash();
		
		//wlaczenie timera w desce do odswiezania danych
		deska.start();

		//tworzymy watek do odswiezania widoku co sekunde
		Timer t1 = new Timer();
		t1.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				refreash();
			}
		}, 1000, 1000);
		
		//wlaczenie kierunkowskazow w gui jesli byl odczytany stan jako wlaczony
		if(deska.getStrzalka(0).getWlaczona())
		{
			lewyWlaczony = true;
			t = new Timer();

			t.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					// Called each time when 1000 milliseconds (1 second) (the period parameter)
					if (lewaStrzalka.getFill() == Color.WHITE) {
						lewaStrzalka.setFill(Color.GREEN);
					} else
						lewaStrzalka.setFill(Color.WHITE);
				}

			},0,1000);
			
			lewaStrzalka.setFill(Color.WHITE);
		}
		
		if(deska.getStrzalka(1).getWlaczona())
		{
			t = new Timer();
			prawyWlaczony = true;

			t.scheduleAtFixedRate(new TimerTask() {

				@Override
				public void run() {
					if (prawaStrzalka.getFill() == Color.WHITE) {
						prawaStrzalka.setFill(Color.GREEN);
					} else
						prawaStrzalka.setFill(Color.WHITE);

				}

			}, 0, 1000);
			prawaStrzalka.setFill(Color.WHITE);
		}

	}

	/**
	 * Metoda zapewniająca obsługę zdarzeń klawiatuwy
	 * @param event klawisz który został wciśnięty
	 */
	@FXML
	void handleOnKeyPressed(KeyEvent event) {
		System.out.println("Klawisz wcisnieto");
		// strzalki

		if (event.getCode() == KeyCode.LEFT) {

			if (!lewyWlaczony && !prawyWlaczony) {
				deska.getStrzalka(0).wlacz();
				t = new Timer();

				lewyWlaczony = true;

				t.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						if (lewaStrzalka.getFill() == Color.WHITE) {
							lewaStrzalka.setFill(Color.GREEN);
						} else
							lewaStrzalka.setFill(Color.WHITE);
					}

				}, 0, 1000);

				lewaStrzalka.setFill(Color.WHITE);
			}

			else {
				t.cancel();
				t.purge();
				deska.getStrzalka(0).wylacz();
				deska.getStrzalka(1).wylacz();
				prawyWlaczony = false;
				lewyWlaczony = false;
				lewaStrzalka.setFill(Color.WHITE);
				prawaStrzalka.setFill(Color.WHITE);

			}
		}

		if (event.getCode() == KeyCode.RIGHT) {
			if (!prawyWlaczony && !lewyWlaczony) {
				deska.getStrzalka(1).wlacz();

				t = new Timer();
				prawyWlaczony = true;

				t.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						if (prawaStrzalka.getFill() == Color.WHITE) {
							prawaStrzalka.setFill(Color.GREEN);
						} else
							prawaStrzalka.setFill(Color.WHITE);

					}

				}, 0, 1000);

				prawaStrzalka.setFill(Color.WHITE);

			}

			else {
				t.cancel();
				t.purge();
				deska.getStrzalka(0).wylacz();
				deska.getStrzalka(1).wylacz();
				prawyWlaczony = false;
				lewyWlaczony = false;
				lewaStrzalka.setFill(Color.WHITE);
				prawaStrzalka.setFill(Color.WHITE);
			}
		}
		
		if (event.getCode() == KeyCode.UP) {
		
			try {
				deska.getPredkosciomierz().przyspiesz();
			}

			catch (OsiagnietaMaksymalnaSzybkoscException e) {
				System.out.println(e.getMessage());
			}
		}
		
		if (event.getCode() == KeyCode.DOWN) {
			try {
				deska.getPredkosciomierz().bardzoZwolnij();
			} catch (OsiagnietaMinimalnaSzybkoscException e) {
				System.out.println(e.getMessage());
			}
		}
		// swiatla 
		if (event.getCode() == KeyCode.Q) {
			System.out.println("Q pressed");
			if (!deska.getSwiatlo(0).getWlaczona())
				deska.getSwiatlo(0).wlacz();

			else
				deska.getSwiatlo(0).wylacz();

		}

		if (event.getCode() == KeyCode.E) {
			System.out.println("E pressed");
			if (!deska.getSwiatlo(1).getWlaczona())
				deska.getSwiatlo(1).wlacz();

			else
				deska.getSwiatlo(1).wylacz();
		}

		if (event.getCode() == KeyCode.Z) {
			System.out.println("Z pressed");
			if (!deska.getSwiatlo(2).getWlaczona())
				deska.getSwiatlo(2).wlacz();

			else
				deska.getSwiatlo(2).wylacz();
		}

		if (event.getCode() == KeyCode.X) {
			System.out.println("X pressed");
			if (!deska.getSwiatlo(3).getWlaczona())
				deska.getSwiatlo(3).wlacz();

			else
				deska.getSwiatlo(3).wylacz();
		}
		if (event.getCode() == KeyCode.C) {
			System.out.println("C pressed");
			if (!deska.getSwiatlo(4).getWlaczona())
				deska.getSwiatlo(4).wlacz();

			else
				deska.getSwiatlo(4).wylacz();
		}
		if(event.getCode() == KeyCode.U) {
			System.out.println("Wyzerowanie licznika przebiegu dziennego");
			deska.getLicznikPrzebieguDziennego().wyzerujLicznik();
		}

		refreash();

	}
	
	/**
	 * Metoda odświeżająca interfejsc graficzny aplikacji
	 */

	void refreash() {
		przebiegCalkowity.setText(Double.toString(bd(deska.getLicznikPrzebieguCalkowitego().getPrzebieg())));

		przebiegDzienny.setText(Double.toString(bd(deska.getLicznikPrzebieguDziennego().getPrzebieg())));

		czasPodrorzy.setText(Double.toString(bd(deska.getKomputerPokladowy().getCzasPodrozy())));

		dystans.setText(Double.toString(bd(deska.getKomputerPokladowy().getDystans())));

		spalanie.setText(Double.toString(bd(deska.getKomputerPokladowy().getSrednieSpalanie())));

		srednia.setText(Double.toString(bd(deska.getKomputerPokladowy().getPredkoscSrednia())));

		maksymalna.setText(Integer.toString((int)Math.round(deska.getKomputerPokladowy().getPredkoscMaksymalna())));

		predkosc.setText(Integer.toString((int)Math.round(deska.getPredkosciomierz().getPredkosc())));

		if (deska.getSwiatlo(0).getWlaczona())
			swiatloDrogowe.setFill(Color.BLUE);
		else
			swiatloDrogowe.setFill(Color.WHITE);

		if (deska.getSwiatlo(1).getWlaczona())
			swiatloMijania.setFill(Color.YELLOW);
		else
			swiatloMijania.setFill(Color.WHITE);

		if (deska.getSwiatlo(2).getWlaczona())
			swiatloPrzod.setFill(Color.BLUE);
		else
			swiatloPrzod.setFill(Color.WHITE);

		if (deska.getSwiatlo(3).getWlaczona())
			swiatloTyl.setFill(Color.YELLOW);
		else
			swiatloTyl.setFill(Color.WHITE);

		if (deska.getSwiatlo(4).getWlaczona())
			swiatloPozycyjne.setFill(Color.RED);
		else
			swiatloPozycyjne.setFill(Color.WHITE);
	}
	
	/**
	 * Metoda do zaokrąglenia wartości do dwu miejsc po przycinku przed wyświetlaniem na ekranie
	 * @param input wartość która będzie poddana zaokrągleniu
	 * @return double
	 */

	public double bd(double input) {
		return new BigDecimal(input).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
