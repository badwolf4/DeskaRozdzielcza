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
    	setSrednieSpalanie(arraySpalania(predkosc)); 
	}
	
	int i=0;
	double rez=0.0;
	
	double arraySpalania(double predkosc) {
		
		i++;
		//System.out.println("i="+i);
		double spalanie = policzSpalanie(predkosc);
		//System.out.println("policzSpalanie="+rez);
		rez+=spalanie;
		return rez/i;
	}
	
	//zamiast predkosci brac co 5 minut (naprzyklad) wyliczac srednia predkosc i dalej w metode niziej
	//zapisac w druga tablice srednie wartosci spalania (mamy 2 tablicy 1 do wartosci predkosci, inna do 
	//wartosci spalania w tym czasie co robiono pomiary dla pierwszej. wynik srednia z elementow drugej tablicy
	double policzSpalanie(double predkosc) {
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
