package warstwaLogiki;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import warstwaDanych.KontrolkaKierunkowskazu;
import warstwaDanych.KontrolkaSwiatel;
import warstwaDanych.LicznikPrzebieguCalkowitego;
import warstwaDanych.LicznikPrzebieguDziennego;
import warstwaDanych.Predkosciomierz;

public class DeskaRozdzielcza {
	Predkosciomierz predkosciomierz;
	LicznikPrzebieguCalkowitego przebiegCalkowity;
	LicznikPrzebieguDziennego przebiegDzienny;
	//lewo, prawo
	ArrayList<KontrolkaKierunkowskazu> strzalki;
	ArrayList<KontrolkaSwiatel> swiatla;
	//pozycyjnych, mijania, drogowych, przeciwmgelnychPrzod, przeciwmgelnychTyl
	KomputerPokladowy komputer;
	
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
	
	public void refreashDeska()
	{
		double sekundy = 1;
		double godziny = sekundy/3600;
		double dystans = getPredkosciomierz().getPredkosc() * godziny;
        

    	przebiegCalkowity.zwiekszPrzebieg(dystans);
    	przebiegDzienny.zwiekszPrzebieg(dystans);
    	komputer.refreashKomputer(sekundy, godziny,dystans,predkosciomierz.getPredkosc());
	}
	
	public void setPredkosciomierz(Predkosciomierz p)
	{
		predkosciomierz = p;
	}
	
	public Predkosciomierz getPredkosciomierz()
	{
		return predkosciomierz;
	}
	
	public void setLicznikPrzebieguCalkowitego(LicznikPrzebieguCalkowitego p)
	{
		przebiegCalkowity = p;
	}
	
	public LicznikPrzebieguCalkowitego getLicznikPrzebieguCalkowitego()
	{
		return przebiegCalkowity;
	}
	
	public void setLicznikPrzebieguDziennego(LicznikPrzebieguDziennego p)
	{
		przebiegDzienny  = p;
	}
	
	public LicznikPrzebieguDziennego getLicznikPrzebieguDziennego()
	{
		return przebiegDzienny;
	}
	
	public void setKomputerPokladowy(KomputerPokladowy p)
	{
		komputer  = p;
	}
	
	public KomputerPokladowy getKomputerPokladowy()
	{
		return komputer;
	}

	
	public void setSwiatlo(int n, boolean stan)
	{
		swiatla.set(n, new KontrolkaSwiatel(stan));
		
	}
	
	public KontrolkaSwiatel getSwiatlo(int n)
	{
		return swiatla.get(n);
	}
	
	public void setStrzalka(int n, boolean stan)
	{
		strzalki.set(n, new KontrolkaKierunkowskazu(stan));
	}
	
	public KontrolkaKierunkowskazu getStrzalka(int n)
	{
		return strzalki.get(n);
	}
	
	
}
