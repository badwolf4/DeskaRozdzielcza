package warstwaInterfejsu;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;

//import org.xml.sax.helpers.XMLReaderAdapter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import warstwaDanych.DatabaseHandler;
import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;
import warstwaLogiki.XMLReaderWriter;

//import javafx.scene.shape.SVGPath;

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

	@FXML
	private Button zapiszDoBDButton;
	
	@FXML
	private Button zapiszDoXMLButton;
	
	/*
	 * @FXML public void handleCloseButtonAction(ActionEvent event) {
	 * ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); }
	 */

	private boolean lewyWlaczony = false;
	private boolean prawyWlaczony = false;
	private boolean zapisano = false;

	private DeskaRozdzielcza deska = null;

	private Timer t;
	private boolean flag;

	public Controller(boolean flag) {
		this.flag = flag;
		this.deska = new DeskaRozdzielcza();
	}

	void wczytaj(boolean W) {
		System.out.println("Pobrano " + W);
	}
	
	@FXML
	void initialize() {
		XMLReaderWriter xmlInterpretor = new XMLReaderWriter();
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		//wczytywanie przy starcie okna
		if(flag) {
			 deska = dbHandler.wczytajZBD(deska);
			 System.out.println("czytam z sql");
			 zapisano = true;
		} else {
			 deska = xmlInterpretor.odczytaj("state.xml");
			 System.out.println("czytam z xml");
		}
		
		//obsluga klawiszow do zapisu danych
		//BD
		zapiszDoBDButton.setOnAction(event ->{
			  dbHandler.usunZBD();
			  dbHandler.zapisacDoBD(deska);
		System.out.println("Information have been saved"); });
		
		//XML
		zapiszDoXMLButton.setOnAction(event->{
			try {
				xmlInterpretor.zapisz(deska);
				zapisano = true;
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
				// Called each time when 1000 milliseconds (1 second) (the period parameter)
				refreash();
			}

		},
				// Set how long before to start calling the TimerTask (in milliseconds)
				1000,
				// Set the amount of time between each execution (in milliseconds)
				1000);
		
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

		refreash();

	}

	void refreash() {
		// System.out.println("deska refreash");
		przebiegCalkowity.setText(Double.toString(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));

		przebiegDzienny.setText(Double.toString(deska.getLicznikPrzebieguDziennego().getPrzebieg()));

		czasPodrorzy.setText(Double.toString(deska.getKomputerPokladowy().getCzasPodrozy()));

		dystans.setText(Double.toString(deska.getKomputerPokladowy().getDystans()));

		spalanie.setText(Double.toString(deska.getKomputerPokladowy().getSrednieSpalanie()));

		srednia.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscSrednia()));

		maksymalna.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscMaksymalna()));

		predkosc.setText(Integer.toString(deska.getPredkosciomierz().getPredkosc()));

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

}
