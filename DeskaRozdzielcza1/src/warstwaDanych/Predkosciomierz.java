package warstwaDanych;


import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;

/**
 * Klasa reprezentująca prędkościomierz samochodu
 */
public class Predkosciomierz {
	protected double predkosc;

	/**
	 * Tworzenie nowej instancji obiektu klasy Predkosciomierz
	 * @param d
	 */
	public Predkosciomierz(double d) {
		predkosc = d;
	}
	
	/**
	 * Metoda dostępu do pola predkość
	 * @return double 
	 */

	public double getPredkosc() {
		return predkosc;
	}
	
	/**
	 * Metoda modyfikująca pole predkość Zwiększa wartość pola o 1
	 * @throws OsiagnietaMaksymalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 280
	 */

	public void przyspiesz() throws OsiagnietaMaksymalnaSzybkoscException {
		if (predkosc == 280)
			throw new OsiagnietaMaksymalnaSzybkoscException();
		predkosc++;
	}

	/**
	 * Metoda modyfikująca pole predkość.Zmniejsza wartość pola o 1
	 * @throws OsiagnietaMinimalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 0
	 */
	public void zwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc--;
	}
	
	/**
	 * Metoda modyfikująca pole predkość.Zmniejsza wartość pola o 5
	 * @throws OsiagnietaMinimalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 0
	 */

	public void bardzoZwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0 || (predkosc - 5) < 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc -= 5;
	}
}
