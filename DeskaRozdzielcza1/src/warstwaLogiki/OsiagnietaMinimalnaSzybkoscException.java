package warstwaLogiki;

/**
 * Klasa wyjątku, rzucanego przy spróbie przekroczenia dolnego limitu prędkości, ustawić prędkość na mniejszą od zera 
 */
@SuppressWarnings("serial")
public class OsiagnietaMinimalnaSzybkoscException extends Exception{
	public String getMessage()
	{
		return "Nie mozna zmienic szybkosc na mniejsza od zera";
	}
}
