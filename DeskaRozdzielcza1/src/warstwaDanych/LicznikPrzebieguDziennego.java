package warstwaDanych;
/**

 * Klass który reprezentuje licznik przebiegu calkowitego
 */
public class LicznikPrzebieguDziennego extends LicznikPrzebieguCalkowitego{
	
	/**
	 * Tworzenie nowej instancji klasy LicznikPrzebieguCalkowitego.
	 * @param p początkowy przebieg
	 */
	public LicznikPrzebieguDziennego(double p) {
		super(p);
	}
	
	/**
	 * Wyzerowanie przebiegu
	 */
	public void wyzerujLicznik()
	{
		przebieg = 0;
	}

}
