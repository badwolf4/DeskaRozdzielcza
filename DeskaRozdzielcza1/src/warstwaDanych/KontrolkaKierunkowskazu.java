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
	 * Zmiana stanu kontrolki na true, jeśli jest false (włączenie kontrolkę)
	 */
	@Override
	public void wlacz() {
		if(!getWlaczona())
			zmienWlaczonaStan();
		
	}

	/**
	 * Zmiana stanu kontrolki na false, jeśli jest true (wyłączenie kontrolkę)
	 */
	@Override
	public void wylacz() {
		if(getWlaczona())
			zmienWlaczonaStan();
		
	}


}
