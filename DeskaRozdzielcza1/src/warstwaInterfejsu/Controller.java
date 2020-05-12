package warstwaInterfejsu;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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
import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;
import warstwaLogiki.XMLReaderWriter;

//import javafx.scene.shape.SVGPath;

public class Controller {

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
	private Button testbutton;
	
	/*
	 * @FXML public void handleCloseButtonAction(ActionEvent event) {
	 * ((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); }
	 */

	private boolean lewyWlaczony = false;
	private boolean prawyWlaczony = false;

	private DeskaRozdzielcza deska;

	private Timer t;

	@FXML
	void initialize() {
		XMLReaderWriter xmlInterpretor = new XMLReaderWriter();

		
		  testbutton.setOnAction(event ->{
		  System.out.println("Information button pressed"); });
		  
		 
		deska = xmlInterpretor.odczytaj("state.xml");

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

		refreash();

		deska.start();

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

	}

	@FXML
	void handleOnKeyPressed(KeyEvent event) {
		System.out.println("Klawisz wcisnieto");
		// strzalki

		if (event.getCode() == KeyCode.LEFT) {

			// Declare the timer
			if (!lewyWlaczony && !prawyWlaczony) {
				deska.getStrzalka(0).wlacz();
				t = new Timer();

				lewyWlaczony = true;

				// Set the schedule function and rate

				t.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						// Called each time when 1000 milliseconds (1 second) (the period parameter)
						if (lewaStrzalka.getFill() == Color.WHITE) {
							lewaStrzalka.setFill(Color.GREEN);
						} else
							lewaStrzalka.setFill(Color.WHITE);

					}

				},
						// Set how long before to start calling the TimerTask (in milliseconds)
						0,
						// Set the amount of time between each execution (in milliseconds)
						1000);

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
			// Declare the timer
			if (!prawyWlaczony && !lewyWlaczony) {
				deska.getStrzalka(1).wlacz();

				t = new Timer();

				prawyWlaczony = true;

				// Set the schedule function and rate

				t.scheduleAtFixedRate(new TimerTask() {

					@Override
					public void run() {
						// Called each time when 1000 milliseconds (1 second) (the period parameter)
						if (prawaStrzalka.getFill() == Color.WHITE) {
							prawaStrzalka.setFill(Color.GREEN);
						} else
							prawaStrzalka.setFill(Color.WHITE);

					}

				},
						// Set how long before to start calling the TimerTask (in milliseconds)
						0,
						// Set the amount of time between each execution (in milliseconds)
						1000);

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

		predkosc.setText(Double.toString(deska.getPredkosciomierz().getPredkosc()));

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
