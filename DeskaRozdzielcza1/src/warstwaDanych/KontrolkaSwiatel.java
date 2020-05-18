package warstwaDanych;

/**
 * Klasa reprezentująca kontrolki świateł samochodu
 */
public class KontrolkaSwiatel extends Kontrolka implements KontrolkaInterface {

	/**
	 * Tworzenie nowej instancji obiektu kontrolki świateł
	 * @param stan początkowy stan nowoutworzonej kontrolki świateł
	 */
	public KontrolkaSwiatel(boolean stan)
	{
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
