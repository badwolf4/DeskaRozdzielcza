package warstwaLogiki;

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
	 * Metoda do tworzenia nowej instancji klasy KomputerPokladowy
	 * @param ps predkość średnia
	 * @param pm predkość maksymalna
	 * @param czp czas podrozy
	 * @param d dystans 
	 * @param ss średnie spalanie
	 */
	KomputerPokladowy(double ps, double pm, double czp, double d, double ss)
	{
		predkoscSrednia = ps;
		predkoscMaksymalna = pm;
		czasPodrozy = czp;
		dystans = d;
		srednieSpalanie = ss;
	}
	
	/**
	 * Metoda do tworzenia nowej instancji klasy KomputerPokladowy, bezparametrowa
	 */
	public KomputerPokladowy()
	{
		
	}
	
	/**
	 * Metoda do odświeżenia zawartości pól obiektu klasy
	 * @param sekundy czas w sekundach
	 * @param godziny czas w godzinach
	 * @param dystans1 dystans pokonany w tym czasie
	 * @param predkosc nowa prędkość
	 */
	void refreashKomputer(double sekundy, double godziny, double dystans1, double predkosc)
	{
		
		setCzasPodrozy(getCzasPodrozy()+sekundy);
    	setDystans(getDystans()+ dystans1);
    	setPredkoscSrednia(getDystans()/(getCzasPodrozy()/360));
    	if(getPredkoscMaksymalna() < predkosc)
    		setPredkoscMaksymalna(predkosc);
    	//setSrednieSpalanie(); dopisat
	}
	
	/**
	 * Metoda modyfikująca pole predkoscSrednia
	 * @param p nowa predkość srednia
	 */
	public void setPredkoscSrednia(double p)
	{
		predkoscSrednia = p;
	}
	/**
	 * Metoda modyfikująca pole predkoscMaksymalna
	 * @param p nowa predkość maksymalna
	 */
	public void setPredkoscMaksymalna(double p)
	{
		predkoscMaksymalna= p;
	}
	/**
	 * Metoda modyfikująca pole czasPodrozy
	 * @param p nowy czas podróży
	 */
	public void setCzasPodrozy(double p)
	{
		czasPodrozy = p;
	}
	/**
	 * Metoda modyfikująca pole dystans
	 * @param p nowy dystans
	 */
	public void setDystans(double p)
	{
		dystans = p;
	}
	/**
	 * Metoda modyfikująca pole srednieSpalanie
	 * @param p nowe średnie spalanie
	 */
	public void setSrednieSpalanie(double p)
	{
		srednieSpalanie = p;
	}
	/**
	 * Metoda dostępu do pola predkoscSrednia
	 * @return double
	 */
	public double getPredkoscSrednia()
	{
		return predkoscSrednia;
	}
	/**
	 * Metoda dostępu do pola predkoscMaksymalna
	 * @return double
	 */
	public double getPredkoscMaksymalna()
	{
		return predkoscMaksymalna;
	}
	/**
	 * Metoda dostępu do pola czasPodrozy
	 * @return double
	 */
	public double getCzasPodrozy()
	{
		return czasPodrozy;
	}
	/**
	 * Metoda dostępu do pola dystans
	 * @return double
	 */
	public double getDystans()
	{
		return dystans;
	}
	/**
	 * Metoda dostępu do pola srednieSpalanie
	 * @return double
	 */
	public double getSrednieSpalanie()
	{
		return srednieSpalanie;
	}
	
	
}
