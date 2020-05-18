package warstwaDanych;

/**
 * Klasa reprezentująca kontrolki kierunkowskazów
 */
public class KontrolkaKierunkowskazu extends Kontrolka implements KontrolkaInterface {
	
	/**
	 * Tworzenie nowej instancji obiektu kontrolki kierunkowskazu
	 * @param stan początkowy stan nowoutworzonej kontrolki kierunkowskazu
	 */
	public KontrolkaKierunkowskazu(boolean stan) {
		super(stan);
		
	}

	/**
	 * Nadpisana metoda interfejsu Kontrolka, zapewniająca zmianę stanu
	 * pola klasy bazowej "wlaczona" na true tylko w przypadku jeśli bieżący 
	 * stan jest false
	 */
	@Override
	public void wlacz() {
		if(!getWlaczona())
			zmienWlaczonaStan();
		
	}

	/**
	 * Nadpisana metoda interfejsu Kontrolka, zapewniająca zmianę stanu
	 * pola klasy bazowej "wlaczona" na false tylko w przypadku jeśli bieżący 
	 * stan jest true
	 */
	@Override
	public void wylacz() {
		if(getWlaczona())
			zmienWlaczonaStan();
		
	}


}
