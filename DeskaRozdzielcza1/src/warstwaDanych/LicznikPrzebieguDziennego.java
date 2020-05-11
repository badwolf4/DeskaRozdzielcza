package warstwaDanych;
public class LicznikPrzebieguDziennego extends LicznikPrzebieguCalkowitego{

	public LicznikPrzebieguDziennego() { 
		super();
	}
	public LicznikPrzebieguDziennego(double p) {
		super(p);
		// TODO Auto-generated constructor stub
	}
	
	public void wyzerujLicznik()
	{
		przebieg = 0;
	}

}
