package warstwaLogiki;

/**
 * Klasa wyjątku, rzucanego przy spróbie przekroczenia górnego limitu prędkości 
 */
@SuppressWarnings("serial")
public class OsiagnietaMaksymalnaSzybkoscException extends Exception {
	public String getMessage()
	{
		return "Nie mozna zwiekszyc szybkosc. Maksymalna mozliwa szybkosc to 280 kilometrow na godzine";
	}
}
