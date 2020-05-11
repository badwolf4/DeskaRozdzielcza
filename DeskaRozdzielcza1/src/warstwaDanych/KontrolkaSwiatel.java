package warstwaDanych;
public class KontrolkaSwiatel extends Kontrolka implements KontrolkaInterface {

	public KontrolkaSwiatel(boolean stan)
	{
		super(stan);
	}
	
	@Override
	public void wyswietl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wlacz() {
		if(!getWlaczona())
			zmienWlaczonaStan();
		
	}

	@Override
	public void wylacz() {
		if(getWlaczona())
			zmienWlaczonaStan();
		
	}

}
