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
