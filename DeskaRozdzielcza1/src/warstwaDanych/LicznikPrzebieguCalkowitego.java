package warstwaDanych;


/**
 * Klasa reprezentująca licznik przebiegu całkowitego samochodu
 */
public class LicznikPrzebieguCalkowitego {

	protected double przebieg;
	
	/**
	 * Tworzenie nowej instancji klasy LIcznikPrzebieguCałkowitego
	 * @param p
	 */
	public LicznikPrzebieguCalkowitego(double p)
	{
		przebieg = p;
	}
	
	/**
	 * Zwraca przebieg całkowity
	 * @return double
	 */
	public double getPrzebieg()
	{
		return przebieg;
	}
	
	/**
	 * Zwiększa przebieg o podaną wartość(ile)
	 * @param ile
	 */
	public void zwiekszPrzebieg(double ile)
	{
		przebieg+=ile;
	}

}
