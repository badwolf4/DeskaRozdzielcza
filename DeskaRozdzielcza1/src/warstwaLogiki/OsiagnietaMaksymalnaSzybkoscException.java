package warstwaLogiki;

@SuppressWarnings("serial")
public class OsiagnietaMaksymalnaSzybkoscException extends Exception {
	public String getMessage()
	{
		return "Nie mozna zwiekszyc szybkosc. Maksymalna mozliwa szybkosc to 280 kilometrow na godzine";
	}
}
