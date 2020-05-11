package warstwaDanych;

public class LicznikPrzebieguCalkowitego {
	protected double przebieg;
	
	public LicznikPrzebieguCalkowitego() { }
	public LicznikPrzebieguCalkowitego(double p)
	{
		przebieg = p;
	}
	
	public double getPrzebieg()
	{
		return przebieg;
	}
	
	public void zwiekszPrzebieg(double ile)
	{
		przebieg+=ile;
	}

}
