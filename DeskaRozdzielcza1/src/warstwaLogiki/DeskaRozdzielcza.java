package warstwaLogiki;
import java.util.ArrayList;

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
//		przebiegCalkowity = new LicznikPrzebieguCalkowitego();
//		przebiegDzienny = new LicznikPrzebieguDziennego();
//		komputer = new KomputerPokladowy();
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
		
//		strzalki.put("prawo",new KontrolkaKierunkowskazu(prawo));
//		strzalki.put("lewo",new KontrolkaKierunkowskazu(lewo));
//		
//		swiatla.put("pozycyjnych",new KontrolkaSwiatel(pozycyjnych));
//		swiatla.put("mijania", new KontrolkaSwiatel(mijania));
//		swiatla.put("drogowych", new KontrolkaSwiatel(drogowych));
//		swiatla.put("przeciwmgelnychPrzod", new KontrolkaSwiatel(przeciwmgelnychPrzod));
//		swiatla.put("przeciwmgelnychTyl", new KontrolkaSwiatel(przeciwmgelnychTyl));
		
		
//		this.prawo = prawo;
//		this.lewo = lewo;
//		this.pozycyjnych = pozycyjnych;
//		this.mijania = mijania;
//		this.drogowych = drogowych;
//		this.przeciwmgelnychPrzod = przeciwmgelnychPrzod;
//		this.przeciwmgelnychTyl = przeciwmgelnychTyl;
		
		this.komputer = komputer;
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
	
//	public void setSwiatlo(String nazwa, boolean stan)
//	{
//		if (swiatla.putIfAbsent(nazwa, new KontrolkaSwiatel(stan))!=null)
//		{
//			swiatla.replace(nazwa, new KontrolkaSwiatel(stan));
//		}
//	}
//	
//	public KontrolkaSwiatel getSwiatlo(String klucz)
//	{
//		return swiatla.get(klucz);
//	}
//	
//	public void setKontrolkaKieronkowskazu(String nazwa, boolean stan)
//	{
//		if(strzalki.putIfAbsent(nazwa, new KontrolkaKierunkowskazu(stan)) != null)
//		{
//			strzalki.replace(nazwa, new KontrolkaKierunkowskazu(stan));
//		}
//				
//	}
//	
//	public KontrolkaKierunkowskazu getKontrolkaKieronkowskazu(String klucz)
//	{
//		return strzalki.get(klucz);
//	}
	
	public void setSwiatlo(int n, boolean stan)
	{
		swiatla.set(n, new KontrolkaSwiatel(stan));
//		if(stan)
//		{
//			if(!swiatla.get(n).getWlaczona())
//				swiatla.get(n).wlacz();
//		}
//		
//		else
//		{
//			if(swiatla.get(n).getWlaczona())
//				swiatla.get(n).wylacz();
//		}
		
	}
	
	public KontrolkaSwiatel getSwiatlo(int n)
	{
		return swiatla.get(n);
	}
	
	public void setStrzalka(int n, boolean stan)
	{
		strzalki.set(n, new KontrolkaKierunkowskazu(stan));
		
//		if(stan)
//		{
//			if(!strzalki.get(n).getWlaczona())
//				strzalki.get(n).wlacz();
//		}
//		
//		else
//		{
//			if(strzalki.get(n).getWlaczona())
//				strzalki.get(n).wylacz();
//		}
	}
	
	public KontrolkaKierunkowskazu getStrzalka(int n)
	{
		return strzalki.get(n);
	}
	
	
//	public void setKontrolkaKierunkowskazuPrawo(KontrolkaKierunkowskazu p)
//	{
//		prawo  = p;
//	}
//	
//	public  KontrolkaKierunkowskazu getKontrolkaKierunkowskazuPrawo()
//	{
//		return prawo;
//	}
//	
//	public void setKontrolkaKierunkowskazuLewo(KontrolkaKierunkowskazu p)
//	{
//		lewo  = p;
//	}
//	
//	public KontrolkaKierunkowskazu getKontrolkaKierunkowskazuLewo()
//	{
//		return lewo;
//	}
//	
//	public void setKontrolkaSwiatelPozycyjnych(KontrolkaSwiatel p)
//	{
//		pozycyjnych  = p;
//	}
//	
//	public KontrolkaSwiatel getKontrolkaSwiatelPozycyjnych()
//	{
//		return pozycyjnych;
//	}
//	
//	public void setKontrolkaSwiatelDrogowych(KontrolkaSwiatel p)
//	{
//		drogowych  = p;
//	}
//	
//	public KontrolkaSwiatel getKontrolkaSwiatelDrogowych()
//	{
//		return drogowych;
//	}
//	
//	public void setKontrolkaSwiatelPrzeciwmgelnychPrzod(KontrolkaSwiatel p)
//	{
//		przeciwmgelnychPrzod  = p;
//	}
//	
//	public KontrolkaSwiatel getKontrolkaSwiatelPrzeciwmgelnychPrzod()
//	{
//		return przeciwmgelnychPrzod;
//	}
//	
//	public void setKontrolkaSwiatelPrzeciwmgelnychTyl(KontrolkaSwiatel p)
//	{
//		przeciwmgelnychTyl = p;
//	}
//	
//	public KontrolkaSwiatel getKontrolkaSwiatelPrzeciwmgelnychTyl()
//	{
//		return przeciwmgelnychTyl;
//	}
//	
//	public void setKontrolkaSwiatelMijania(KontrolkaSwiatel p)
//	{
//		mijania  = p;
//	}
//	
//	public KontrolkaSwiatel getKontrolkaSwiatelMijania()
//	{
//		return mijania;
//	}
	
	
}
