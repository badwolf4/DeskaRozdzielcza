package warstwaDanych;
public class KontrolkaKierunkowskazu extends Kontrolka implements KontrolkaInterface {
	

	public KontrolkaKierunkowskazu(boolean stan) {
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
