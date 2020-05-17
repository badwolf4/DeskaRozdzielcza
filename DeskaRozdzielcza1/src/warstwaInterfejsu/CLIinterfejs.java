package warstwaInterfejsu;



import java.util.ArrayList;
import java.util.Scanner;

import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;
import warstwaLogiki.XMLReaderWriter;

public class CLIinterfejs {
	private DeskaRozdzielcza deska;
	private String wczytajZ;
	private String zapiszDo;
	
	public CLIinterfejs()
	{
		String wczytajZ = null;
		String zapiszDo = null;
	}
	
	public void pokazStartMenu()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("--------Menu---------");
		System.out.println("1 - Wczytaj z XML");
		System.out.println("2 - Wczytaj z bazy danych");
		do
		{
			wczytajZ = scan.nextLine();
			
		}while(!wczytajZ.equals("1") && !wczytajZ.equals("2"));
		
		System.out.println("1 - Zapisz do XML");
		System.out.println("2 - Zapisz do bazy danych");
		do
		{
			zapiszDo  = scan.nextLine();
			
		}while(!zapiszDo.equals("1") && !zapiszDo.equals("2"));
		
		
		
	}
	
	public void start()
	{
		Scanner scan = new Scanner(System.in);
		String wybor = null;
		XMLReaderWriter rwXML = new XMLReaderWriter();
		
		pokazStartMenu();
		if(wczytajZ.equals("1"))
			deska = rwXML.odczytaj("state.xml");
		if(wczytajZ.equals("2"))
			return;
		
		deska.start();
		do {
			System.out.println("------Menu--------");
			
			System.out.println("1 - pokaz stan deski");
			System.out.println("2 - przejdz do trybu edycji");
			System.out.println("3 - wyjsc z aplikacji");
			do {
				wybor = scan.nextLine();
				
			}while(!wybor.equals("1") && !wybor.equals("2") && !wybor.equals("3"));
			
			if(wybor.equals("1"))
				pokazStanDeski();
			if(wybor.equals("2"))
				trybEdycji();			
		}while(!wybor.equals("3"));
		
		if(zapiszDo.equals("1"))
		{
			try {
				rwXML.zapisz(deska);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		if(zapiszDo.equals("2"))
			return;
			
	}
	
	public void pokazStanDeski()
	{
		System.out.println("---------Deska Rozdzielcza------------");
		System.out.println("Predkosc: " + deska.getPredkosciomierz().getPredkosc());
		System.out.println("Przebieg dzienny: " + deska.getLicznikPrzebieguDziennego().getPrzebieg());
		System.out.println("Przebieg calkowity: " + deska.getLicznikPrzebieguCalkowitego().getPrzebieg());
		ArrayList<String> swiatla = new ArrayList<String>(); 
		for( int i = 0; i < 2; i++)
		{
			if(deska.getStrzalka(i).getWlaczona())
				swiatla.add("wlaczony");
			else
				swiatla.add("wylaczony");
		}
		
		for( int i = 0; i < 5; i++)
		{
			if(deska.getSwiatlo(i).getWlaczona())
				swiatla.add("wlaczony");
			else
				swiatla.add("wylaczony");
		}
		System.out.println("Kierunkowskaz lewo: " + swiatla.get(0));
		System.out.println("Kierunkowskaz prawo: " + swiatla.get(1));

		System.out.println("Swiatla drogowe: " + swiatla.get(2));
		System.out.println("Swiatla mijania: " + swiatla.get(3));
		System.out.println("Swiatla przeciwmgielne przod: " + swiatla.get(4));
		System.out.println("Swiatla przeciwmgelne tyl: " + swiatla.get(5));
		System.out.println("Swiatla pozycyjne: " + swiatla.get(6));

		System.out.println("Swiatla pozycyjne: " + swiatla.get(2));
		System.out.println("Swiatla mijania: " + swiatla.get(3));
		System.out.println("Swiatla drogowe: " + swiatla.get(4));
		System.out.println("Swiatla przeciwmgielne przod: " + swiatla.get(5));
		System.out.println("Swiatla przeciwmgelne tyl: " + swiatla.get(6));

		
		System.out.println("----Komputer pokladowy-------");
		System.out.println("Predkosc srednia: " + deska.getKomputerPokladowy().getPredkoscSrednia());
		System.out.println("Predkosc maksymalna: " + deska.getKomputerPokladowy().getPredkoscMaksymalna());
		System.out.println("Czas podrozy: " + deska.getKomputerPokladowy().getCzasPodrozy());
		System.out.println("Dystans: " + deska.getKomputerPokladowy().getDystans());
		System.out.println("Srednie spalanie: " + deska.getKomputerPokladowy().getSrednieSpalanie());
	}
	
	public void wypiszPomoc()
	{
		System.out.println("W - zwieksz szybosc");
		System.out.println("S - zmniejsz szybkosc");
		System.out.println("A - wlacz/wylacz lewy kierunkowskaz");
		System.out.println("D - wlacz/wylacz prawy kierunkowskaz");
		System.out.println("Q - wlacz/wylacz kontrolke swiatel pozycyjnych");
		System.out.println("E - wlacz/wylacz kontrolke swiatel mijania");
		System.out.println("Z - wlacz/wylacz kontrolke swiatel drogowych");
		System.out.println("X - wlacz/wylacz kontrolke swiatel przeciwmgielnych przod");
		System.out.println("C - wlacz/wylacz kontrolke swiatel przeciwmgielnych tyl");
		System.out.println("H - wyswietl pomoc");
		System.out.println("P - wyjdz");
	}
	
	public void trybEdycji()
	{
		String wybor = null;
		Scanner scan = new Scanner(System.in);
		
		wypiszPomoc();
		do {
			
			wybor = scan.nextLine();
			switch(wybor) {
			case "w":
				{
					try {
						deska.getPredkosciomierz().przyspiesz();
					} catch (OsiagnietaMaksymalnaSzybkoscException e) {
						
						e.printStackTrace();
					}
					System.out.println("Predkosc: " + deska.getPredkosciomierz().getPredkosc());
					break;
				}
			case "s":
				{
					try {
						deska.getPredkosciomierz().zwolnij();
					} catch (OsiagnietaMinimalnaSzybkoscException e) {
						e.printStackTrace();
					}
					System.out.println("Predkosc: " + deska.getPredkosciomierz().getPredkosc());
					break;
				}
			case "a":
				{
					if(deska.getStrzalka(0).getWlaczona())
						{
							deska.getStrzalka(0).wylacz();
							System.out.println("Lewy kierunkowskaz: wylaczony");
						}
					else
						{
							deska.getStrzalka(0).wlacz();
							System.out.println("Lewy kierunkowskaz: wlaczony");
						}
					break;
				}
			case "d":
				{
					if(deska.getStrzalka(1).getWlaczona())
					{
						deska.getStrzalka(1).wylacz();
						System.out.println("Prawy kierunkowskaz: wylaczony");
					}
				else
					{
						deska.getStrzalka(1).wlacz();
						System.out.println("Prawy kierunkowskaz: wlaczony");
					}
					break;
				}
			case "q":
				{
					if(deska.getSwiatlo(0).getWlaczona())
					{
						deska.getSwiatlo(0).wylacz();
						System.out.println("Swiatla pozycyjne: wylaczony");
					}
				else
					{
						deska.getSwiatlo(0).wlacz();
						System.out.println("Swiatla pozycyjne: wlaczony");
					}
					break;
				}
			case "e":
				{
					if(deska.getSwiatlo(1).getWlaczona())
					{
						deska.getSwiatlo(1).wylacz();
						System.out.println("Swiatla mijania: wylaczony");
					}
				else
					{
						deska.getSwiatlo(1).wlacz();
						System.out.println("Swiatla mijania: wlaczony");
					}
					break;
				}
			case "z":
				{
					if(deska.getSwiatlo(2).getWlaczona())
					{
						deska.getSwiatlo(2).wylacz();
						System.out.println("Swiatla drogowe: wylaczony");
					}
				else
					{
						deska.getSwiatlo(2).wlacz();
						System.out.println("Swiatla drogowe: wlaczony");
					}
					break;
				}
			case "x":
				{
					if(deska.getSwiatlo(3).getWlaczona())
					{
						deska.getSwiatlo(3).wylacz();
						System.out.println("Przeciwmgielne przod: wylaczony");
					}
				else
					{
						deska.getSwiatlo(3).wlacz();
						System.out.println("Przeciwmgielne przod: wlaczony");
					}
					break;
				}
			case "c":
				{
					if(deska.getSwiatlo(4).getWlaczona())
					{
						deska.getSwiatlo(4).wylacz();
						System.out.println("Przeciwmgielne tyl: wylaczony");
					}
				else
					{
						deska.getSwiatlo(4).wlacz();
						System.out.println("Przeciwmgielne tyl: wlaczony");
					}
					break;
				}
			case "h":
			{
				wypiszPomoc();
				break;
			}
			default:
				{
					break;
				}
			}
		}while(!wybor.equals("p"));
	}

}



