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
	 * Metoda dostępu do pola przebieg
	 * @return double
	 */
	public double getPrzebieg()
	{
		return przebieg;
	}
	
	public void zwiekszPrzebieg(double ile)
	{
		przebieg+=ile;
	}

}
