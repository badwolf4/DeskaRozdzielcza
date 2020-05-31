package warstwaInterfejsu;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

import warstwaLogiki.DatabaseHandler;
import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;
import warstwaLogiki.Swiatla;
import warstwaLogiki.SwiatlaKierunkowskazow;
import warstwaLogiki.XMLReaderWriter;

/**
 * Klasa reprezentująca uposzczony interfejs w konsoli
 */
public class CLIinterfejs extends Thread{
	private DeskaRozdzielcza deska;
	private String wczytajZ;
	private String zapiszDo;
	
	/**
	 * Tworzenie nowej instancji klasy CLIinterfejs
	 */
	public CLIinterfejs()
	{
		
	}
	
	/**
	 * Główna metoda służąca do interakcji z użytkownikiem
	 */
	
	public void run()
	{
		Scanner scan = new Scanner(System.in);
		String wybor = null;
		XMLReaderWriter rwXML = new XMLReaderWriter();
		DatabaseHandler handler = new DatabaseHandler();
		
		pokazStartMenu();
		
		if(wczytajZ.equals("1"))
			deska = rwXML.odczytaj("state.xml");
		if(wczytajZ.equals("2"))
			deska = handler.wczytajZBD();
		
		deska.start();
		do {
			System.out.println("\n------Menu--------");
			
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
		{
			handler.zapisacDoBD(deska);
		}
		
		scan.close();
		interrupt();
		System.exit(0);
			
	}
	
	/**
	 * Wyświetlenie startowego menu
	 */
	public void pokazStartMenu()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.println("\n--------Menu---------");
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
	
	/**
	 * Wyświetlenie stanu deski
	 */
	
	public void pokazStanDeski()
	{
		System.out.println("\n---------Deska Rozdzielcza------------");
		System.out.println("Predkosc: " + (int)Math.abs(deska.getPredkosciomierz().getPredkosc()));
		System.out.println("Przebieg dzienny: " + bd(deska.getLicznikPrzebieguDziennego().getPrzebieg()));
		System.out.println("Przebieg calkowity: " + bd(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));
		ArrayList<String> swiatla = new ArrayList<String>(); 
		
		if(deska.getStrzalka(SwiatlaKierunkowskazow.lewo).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getStrzalka(SwiatlaKierunkowskazow.prawo).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getSwiatlo(Swiatla.pozycyjne).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getSwiatlo(Swiatla.mijania).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getSwiatlo(Swiatla.drogowe).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getSwiatlo(Swiatla.przeciwmgelnePrzod).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		if(deska.getSwiatlo(Swiatla.przeciwmgelneTyl).getWlaczona())
			swiatla.add("wlaczony");
		else
			swiatla.add("wylaczony");
		
		System.out.println("Kierunkowskaz lewo: " + swiatla.get(0));
		System.out.println("Kierunkowskaz prawo: " + swiatla.get(1));


		System.out.println("Swiatla pozycyjne: " + swiatla.get(2));
		System.out.println("Swiatla mijania: " + swiatla.get(3));
		System.out.println("Swiatla drogowe: " + swiatla.get(4));
		System.out.println("Swiatla przeciwmgielne przod: " + swiatla.get(5));
		System.out.println("Swiatla przeciwmgelne tyl: " + swiatla.get(6));

		
		System.out.println("\n----Komputer pokladowy-------");
		System.out.println("Predkosc srednia: " + bd(deska.getKomputerPokladowy().getPredkoscSrednia()));
		System.out.println("Predkosc maksymalna: " + (int)Math.abs(deska.getKomputerPokladowy().getPredkoscMaksymalna()));
		System.out.println("Czas podrozy: " + bd(deska.getKomputerPokladowy().getCzasPodrozy()));
		System.out.println("Dystans: " + bd(deska.getKomputerPokladowy().getDystans()));
		System.out.println("Srednie spalanie: " + bd(deska.getKomputerPokladowy().getSrednieSpalanie()));
	}
	
	/**
	 * Wyświetlenie pomocy dla trybu edycji
	 */
	
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
	
	/**
	 * Modyfikacja zawartości deski rozdzielczej, tzw. tryb edycji
	 */
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
					if(deska.getStrzalka(SwiatlaKierunkowskazow.lewo).getWlaczona())
						{
							deska.getStrzalka(SwiatlaKierunkowskazow.lewo).wylacz();
							System.out.println("Lewy kierunkowskaz: wylaczony");
						}
					else
						{
							deska.getStrzalka(SwiatlaKierunkowskazow.lewo).wlacz();
							System.out.println("Lewy kierunkowskaz: wlaczony");
						}
					break;
				}
			case "d":
				{
					if(deska.getStrzalka(SwiatlaKierunkowskazow.prawo).getWlaczona())
					{
						deska.getStrzalka(SwiatlaKierunkowskazow.prawo).wylacz();
						System.out.println("Prawy kierunkowskaz: wylaczony");
					}
				else
					{
						deska.getStrzalka(SwiatlaKierunkowskazow.prawo).wlacz();
						System.out.println("Prawy kierunkowskaz: wlaczony");
					}
					break;
				}
			case "q":
				{
					if(deska.getSwiatlo(Swiatla.pozycyjne).getWlaczona())
					{
						deska.getSwiatlo(Swiatla.pozycyjne).wylacz();
						System.out.println("Swiatla pozycyjne: wylaczony");
					}
				else
					{
						deska.getSwiatlo(Swiatla.pozycyjne).wlacz();
						System.out.println("Swiatla pozycyjne: wlaczony");
					}
					break;
				}
			case "e":
				{
					if(deska.getSwiatlo(Swiatla.mijania).getWlaczona())
					{
						deska.getSwiatlo(Swiatla.mijania).wylacz();
						System.out.println("Swiatla mijania: wylaczony");
					}
				else
					{
						deska.getSwiatlo(Swiatla.mijania).wlacz();
						System.out.println("Swiatla mijania: wlaczony");
					}
					break;
				}
			case "z":
				{
					if(deska.getSwiatlo(Swiatla.drogowe).getWlaczona())
					{
						deska.getSwiatlo(Swiatla.drogowe).wylacz();
						System.out.println("Swiatla drogowe: wylaczony");
					}
				else
					{
						deska.getSwiatlo(Swiatla.drogowe).wlacz();
						System.out.println("Swiatla drogowe: wlaczony");
					}
					break;
				}
			case "x":
				{
					if(deska.getSwiatlo(Swiatla.przeciwmgelnePrzod).getWlaczona())
					{
						deska.getSwiatlo(Swiatla.przeciwmgelnePrzod).wylacz();
						System.out.println("Przeciwmgielne przod: wylaczony");
					}
				else
					{
						deska.getSwiatlo(Swiatla.przeciwmgelnePrzod).wlacz();
						System.out.println("Przeciwmgielne przod: wlaczony");
					}
					break;
				}
			case "c":
				{
					if(deska.getSwiatlo(Swiatla.przeciwmgelneTyl).getWlaczona())
					{
						deska.getSwiatlo(Swiatla.przeciwmgelneTyl).wylacz();
						System.out.println("Przeciwmgielne tyl: wylaczony");
					}
				else
					{
						deska.getSwiatlo(Swiatla.przeciwmgelneTyl).wlacz();
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
		return;
	}
	
	/**
	 * Zaokrąglenie wartości do dwu miejsc po przycinku
	 * @param input wartość która będzie poddana zaokrągleniu
	 * @return double
	 */

	public double bd(double input) {
		return new BigDecimal(input).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}



