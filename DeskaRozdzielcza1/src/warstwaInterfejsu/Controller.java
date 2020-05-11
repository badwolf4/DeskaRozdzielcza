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
    private Button firstButton;
    
    @FXML
    private Button secondButton;
  
    @FXML
    private Button firstButton2;
    
    @FXML
    private Button secondButton2;
    
    @FXML
    private Circle circle1;
    
    @FXML
    private Circle circle2;
    
    @FXML
    private Circle circle11;
    
    @FXML
    private Circle circle22;
    
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
    
    private boolean lewyWlaczony = false;
    private boolean prawyWlaczony = false;
  
    
    private int  counter = 0;
    
    private DeskaRozdzielcza deska;
    
   // @FXML
    //private SVGPath lewaStrzalka;
    
    @FXML
    void initialize() {
    	XMLReaderWriter xmlInterpretor = new XMLReaderWriter();
    	
    	deska = xmlInterpretor.odczytaj("state.xml");
    	
    	//0
        firstButton.setOnAction(event ->{
        	if(circle1.getFill()==Color.BLUE) {
        		circle1.setFill(Color.WHITE);
        	}
        	else circle1.setFill(Color.BLUE);
        	System.out.println("blue");
        } );
        
        //1
        secondButton.setOnAction(event ->{
        	if(circle2.getFill()==Color.YELLOW) {
        		circle2.setFill(Color.WHITE);
        	}
        	else circle2.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
        } );
        
        //2
        firstButton2.setOnAction(event ->{
        	if(circle11.getFill()==Color.BLUE) {
        		circle11.setFill(Color.WHITE);
        	}
        	else circle11.setFill(Color.BLUE);
        	System.out.println("blue");
        	lewaStrzalka.setFill(Color.WHITE);
        } );
        
        //3
        secondButton2.setOnAction(event ->{
        	if(circle22.getFill()==Color.YELLOW) {
        		circle22.setFill(Color.WHITE);
        	}
        	else circle22.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
    		lewaStrzalka.setFill(Color.GREEN);
        } );
        
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
//		    	if(lewaStrzalka.getFill()==Color.WHITE) {
//	    			lewaStrzalka.setFill(Color.GREEN);
//	        	}
//	        	else lewaStrzalka.setFill(Color.WHITE);
//		    	
//		    	counter++;
//		    	if(counter == 10)
//		    		{
//		    			t1.cancel();
//		    			t1.purge();
//		    		}
		    	deska.getKomputerPokladowy().setCzasPodrozy(deska.getKomputerPokladowy().getCzasPodrozy()+0.01);
		    	refreash();
		    }

		},
		//Set how long before to start calling the TimerTask (in milliseconds)
		0,
		//Set the amount of time between each execution (in milliseconds)
		100);
        
        	
    }
    
    @FXML
    void handleOnKeyPressed(KeyEvent event) {
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
    			deska.getPredkosciomierz().zwolnij();
    		}
    		catch(OsiagnietaMinimalnaSzybkoscException e)
    		{
    			System.out.println(e.getMessage());
    		}
    	}
    	//swiatla
    	if (event.getCode() == KeyCode.Q) {
    		System.out.println("Q pressed");
    		if(circle1.getFill()==Color.BLUE) {
        		circle1.setFill(Color.WHITE);
        	}
        	else circle1.setFill(Color.BLUE);
//    		if(deska.getSwiatlo(0).getWlaczona())
//    			deska.getSwiatlo(0).wylacz();
//    		else
//    			deska.getSwiatlo(0).wylacz();
//        	System.out.println("blue");
    	}
    	
    	if (event.getCode() == KeyCode.E) {
    		System.out.println("E pressed");
    		if(circle2.getFill()==Color.YELLOW) {
        		circle2.setFill(Color.WHITE);
        	}
        	else circle2.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
    	}
    	
    	if (event.getCode() == KeyCode.Z) {
    		System.out.println("Z pressed");
    		if(circle11.getFill()==Color.BLUE) {
        		circle11.setFill(Color.WHITE);
        	}
        	else circle11.setFill(Color.BLUE);
        	System.out.println("blue");
    	}
    	
    	if (event.getCode() == KeyCode.C) {
    		System.out.println("C pressed");
    		if(circle22.getFill()==Color.YELLOW) {
        		circle22.setFill(Color.WHITE);
        	}
        	else circle22.setFill(Color.YELLOW);
        	System.out.println("YELLOW");
    	}
    	
    	refreash();
    	
    }
    
    
    void refreash()
    {
    	przebiegCalkowity.setText(Double.toString(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));
        
        przebiegDzienny.setText(Double.toString(deska.getLicznikPrzebieguDziennego().getPrzebieg()));
        
        
        czasPodrorzy.setText(Double.toString(deska.getKomputerPokladowy().getCzasPodrozy()));
        
        
        dystans.setText(Double.toString(deska.getKomputerPokladowy().getDystans()));
       
 
        spalanie.setText(Double.toString(deska.getKomputerPokladowy().getSrednieSpalanie()));
        
        
        srednia.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscSrednia()));
        
        
        maksymalna.setText(Double.toString(deska.getKomputerPokladowy().getPredkoscMaksymalna()));
        
        
        predkosc.setText(Double.toString(deska.getPredkosciomierz().getPredkosc()));
        
        
//        if(deska.getSwiatlo(0).getWlaczona())
//        	circle1.setFill(Color.BLUE);
//        else
//        	circle1.setFill(Color.WHITE);
//        if(deska.getSwiatlo(1).getWlaczona())
//        	circle2.setFill(Color.YELLOW);
//        else
//        	circle2.setFill(Color.WHITE);
//        if(deska.getSwiatlo(2).getWlaczona())
//        	circle11.setFill(Color.BLUE);
//        else
//        	circle11.setFill(Color.WHITE);
// 		if(deska.getSwiatlo(3).getWlaczona())
// 			circle22.setFill(Color.YELLOW);
// 		else
// 			circle22.setFill(Color.WHITE);
    }
    
}
