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
	Predkosciomierz predkosciomierz;
	LicznikPrzebieguCalkowitego przebiegCalkowity;
	LicznikPrzebieguDziennego przebiegDzienny;
	//lewo, prawo
	ArrayList<KontrolkaKierunkowskazu> strzalki;
	ArrayList<KontrolkaSwiatel> swiatla;
	//pozycyjnych, mijania, drogowych, przeciwmgelnychPrzod, przeciwmgelnychTyl
	KomputerPokladowy komputer;
	
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
		Timer t3 = new Timer();
		
		 t3.scheduleAtFixedRate(new TimerTask() {

			    @Override
			    public void run() {
			    	refreashDeska();
			    	
			    	try {
						predkosciomierz.zwolnij();
					} catch (OsiagnietaMinimalnaSzybkoscException e) {
						e.printStackTrace();
					}
			    }

			},
			//Set how long before to start calling the TimerTask (in milliseconds)
			1000,
			//Set the amount of time between each execution (in milliseconds)
			1000);
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
	 * Zamienia obiekt KontrolkaSwiatel na odpowiedniej pozycji, reprezentujący odpowiednią kontrolkę świateł
	 * pozycyjnych, mijania, drogowych, przeciwmgelnychPrzod, przeciwmgelnychTyl
	 * @param n pozycja na liście
	 * @param stan stan nowoutworzonej kontrolki swiatla
	 */
	public void setSwiatlo(int n, boolean stan)
	{
		swiatla.set(n, new KontrolkaSwiatel(stan));
		
	}
	
	/**
	 * Zwraca obiekt z ArrayLIst swiatla w kolejności
	 * pozycyjne, mijania, drogowych, przeciwmgelnychPrzod, przeciwmgelnychTyl
	 * @param n pozycja na liście
	 * @return KontrolkaSwiatel obiekt z listy
	 */
	public KontrolkaSwiatel getSwiatlo(int n)
	{
		return swiatla.get(n);
	}
	
	/**
	 * Zamienia obiekt KontrolkaKierunkowskazu na odpowiedniej pozycji, reprezentujący odpowiednią kotrolkę kirunkowskazu
	 * w kolejności: lewa, prawa
	 * @param n pozycja na liście
	 * @param stan stan kontrolki
	 */
	public void setStrzalka(int n, boolean stan)
	{
		strzalki.set(n, new KontrolkaKierunkowskazu(stan));
	}
	
	/**
	 * Zwraca obiekt z ArrayLIst strzalki reprezentujące kierunkowskazy w kolejności: lewy, prawy
	 * @param n pozycja na liście
	 * @return KontrolkaKierunkowskazu
	 */
	public KontrolkaKierunkowskazu getStrzalka(int n)
	{
		return strzalki.get(n);
	}
	
	
}
