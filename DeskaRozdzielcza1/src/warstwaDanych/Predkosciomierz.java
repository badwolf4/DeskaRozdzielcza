package warstwaDanych;


import warstwaLogiki.OsiagnietaMaksymalnaSzybkoscException;
import warstwaLogiki.OsiagnietaMinimalnaSzybkoscException;

public class Predkosciomierz {
	protected int predkosc;

	public Predkosciomierz(int p) {
		predkosc = p;
	}

	public int getPredkosc() {
		return predkosc;
	}

	public void przyspiesz() throws OsiagnietaMaksymalnaSzybkoscException {
		if (predkosc == 280)
			throw new OsiagnietaMaksymalnaSzybkoscException();
		predkosc++;
	}

	public void zwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc--;
	}

	public void bardzoZwolnij() throws OsiagnietaMinimalnaSzybkoscException {
		if (predkosc == 0 || (predkosc - 5) < 0)
			throw new OsiagnietaMinimalnaSzybkoscException();
		predkosc -= 5;
	}
}
