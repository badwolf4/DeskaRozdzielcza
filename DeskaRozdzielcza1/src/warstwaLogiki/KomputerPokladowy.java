package warstwaLogiki;

import java.util.ArrayList;

/**
 * Klasa reprezentująca komputer pokładowy samochodu
 */
public class KomputerPokladowy {
	private double predkoscSrednia;
	private double predkoscMaksymalna;
	private double czasPodrozy;
	private double dystans;
	private double srednieSpalanie;
	
	/**
	 * Tworzenie nowej instancji klasy KomputerPokladowy
	 * @param ps predkość średnia
	 * @param pm predkość maksymalna
	 * @param czp czas podrozy
	 * @param d dystans 
	 * @param ss średnie spalanie
	 */
	public KomputerPokladowy(double ps, double pm, double czp, double d, double ss)
	{
		predkoscSrednia = ps;
		predkoscMaksymalna = pm;
		czasPodrozy = czp;
		dystans = d;
		srednieSpalanie = ss;
		
		pomiaryPredkosci = new ArrayList<Double>();
		srednieSpalania = new ArrayList<Double>();
		
		srednieSpalania.add(ss);
	}
	
	/**
	 * Tworzenie nowej instancji klasy KomputerPokladowy
	 */
	public KomputerPokladowy()
	{
		pomiaryPredkosci = new ArrayList<Double>();
		srednieSpalania = new ArrayList<Double>();
	}
	
	/**
	 * Odświeżenie zawartości pól obiektu klasy
	 * @param sekundy czas w sekundach
	 * @param godziny czas w godzinach
	 * @param dystans1 dystans pokonany w tym czasie
	 * @param predkosc nowa prędkość
	 */
	public void refreashKomputer(double sekundy, double godziny, double dystans1, double predkosc)
	{
		
		setCzasPodrozy(getCzasPodrozy()+sekundy);
    	setDystans(getDystans()+ dystans1);
    	setPredkoscSrednia(getDystans()/(getCzasPodrozy()/360));
    	if(getPredkoscMaksymalna() < predkosc)
    		setPredkoscMaksymalna(predkosc);
    	//setSrednieSpalanie(arraySpalania(predkosc)); 
    	pomiar++;
    	pomiaryPredkosci.add(predkosc);
    	System.out.println("predkosc / speed: " + predkosc);
    	if(pomiar==60)
    	{
    		setSrednieSpalanie(obliczSpalanie());
    	}
    	
	}
	private int pomiar=0;
	private ArrayList<Double> pomiaryPredkosci;
	private ArrayList<Double> srednieSpalania;
	
	/**
	 * Obliczanie spalania na podstawie sredniej z 60 pomiarow predkosci
	 * @return
	 */
	private double obliczSpalanie()
	{
		double p = 0.0;
		for(double i : pomiaryPredkosci)
			p+=i;
		System.out.println("srednie spalanie / Avarage  fuel consuming: " + policzSpalanie(p/60));
		srednieSpalania.add(policzSpalanie(p/60));
		pomiaryPredkosci.clear();
		pomiar=0;
		double l = 0.0;
		for(double i : srednieSpalania)
			{
				l+=i;
				System.out.println("Poprzednia srednia / All values of fuel consuming: " + i);
			}
		System.out.println("Nowa srednia / New average: " + l/srednieSpalania.size());
		return l/srednieSpalania.size();
	}
	
//	int i=0;
//	double rez=0.0;
	
//	double arraySpalania(double predkosc) {
//		
//		i++;
//		double spalanie = policzSpalanie(predkosc);
//		rez+=spalanie;
//		return rez/i;
//	}
	
	//zamiast predkosci brac co 5 minut (naprzyklad) wyliczac srednia predkosc i dalej w metode niziej
	//zapisac w druga tablice srednie wartosci spalania (mamy 2 tablicy 1 do wartosci predkosci, inna do 
	//wartosci spalania w tym czasie co robiono pomiary dla pierwszej. wynik srednia z elementow drugej tablicy
	
	/**
	 * Obliczanie średniego spalania za minutę
	 * @param predkosc prędkość średnia z 60 pomiarów 
	 * @return
	 */
	private double policzSpalanie(double predkosc) {
		double litrowNa100km =10.0;
		double mnoznik0=1.0;
		double mnoznikNiski=0.5;
		double mnoznikWielki=1.5;
		if(predkosc>80.0 && predkosc<=120.0) {
			return litrowNa100km*mnoznik0;
		}
		else
			if(predkosc>1.0 && predkosc<=40.0) {
				return litrowNa100km*mnoznikWielki;
			}
			else
				if(predkosc>120.0){
					return litrowNa100km*mnoznikWielki; 
				}
				else 
					if(predkosc>40.0 && predkosc<=80.0) {
						return litrowNa100km*mnoznikNiski; 
					}
					else return 0.0;
	}
	
	/**
	 * Zamiana zawartości pola predkoscSrednia
	 * @param p nowa predkość srednia
	 */
	public void setPredkoscSrednia(double p)
	{
		predkoscSrednia = p;
	}
	/**
	 * Zamiana zawartości pola predkoscMaksymalna
	 * @param p nowa predkość maksymalna
	 */
	public void setPredkoscMaksymalna(double p)
	{
		predkoscMaksymalna= p;
	}
	/**
	 * Zamiana zawartości pola czasPodrozy
	 * @param p nowy czas podróży
	 */
	public void setCzasPodrozy(double p)
	{
		czasPodrozy = p;
	}
	/**
	 * Zamiana zawartości pola dystans
	 * @param p nowy dystans
	 */
	public void setDystans(double p)
	{
		dystans = p;
	}
	/**
	 * Zamiana zawartości pola srednieSpalanie
	 * @param p nowe średnie spalanie
	 */
	public void setSrednieSpalanie(double p)
	{
		srednieSpalanie = p;
		srednieSpalania.add(p);
	}
	/**
	 * Zwraca predkoscSrednia
	 * @return double
	 */
	public double getPredkoscSrednia()
	{
		return predkoscSrednia;
	}
	/**
	 * Zwraca predkoscMaksymalna
	 * @return double
	 */
	public double getPredkoscMaksymalna()
	{
		return predkoscMaksymalna;
	}
	/**
	 * Zwraca czasPodrozy
	 * @return double
	 */
	public double getCzasPodrozy()
	{
		return czasPodrozy;
	}
	/**
	 * Zwraca dystans
	 * @return double
	 */
	public double getDystans()
	{
		return dystans;
	}
	/**
	 * Zwraca srednieSpalanie
	 * @return double
	 */
	public double getSrednieSpalanie()
	{
		return srednieSpalanie;
	}
	
	
}
