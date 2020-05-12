package warstwaDanych;


public class Kontrolka {
	private boolean wlaczona;
	
	public Kontrolka(boolean stan) {
		wlaczona = stan;
	}

	public boolean getWlaczona()
	{
		return wlaczona;
	}
	
	public void zmienWlaczonaStan()
	{
		wlaczona = !wlaczona;
	}

}
