package warstwaLogiki;

@SuppressWarnings("serial")
public class OsiagnietaMinimalnaSzybkoscException extends Exception{
	public String getMessage()
	{
		return "Nie mozna zmienic szybkosc na mniejsza od zera";
	}
}
