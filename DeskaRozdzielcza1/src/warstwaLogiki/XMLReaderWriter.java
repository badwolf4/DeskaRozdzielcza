package warstwaLogiki;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import warstwaDanych.LicznikPrzebieguCalkowitego;
import warstwaDanych.LicznikPrzebieguDziennego;
import warstwaDanych.Predkosciomierz;



public class XMLReaderWriter {
	
	
	
	public XMLReaderWriter(){ }
	
	public DeskaRozdzielcza odczytaj(String file)
	{
		DeskaRozdzielcza deska = null;
		KomputerPokladowy komputer = null;
		boolean stan = false;
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

            //otwieramy plik xml i tworzymy nowy evenReader
            InputStream in = new FileInputStream(file);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            
            while(eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                
                if(event.isStartElement()) {
                	StartElement startElement = event.asStartElement();
                	
                	if(startElement.getName().getLocalPart().contentEquals("deska_rozdzielcza")) {
                        deska = new DeskaRozdzielcza();

                    }
                	
                	if(startElement.getName().getLocalPart().contentEquals("komputer_pokladowy")) {
                        komputer = new KomputerPokladowy();

                    }
                
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("predkosc")) {
                            event = eventReader.nextEvent();
                            deska.setPredkosciomierz(new Predkosciomierz(Integer.parseInt(event.asCharacters().getData())));
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("przebieg_calkowity")) {
                            event = eventReader.nextEvent();
                            deska.setLicznikPrzebieguCalkowitego(new LicznikPrzebieguCalkowitego(Double.parseDouble(event.asCharacters().getData())));
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("przebieg_dzienny")) {
                            event = eventReader.nextEvent();
                            deska.setLicznikPrzebieguDziennego(new LicznikPrzebieguDziennego(Double.parseDouble(event.asCharacters().getData())));
                            continue;
                        }
                    }
                	
                	//lewy_kierunkowskaz
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("lewy_kierunkowskaz")) {
                            event = eventReader.nextEvent();
                            
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            {
                            	stan = true;
                            	System.out.println("wlaczony");
                            }
                       
                            else
                            	stan = false;
                            System.out.println("Pobrany stan: " + stan);
                            deska.setStrzalka(0,stan);     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("prawy_kierunkowskaz")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setStrzalka(1,stan);     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("kontrolka_swiatel_pozycyjnych")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setSwiatlo(0, stan);     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("kontrolka_swiatel_mijania")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setSwiatlo(1, stan);     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("kontrolka_swiatel_drogowych")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setSwiatlo(2, stan);     
                            continue;
                        }
                    }
                	
                	//kontrolka_swiatel_przeciwmgielnych_przod
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("kontrolka_swiatel_przeciwmgielnych_przod")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setSwiatlo(3, stan);     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("kontrolka_swiatel_przeciwmgielnych_tyl")) {
                            event = eventReader.nextEvent();
                            if(String.valueOf(event.asCharacters()).equals("wlaczony"))
                            	stan = true;
                            else
                            	stan = false;
                            deska.setSwiatlo(4,stan);     
                            continue;
                        }
                    }
                	
                	
                	//predkosc_srednia
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("predkosc_srednia")) {
                            event = eventReader.nextEvent();
                            komputer.setPredkoscSrednia(Double.parseDouble(event.asCharacters().getData()));     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("predkosc_maksymalna")) {
                            event = eventReader.nextEvent();
                            komputer.setPredkoscMaksymalna(Double.parseDouble(event.asCharacters().getData()));     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("czas_podrozy")) {
                            event = eventReader.nextEvent();
                            komputer.setCzasPodrozy(Double.parseDouble(event.asCharacters().getData()));     
                            continue;
                        }
                    }
                	
                	//dystans
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("dystans")) {
                            event = eventReader.nextEvent();
                            komputer.setDystans(Double.parseDouble(event.asCharacters().getData()));     
                            continue;
                        }
                    }
                	
                	if(event.isStartElement()) {
                        if(event.asStartElement().getName().getLocalPart().equals("srednie_spalanie")) {
                            event = eventReader.nextEvent();
                            komputer.setSrednieSpalanie(Double.parseDouble(event.asCharacters().getData()));     
                            continue;
                        }
                    }
                	
                }
                
                if(event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if(endElement.getName().getLocalPart().contentEquals("komputer_pokladowy")) {
                    	deska.setKomputerPokladowy(komputer);
                    }
                }
            }
		}
		catch(FileNotFoundException e) {
            System.out.println("Plik nie znaleziony");
            e.printStackTrace();
        }
        catch(XMLStreamException e) {
            System.out.println("Blad strumienia xml");
            e.printStackTrace();
        }
		return deska;
	}
	
	public void zapisz(DeskaRozdzielcza deska) throws Exception
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = factory.createXMLEventWriter(new FileOutputStream("output.xml"));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		DTD tab = eventFactory.createDTD("\t");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		
		// <deska_rozdzielcza>
		StartElement startElement = eventFactory.createStartElement("", "", "deska_rozdzielcza");
		eventWriter.add(startElement);
		eventWriter.add(end);
		
		//<predkosc> wartosc </predkosc>
		createNode(eventWriter,"predkosc", Integer.toString(deska.getPredkosciomierz().getPredkosc()));
	
		
		//<przebieg_calkowity> wartosc </przebieg_calkowity>
		createNode(eventWriter,"przebieg_calkowity", Double.toString(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));
		
		//<przebieg_dzienny> wartosc </przebieg_dzienny>
		createNode(eventWriter,"przebieg_dzienny", Double.toString(deska.getLicznikPrzebieguDziennego().getPrzebieg()));
		
		//<lewy_kierunkowskaz> stan </lewy_kierunkowskaz>
		createNode(eventWriter,"lewy_kierunkowskaz", Boolean.toString(deska.getStrzalka(0).getWlaczona()));
		
		//<prawy_kierunkowskaz> stan </prawy_kierunkowskaz>
		createNode(eventWriter,"prawy_kierunkowskaz", Boolean.toString(deska.getStrzalka(1).getWlaczona()));
		
		//<kontrolka_swiatel_pozycyjnych> stan </kontrolka_swiatel_pozycyjnych>
		createNode(eventWriter,"kontrolka_swiatel_pozycyjnych", Boolean.toString(deska.getSwiatlo(0).getWlaczona()));
		
		//<kontrolka_swiatel_mijania> stan </kontrolka_swiatel_mijania>
		createNode(eventWriter,"kontrolka_swiatel_mijania", Boolean.toString(deska.getSwiatlo(1).getWlaczona()));
		
		//<kontrolka_swiatel_drogowych> stan </kontrolka_swiatel_drogowych>
		createNode(eventWriter,"kontrolka_swiatel_drogowych", Boolean.toString(deska.getSwiatlo(2).getWlaczona()));
		
		//<kontrolka_swiatel_przeciwmgielnych_przod> stan </kontrolka_swiatel_przeciwmgielnych_przod>
		createNode(eventWriter,"kontrolka_swiatel_przeciwmgielnych_przod", Boolean.toString(deska.getSwiatlo(3).getWlaczona()));
		
		//<kontrolka_swiatel_przeciwmgielnych_tyl> stan </kontrolka_swiatel_przeciwmgielnych_tyl>
		createNode(eventWriter,"kontrolka_swiatel_przeciwmgielnych_tyl", Boolean.toString(deska.getSwiatlo(4).getWlaczona()));
		
		eventWriter.add(tab);
		//<komputer_pokladowy> 
		startElement = eventFactory.createStartElement("", "", "komputer_pokladowy");
		eventWriter.add(startElement);
		eventWriter.add(end);
		eventWriter.add(tab);
		
		//<predkosc_srednia> wartosc </predkosc_srednia>
		createNode(eventWriter,"predkosc_srednia", Double.toString(deska.getKomputerPokladowy().getPredkoscSrednia()));
		eventWriter.add(tab);
		
		//<predkosc_maksymalna> wartosc </predkosc_maksymalna>
		createNode(eventWriter,"predkosc_maksymalna", Double.toString(deska.getKomputerPokladowy().getPredkoscMaksymalna()));
		eventWriter.add(tab);
		
		//<czas_podrozy> wartosc </czas_podrozy>
		createNode(eventWriter,"czas_podrozy", Double.toString(deska.getKomputerPokladowy().getCzasPodrozy()));
		eventWriter.add(tab);
		
		//<dystans> wartosc </dystans>
		createNode(eventWriter,"dystans", Double.toString(deska.getKomputerPokladowy().getDystans()));
		eventWriter.add(tab);
		
		//<srednie_spalanie> wartosc </srednie_spalanie>
		createNode(eventWriter,"srednie_spalanie", Double.toString(deska.getKomputerPokladowy().getSrednieSpalanie()));
		eventWriter.add(tab);
		
		// </komputer_pokladowy> 
		eventWriter.add(eventFactory.createEndElement("", "", "komputer_pokladowy"));
		eventWriter.add(end);
		
		//</deska_rozdzielcza>
		eventWriter.add(eventFactory.createEndElement("", "", "deska_rozdzielcza"));
		eventWriter.add(end);
		

		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
	}
	
	private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		DTD end = eventFactory.createDTD("\n");
		DTD tab = eventFactory.createDTD("\t");
		
		if(value.equals("true"))
			value = "wlaczony";
		if(value.equals("false"))
			value = "wylaczony";
		
		StartElement sElement = eventFactory.createStartElement("",  "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
	
	public void odczytaj()
	{
		
	}

}
