package warstwaDanych;

/**
 * Klasa reprezentująca pola które będzie miała każda kontrolka, metody dostępu oraz modyfikacji danych pól
 */

public class Kontrolka {
	private boolean wlaczona;
	
	/**
	 * Konstruktor który będzie używany dla wszystkich dziedziczącym po tej klasie klas
	 * @param stan
	 */
	
	public Kontrolka(boolean stan) {
		wlaczona = stan;
	}
	
	/**
	 * Zwaraca stan kontrolki, czy jest ona wlaczona
	 * @return boolean stan
	 */

	public boolean getWlaczona()
	{
		return wlaczona;
	}
	
	/**
	 * Metoda modyfikująca stan kontrolki, włącza lub wyłącza, zmieniając aktualny stan na przeciwny
	 */
	
	public void zmienWlaczonaStan()
	{
		wlaczona = !wlaczona;
	}

}
