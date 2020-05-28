package warstwaLogiki;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import warstwaDanych.KontrolkaKierunkowskazu;
import warstwaDanych.KontrolkaSwiatel;
import warstwaDanych.LicznikPrzebieguCalkowitego;
import warstwaDanych.LicznikPrzebieguDziennego;
import warstwaDanych.Predkosciomierz;

/**
 * Klasa służąca do przechowywania wszytskich parametrów deski rozdzielczej.  
 */
public class DeskaRozdzielcza {
	private Predkosciomierz predkosciomierz;
	private LicznikPrzebieguCalkowitego przebiegCalkowity;
	private LicznikPrzebieguDziennego przebiegDzienny;
	//lewo, prawo
	private ArrayList<KontrolkaKierunkowskazu> strzalki;
	private ArrayList<KontrolkaSwiatel> swiatla;
	//pozycyjnych, mijania, drogowych, przeciwmgelnychPrzod, przeciwmgelnychTyl
	private KomputerPokladowy komputer;
	private Timer t3;
	
	/**
	 * Tworzenie nowej instancji klasy DeskaRozdzielcza, bezparametrowa
	 */
	public DeskaRozdzielcza()
	{
		strzalki = new ArrayList<KontrolkaKierunkowskazu>();
		strzalki.add(new KontrolkaKierunkowskazu(false));
		strzalki.add(new KontrolkaKierunkowskazu(false));
		swiatla = new ArrayList<KontrolkaSwiatel>();
		swiatla.add(new KontrolkaSwiatel(false));
		swiatla.add(new KontrolkaSwiatel(false));
		swiatla.add(new KontrolkaSwiatel(false));
		swiatla.add(new KontrolkaSwiatel(false));
		swiatla.add(new KontrolkaSwiatel(false));
	}
	
	/**
	 * Tworzenia nowej instancji klasy DeskaRozdzielcza
	 * @param predkosciomierz predkosciomierz
	 * @param przebiegCalkowity licznik przebiegu całkowitego
	 * @param przebiegDzienny licznik przebiegu dziennego
	 * @param prawo kierunkowskaz prawy
	 * @param lewo kierunkowskaz lewy
	 * @param pozycyjnych kontrolka świateł pozycyjnych
	 * @param mijania kontrolka świateł mijania
	 * @param drogowych kontrolka świateł drogowych
	 * @param przeciwmgelnychPrzod kontrolka świateł przeciwmgielnych przód
	 * @param przeciwmgelnychTyl kontrolka świateł przeciwmgielnych tył
	 * @param komputer komputer pokładowy
	 */
	public DeskaRozdzielcza(Predkosciomierz predkosciomierz,LicznikPrzebieguCalkowitego przebiegCalkowity,
			LicznikPrzebieguDziennego przebiegDzienny, boolean prawo, boolean lewo,
			boolean  pozycyjnych, boolean mijania, boolean drogowych,boolean przeciwmgelnychPrzod,
			boolean  przeciwmgelnychTyl, KomputerPokladowy  komputer)
	{
		this.predkosciomierz = predkosciomierz;
		this.przebiegCalkowity = przebiegCalkowity;
		this.przebiegDzienny = przebiegDzienny;
		
		strzalki = new ArrayList<KontrolkaKierunkowskazu>();
		strzalki.add(0,new KontrolkaKierunkowskazu(prawo));
		strzalki.add(1,new KontrolkaKierunkowskazu(lewo));
		
		swiatla = new ArrayList<KontrolkaSwiatel>();
		swiatla.add(0,new KontrolkaSwiatel(pozycyjnych));
		swiatla.add(1,new KontrolkaSwiatel(mijania));
		swiatla.add(2,new KontrolkaSwiatel(drogowych));
		swiatla.add(3,new KontrolkaSwiatel(przeciwmgelnychPrzod));
		swiatla.add(4,new KontrolkaSwiatel(przeciwmgelnychTyl));
		this.komputer = komputer;
	}
	
	/**
	 * Uruchomienie wątku do aktualizacji danych w desce
	 */
	public void start()
	{
		t3 = new Timer();
		
		 t3.schedule(new TimerTask() {

			    @Override
			    public void run() {
			    	refreashDeska();
			    	
			    	try {
						predkosciomierz.zwolnij();
					} catch (OsiagnietaMinimalnaSzybkoscException e) {
					}
			    }

			},0,1000);
	}
	/**
	 * 
	 * Zatrzymywanie wątku aktualizującego dane deski w czasie
	 */
	public void stop()
	{
		t3.cancel();
	}
	
	/**
	 * Obliczenie aktualizowanych danych
	 */
	public void refreashDeska()
	{
		double sekundy = 1;
		double godziny = sekundy/3600;
		double dystans = getPredkosciomierz().getPredkosc() * godziny;
        

    	przebiegCalkowity.zwiekszPrzebieg(dystans);
    	przebiegDzienny.zwiekszPrzebieg(dystans);
    	komputer.refreashKomputer(sekundy, godziny,dystans,predkosciomierz.getPredkosc());
	}
	
	/**
	 * Zamienia objekt predkościomierz
	 * @param p nowy obiekt reprezentujący pole predkosciomierz
	 */
	public void setPredkosciomierz(Predkosciomierz p)
	{
		predkosciomierz = p;
	}
	
	/**
	 * Zwraca Predkosciomierz
	 * @return Predkoscomierz
	 */
	public Predkosciomierz getPredkosciomierz()
	{
		return predkosciomierz;
	}
	
	/**
	 * Zamienia objekt przebiegCalkowity
	 * @param p nowy obiekt reprezentujący pole przebiegCalkowity
	 */
	public void setLicznikPrzebieguCalkowitego(LicznikPrzebieguCalkowitego p)
	{
		przebiegCalkowity = p;
	}
	
	/**
	 * Zwraca przebiegCalkowity
	 * @return LicznikPrzebieguCalkowitego
	 */
	public LicznikPrzebieguCalkowitego getLicznikPrzebieguCalkowitego()
	{
		return przebiegCalkowity;
	}
	
	/**
	 * Zamienia objekt przebiegDzienny
	 * @param p nowy obiekt reprezentujący pole przebiegDzienny
	 */
	public void setLicznikPrzebieguDziennego(LicznikPrzebieguDziennego p)
	{
		przebiegDzienny  = p;
	}
	
	/**
	 * Zwraca przebiegDzienny
	 * @return LicznikPrzebieguDziennego
	 */
	public LicznikPrzebieguDziennego getLicznikPrzebieguDziennego()
	{
		return przebiegDzienny;
	}
	
	/**
	 * Zamienia objekt komputer
	 * @param p nowy obiekt reprezentujący pole komputer
	 */
	public void setKomputerPokladowy(KomputerPokladowy p)
	{
		komputer  = p;
	}
	
	/**
	 * Zwraca komputer
	 * @return KomputerPokladowy
	 */
	public KomputerPokladowy getKomputerPokladowy()
	{
		return komputer;
	}

	/**
	 * Zamienia obiekt KontrolkaSwiatel
	 * @param s nazwa kontrolki
	 * @param stan stan kontrolki
	 */
	public void setSwiatlo(Swiatla s, boolean stan)
	{
		switch(s)
		{
			case pozycyjne:
			{
				swiatla.set(0, new KontrolkaSwiatel(stan));
				break;
			}
			case mijania:
			{
				swiatla.set(1, new KontrolkaSwiatel(stan));
				break;
			}
			case drogowe:
			{
				swiatla.set(2, new KontrolkaSwiatel(stan));
				break;
			}
			case przeciwmgelnePrzod:
			{
				swiatla.set(3, new KontrolkaSwiatel(stan));
				break;
			}
			case przeciwmgelneTyl:
			{
				swiatla.set(4, new KontrolkaSwiatel(stan));
				break;
			}
		}
	}
	
	
	/**
	 * Zwraca obiekt reprezentujący kontrolkę świateł o odpowiedziej nazwie
	 * @param s nazwa kontrolki
	 * @return KontrolkaSwiatel obiekt z listy
	 */
	public KontrolkaSwiatel getSwiatlo(Swiatla s)
	{
		switch(s)
		{
			case pozycyjne:
			{
				return swiatla.get(0);
			}
			case mijania:
			{
				return swiatla.get(1);
			}
			case drogowe:
			{
				return swiatla.get(2);
			}
			case przeciwmgelnePrzod:
			{
				return swiatla.get(3);
			}
			case przeciwmgelneTyl:
			{
				return swiatla.get(4);
			}
		}
		return swiatla.get(0);
	}
	

	/**
	 * Zamienia obiekt reprezentujący kontrolkę kierunkowskazu o odpowiedniej nazwie
	 * @param s nazwa kontrolki
	 * @param stan stan kontrolki
	 */
	public void setStrzalka(SwiatlaKierunkowskazow s, boolean stan)
	{
		switch(s) {
			case lewo:
			{
				strzalki.set(0, new KontrolkaKierunkowskazu(stan));
				break;
			}
			case prawo:
			{
				strzalki.set(1, new KontrolkaKierunkowskazu(stan));
				break;
			}
		}
	}
	

	/**
	 * Zwraca obiekt reprezentujący kontrolkę kierunkowskazu o odpowiedniej nazwie
	 * @param s nazwa kontrolki
	 * @return KontrolkaKierunkowskazu obiekt kontrolki kierunkowskazu 
	 */
	public KontrolkaKierunkowskazu getStrzalka(SwiatlaKierunkowskazow s)
	{
		switch(s) {
			case lewo:
			{
				return strzalki.get(0);
			}
			case prawo:
			{
				return strzalki.get(1);
			}
		}
		return strzalki.get(0);
	}
	
}
