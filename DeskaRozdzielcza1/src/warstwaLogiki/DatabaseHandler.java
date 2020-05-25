package warstwaLogiki;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import warstwaDanych.LicznikPrzebieguCalkowitego;
import warstwaDanych.LicznikPrzebieguDziennego;
import warstwaDanych.Predkosciomierz;

import java.sql.ResultSet;

/**
 * Klasa do nawiązania połączenia z bazą danych, umożliwiająca zapis/odczyt danych z/do bazy danych 
 */
public class DatabaseHandler{
Connection dbConnection;

/**
 * Tworzenie nowej instanji klasy DatabaseHandler 
 */
public DatabaseHandler(){ }
/**
 * Nawiązanie połączenia z bazą danych
 * @return Connection obiekt pośredniczący między aplikacją a bazą danych
 * @throws ClassNotFoundException wyjątek rzucany w przypadku kiedy nie znaleziono w systemie sterownika do nawiązania połączenia
 * @throws SQLException wyjątek biblioteczny
 */
public Connection getDbConnection() 
		throws ClassNotFoundException, SQLException {
			String connectionString ="jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + 
						"/" + Config.dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						Class.forName("com.mysql.cj.jdbc.Driver");
						dbConnection = DriverManager.getConnection(connectionString, Config.dbUser, Config.dbPass);
						return dbConnection; 
		}

/**
 * Wczytanie danych z bazy danych
 * @return DeskaRozdzielcza obiekt służący do przechowywania danych wewnątrz działającej aplikacji
 */
public DeskaRozdzielcza wczytajZBD() {
	DeskaRozdzielcza deska = new DeskaRozdzielcza();
	String wczytaj ="SELECT *"+" FROM "+Const.tablica ;
	try {
		Statement state = getDbConnection().createStatement();
		ResultSet rs = state.executeQuery(wczytaj);
		rs.next();
		
		deska.setPredkosciomierz(new Predkosciomierz(rs.getInt(1)));
		deska.setLicznikPrzebieguCalkowitego(new LicznikPrzebieguCalkowitego(rs.getDouble(2)));
		
		deska.setLicznikPrzebieguDziennego(new LicznikPrzebieguDziennego(rs.getDouble(3)));
		deska.setStrzalka(0, rs.getBoolean(4));
		deska.setStrzalka(1, rs.getBoolean(5));
		deska.setSwiatlo(0, rs.getBoolean(6));
		deska.setSwiatlo(1, rs.getBoolean(7));
		deska.setSwiatlo(2, rs.getBoolean(8));
		deska.setSwiatlo(3, rs.getBoolean(9));
		deska.setSwiatlo(4, rs.getBoolean(10));
		
		deska.setKomputerPokladowy(new KomputerPokladowy());
		deska.getKomputerPokladowy().setPredkoscSrednia(rs.getDouble(11));
		deska.getKomputerPokladowy().setPredkoscMaksymalna(rs.getDouble(12));
		deska.getKomputerPokladowy().setCzasPodrozy(rs.getDouble(13));
		deska.getKomputerPokladowy().setDystans(rs.getDouble(14));
		deska.getKomputerPokladowy().setSrednieSpalanie(rs.getDouble(15));
		dbConnection.close();
	} catch (ClassNotFoundException | SQLException e1) {
		e1.printStackTrace();
	}
	return deska;
}

/**
 * Czyszczenie starych danych przed zapisem nowych
 */
public void usunZBD() {
	String delete = "TRUNCATE "+Const.tablica;
	
	try {
		PreparedStatement prStDel = getDbConnection().prepareStatement(delete);
		prStDel.execute();
		dbConnection.close();
	} catch (ClassNotFoundException | SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}

/**
 * Zapis danych do bazy danych
 * @param deska obiekt z ktorego dane zostaną zapisane do bazy danych
 */
public void zapisacDoBD(DeskaRozdzielcza deska) {
	
			String insert = "INSERT INTO "+Const.tablica+ "(" +Const.predkosc + "," +Const.przebieg_calkowity+ "," +Const.przebieg_dzienny+ 
					"," +Const.lewy_kierunkowskaz+ "," +Const.prawy_kierunkowskaz+ "," +Const.kontrolka_swiatel_pozycyjnych + "," +Const.kontrolka_swiatel_mijania
					+ "," +Const.kontrolka_swiatel_drogowych+ "," +Const.kontrolka_swiatel_przeciwmgielnych_przod+ "," 
					+Const.kontrolka_swiatel_przeciwmgielnych_tyl+ "," +Const.predkosc_srednia+ "," +Const.predkosc_maksymalna+ "," +Const.czas_podrozy
					+ "," +Const.dystans+ "," +Const.srednie_spalanie + ")"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement prSt = getDbConnection().prepareStatement(insert);
				prSt.setString(1, Double.toString(deska.getPredkosciomierz().getPredkosc()));
				prSt.setString(2, Double.toString(deska.getLicznikPrzebieguCalkowitego().getPrzebieg()));
				prSt.setString(3, Double.toString(deska.getLicznikPrzebieguDziennego().getPrzebieg()));
				prSt.setBoolean(4, deska.getStrzalka(0).getWlaczona());
				prSt.setBoolean(5, deska.getStrzalka(1).getWlaczona());
				prSt.setBoolean(6, deska.getSwiatlo(0).getWlaczona());
				prSt.setBoolean(7, deska.getSwiatlo(1).getWlaczona());
				prSt.setBoolean(8, deska.getSwiatlo(2).getWlaczona());
				prSt.setBoolean(9, deska.getSwiatlo(3).getWlaczona());
				prSt.setBoolean(10,deska.getSwiatlo(4).getWlaczona());
				prSt.setString(11, Double.toString(deska.getKomputerPokladowy().getPredkoscSrednia()));
				prSt.setString(12, Double.toString(deska.getKomputerPokladowy().getPredkoscMaksymalna()));
				prSt.setString(13, Double.toString(deska.getKomputerPokladowy().getCzasPodrozy()));
				prSt.setString(14, Double.toString(deska.getKomputerPokladowy().getDystans()));
				prSt.setString(15, Double.toString(deska.getKomputerPokladowy().getSrednieSpalanie()));
				prSt.execute();
				dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}

}


