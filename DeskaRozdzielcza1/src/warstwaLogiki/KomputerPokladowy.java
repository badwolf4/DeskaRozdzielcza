package warstwaLogiki;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class KomputerPokladowy {
	private double predkoscSrednia;
	private double predkoscMaksymalna;
	private double czasPodrozy;
	private double dystans;
	private double srednieSpalanie;
	private DecimalFormat dec = new DecimalFormat("#0.00");
	
	
	KomputerPokladowy(double ps, double pm, double czp, double d, double ss)
	{
		predkoscSrednia = ps;
		predkoscMaksymalna = pm;
		czasPodrozy = czp;
		dystans = d;
		srednieSpalanie = ss;
	}
	
	public KomputerPokladowy()
	{
		
	}
	
	void refreashKomputer(double sekundy, double godziny, double dystans1, double predkosc)
	{
		
		setCzasPodrozy(getCzasPodrozy()+sekundy);
    	setDystans(getDystans()+ dystans1);
    	setPredkoscSrednia(getDystans()/(getCzasPodrozy()/360));
    	if(getPredkoscMaksymalna() < predkosc)
    		setPredkoscMaksymalna(predkosc);
    	//setSrednieSpalanie(); dopisat
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
		return bd(predkoscSrednia);
	}
	
	public double getPredkoscMaksymalna()
	{
		return bd(predkoscMaksymalna);
	}
	
	public double getCzasPodrozy()
	{
		return bd(czasPodrozy);
	}
	
	public double getDystans()
	{
		return bd(dystans);
	}
	
	public double getSrednieSpalanie()
	{
		return bd(srednieSpalanie);
	}
	
	public static double bd(double input) {
		return new BigDecimal(input).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
