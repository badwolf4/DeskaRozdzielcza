package warstwaLogiki;

public class KomputerPokladowy {
	private double predkoscSrednia;
	private double predkoscMaksymalna;
	private double czasPodrozy;
	private double dystans;
	private double srednieSpalanie;
	
	KomputerPokladowy(double ps, double pm, double czp, double d, double ss)
	{
		predkoscSrednia = ps;
		predkoscMaksymalna = pm;
		czasPodrozy = czp;
		dystans = d;
		srednieSpalanie = ss;
	}
	
	KomputerPokladowy()
	{
		
	}
	
	void refreashKomputer(double sekundy, double godziny, double dystans1, double predkosc)
	{
		
		setCzasPodrozy(getCzasPodrozy()+sekundy);
    	setDystans(getDystans()+ dystans1);
    	setPredkoscSrednia(getDystans()/(getCzasPodrozy()/60));
    	if(getPredkoscMaksymalna() < predkosc)
    		setPredkoscMaksymalna(predkosc);
	}
	
	public void setPredkoscSrednia(double p)
	{
		predkoscSrednia = p;
	}
	
	public void setPredkoscMaksymalna(double p)
	{
		predkoscMaksymalna= p;
	}
	public void setCzasPodrozy(double p)
	{
		czasPodrozy = p;
	}
	public void setDystans(double p)
	{
		dystans = p;
	}
	public void setSrednieSpalanie(double p)
	{
		srednieSpalanie = p;
	}
	public double getPredkoscSrednia()
	{
		return predkoscSrednia;
	}
	
	public double getPredkoscMaksymalna()
	{
		return predkoscMaksymalna;
	}
	
	public double getCzasPodrozy()
	{
		return czasPodrozy;
	}
	
	public double getDystans()
	{
		return dystans;
	}
	
	public double getSrednieSpalanie()
	{
		return srednieSpalanie;
	}
	
	
}
