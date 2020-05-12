package warstwaInterfejsu;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//import org.xml.sax.helpers.XMLReaderAdapter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
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
    private Button informationButton;
    
    private boolean lewyWlaczony = false;
    private boolean prawyWlaczony = false;
  
    
    private int  counter = 0;
    
    private DeskaRozdzielcza deska;
    
    
    @FXML
    void initialize() {
    	XMLReaderWriter xmlInterpretor = new XMLReaderWriter();
    	
    	informationButton.setOnAction(event ->{
    		System.out.println("Information button pressed");
    	});
    	
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
        //if(deska.getSwiatlo(4).getWlaczona())
        
        Timer t1 = new Timer();
        t1.scheduleAtFixedRate(new TimerTask() {

		    @Override
		    public void run() {
		        //Called each time when 1000 milliseconds (1 second) (the period parameter)
		    
		    	onKomputerTimeValuesChanged();
		    	refreash();
		    }

		},
		//Set how long before to start calling the TimerTask (in milliseconds)
		1000,
		//Set the amount of time between each execution (in milliseconds)
		500);
        
       
        
        //deska.getPredkosciomierz().zwolnij();
    }
    
    void onKomputerTimeValuesChanged()
    {
    	double sekundy = 0.01;
    	double godziny = sekundy/3600;
    	double dystans = deska.getPredkosciomierz().getPredkosc() * godziny;
    	
    	deska.getKomputerPokladowy().setCzasPodrozy(deska.getKomputerPokladowy().getCzasPodrozy()+sekundy);
    	deska.getKomputerPokladowy().setDystans(deska.getKomputerPokladowy().getDystans()+ dystans);
    	deska.getLicznikPrzebieguCalkowitego().zwiekszPrzebieg(dystans);
    	deska.getLicznikPrzebieguDziennego().zwiekszPrzebieg(dystans);
    	deska.getKomputerPokladowy().setPredkoscSrednia(deska.getKomputerPokladowy().getDystans()/(deska.getKomputerPokladowy().getCzasPodrozy()/60));
    	if(deska.getKomputerPokladowy().getPredkoscMaksymalna() < deska.getPredkosciomierz().getPredkosc())
    		deska.getKomputerPokladowy().setPredkoscMaksymalna(deska.getPredkosciomierz().getPredkosc());
    	//TODO spalanie
    	//deska.getKomputerPokladowy().setSrednieSpalanie();
    }
   
    @FXML
    void handleOnKeyPressed(KeyEvent event) {
    	System.out.println("Klawisz wcisnieto");
    	//strzalki
    	counter = 0;
    	
    	if (event.getCode() == KeyCode.LEFT) {
    		
    		//Declare the timer
    		if(!lewyWlaczony)
    		{
    			Timer t = new Timer();
    			 
    			 lewyWlaczony = true;
    			 
        		//Set the schedule function and rate
    			
        		
        		t.scheduleAtFixedRate(new TimerTask() {

        		    @Override
        		    public void run() {
        		        //Called each time when 1000 milliseconds (1 second) (the period parameter)
        		    	if(lewaStrzalka.getFill()==Color.WHITE) {
        	    			lewaStrzalka.setFill(Color.GREEN);
        	        	}
        	        	else lewaStrzalka.setFill(Color.WHITE);
        		    	
        		    	counter++;
        		    	if(counter == 10)
        		    		{
        		    			t.cancel();
        		    			t.purge();
        		    		}
        		    }

        		},
        		//Set how long before to start calling the TimerTask (in milliseconds)
        		0,
        		//Set the amount of time between each execution (in milliseconds)
        		1000);
        		
        		lewaStrzalka.setFill(Color.WHITE);
    		}
    		
    		 
    		
    			
    		
    	}
    	
    	if (event.getCode() == KeyCode.RIGHT) {
    		//Declare the timer
    		if(!prawyWlaczony)
    		{
    			Timer t = new Timer();
    			 
    			prawyWlaczony = true;
    			 
        		//Set the schedule function and rate
    			
        		
        		t.scheduleAtFixedRate(new TimerTask() {

        		    @Override
        		    public void run() {
        		        //Called each time when 1000 milliseconds (1 second) (the period parameter)
        		    	if(prawaStrzalka.getFill()==Color.WHITE) {
        		    		prawaStrzalka.setFill(Color.GREEN);
        	        	}
        	        	else prawaStrzalka.setFill(Color.WHITE);
        		    	
        		    	counter++;
        		    	if(counter == 10)
        		    		{
        		    			t.cancel();
        		    			t.purge();
        		    		}
        		    }

        		},
        		//Set how long before to start calling the TimerTask (in milliseconds)
        		0,
        		//Set the amount of time between each execution (in milliseconds)
        		1000);
        		
        		prawaStrzalka.setFill(Color.WHITE);
        		
    		}
    	}
    	if (event.getCode() == KeyCode.UP)
    	{
    		
    		try
    		{
    			deska.getPredkosciomierz().przyspiesz();
    		}
    		
    		catch(OsiagnietaMaksymalnaSzybkoscException e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	if (event.getCode() == KeyCode.DOWN)
    	{
    		try
    		{
    			deska.getPredkosciomierz().bardzoZwolnij();
    		}
    		catch(OsiagnietaMinimalnaSzybkoscException e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	//swiatla
    	if (event.getCode() == KeyCode.Q) {
    		System.out.println("Q pressed");
//    		if(swiatloDrogowe.getFill()==Color.BLUE) {
//        		swiatloDrogowe.setFill(Color.WHITE);
//        	}
//        	else swiatloDrogowe.setFill(Color.BLUE);
    		if(!deska.getSwiatlo(0).getWlaczona())
    			deska.getSwiatlo(0).wlacz();
    		if(deska.getSwiatlo(0).getWlaczona())
    			deska.getSwiatlo(0).wylacz();
    	}
    	
    	if (event.getCode() == KeyCode.E) {
    		System.out.println("E pressed");
    		if(swiatloMijania.getFill()==Color.YELLOW) {
        		swiatloMijania.setFill(Color.WHITE);
        	}
        	else swiatloMijania.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
    	}
    	
    	if (event.getCode() == KeyCode.Z) {
    		System.out.println("Z pressed");
    		if(swiatloPrzod.getFill()==Color.BLUE) {
        		swiatloPrzod.setFill(Color.WHITE);
        	}
        	else swiatloPrzod.setFill(Color.BLUE);
        	System.out.println("blue");
    	}
    	
    	if (event.getCode() == KeyCode.X) {
    		System.out.println("X pressed");
    		if(swiatloTyl.getFill()==Color.YELLOW) {
        		swiatloTyl.setFill(Color.WHITE);
        	}
        	else swiatloTyl.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
    	}
    	if(event.getCode()==KeyCode.C)
    	{
    		System.out.println("C pressed");
    		if(swiatloPozycyjne.getFill()==Color.RED) {
    			swiatloPozycyjne.setFill(Color.WHITE);
        	}
        	else swiatloPozycyjne.setFill(Color.RED);
    	}
    	
    	refreash();
    	
    }
    
    
    void refreash()
    {
    	//System.out.println("deska refreash");
    	przebiegCalkowity.setText(Double.toString(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));
        
        przebiegDzienny.setText(Double.toString(deska.getLicznikPrzebieguDziennego().getPrzebieg()));
        
        
        czasPodrorzy.setText(Double.toString(deska.getKomputerPokladowy().getCzasPodrozy()));
        
        
        dystans.setText(Double.toString(deska.getKomputerPokladowy().getDystans()));
       
 
        spalanie.setText(Double.toString(deska.getKomputerPokladowy().getSrednieSpalanie()));
        
        
        srednia.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscSrednia()));
        
        
        maksymalna.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscMaksymalna()));
        
        
        predkosc.setText(Double.toString(deska.getPredkosciomierz().getPredkosc()));
        
        
        if(deska.getSwiatlo(0).getWlaczona())
        	
        	swiatloDrogowe.setFill(Color.BLUE);
        else 
        	swiatloDrogowe.setFill(Color.WHITE);
//        if(deska.getSwiatlo(1).getWlaczona())
//        	swiatloMijania.setFill(Color.YELLOW);
//        else
//        	swiatloMijania.setFill(Color.WHITE);
//        if(deska.getSwiatlo(2).getWlaczona())
//        	swiatloPrzod.setFill(Color.BLUE);
//        else
//        	swiatloPrzod.setFill(Color.WHITE);
// 		if(deska.getSwiatlo(3).getWlaczona())
// 			swiatloTyl.setFill(Color.YELLOW);
// 		else
// 			swiatloTyl.setFill(Color.WHITE);
    }
    
}
