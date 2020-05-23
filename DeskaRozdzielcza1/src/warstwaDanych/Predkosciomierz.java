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
	 * Zwraca wartość pola predkość
	 * @return double 
	 */

	public double getPredkosc() {
		return predkosc;
	}
	
	/**
	 * Zwiększenie prędkośći o 1
	 * @throws OsiagnietaMaksymalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 280
	 */

	public void przyspiesz() throws OsiagnietaMaksymalnaSzybkoscException {
		if (predkosc == 280)
			throw new OsiagnietaMaksymalnaSzybkoscException();
		predkosc++;
	}

	/**
	 * Zmniejszenie prędkośći o 1
	 * @throws OsiagnietaMinimalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 0
	 */
	public void zwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc--;
	}
	
	/**
	 * Zwiększenie prędkośći o 5
	 * @throws OsiagnietaMinimalnaSzybkoscException rzuca wyjątek przy spróbie przekroczyć 0
	 */

	public void bardzoZwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0 || (predkosc - 5) < 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc -= 5;
	}
}
