package warstwaDanych;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import warstwaLogiki.DeskaRozdzielcza;
import warstwaLogiki.KomputerPokladowy;

import java.sql.ResultSet;

public class DatabaseHandler extends Config {
Connection dbConnection;
public Connection getDbConnection() 
		throws ClassNotFoundException, SQLException {
			String connectionString ="jdbc:mysql://" + dbHost + ":" + dbPort + 
						"/" + dbName + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
						Class.forName("com.mysql.cj.jdbc.Driver");
						dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
						return dbConnection; 
		}

public DeskaRozdzielcza wczytajZBD(DeskaRozdzielcza deska) {
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

public void zapisacDoBD(
		String predkosc,
		String przebieg_calkowity,
		String przebieg_dzienny,
		Boolean lewy_kierunkowskaz,
		Boolean prawy_kierunkowskaz,
		Boolean kontrolka_swiatel_pozycyjnych,
		Boolean kontrolka_swiatel_mijania,
		Boolean kontrolka_swiatel_drogowych , 
		
		Boolean kontrolka_swiatel_przeciwmgielnych_przod,
		Boolean kontrolka_swiatel_przeciwmgielnych_tyl,
		
		String predkosc_srednia,
		String predkosc_maksymalna,
		String czas_podrozy,
		String dystans, 
		String srednie_spalanie) {
			String insert = "INSERT INTO "+Const.tablica+ "(" +Const.predkosc + "," +Const.przebieg_calkowity+ "," +Const.przebieg_dzienny+ 
					"," +Const.lewy_kierunkowskaz+ "," +Const.prawy_kierunkowskaz+ "," +Const.kontrolka_swiatel_pozycyjnych + "," +Const.kontrolka_swiatel_mijania
					+ "," +Const.kontrolka_swiatel_drogowych+ "," +Const.kontrolka_swiatel_przeciwmgielnych_przod+ "," 
					+Const.kontrolka_swiatel_przeciwmgielnych_tyl+ "," +Const.predkosc_srednia+ "," +Const.predkosc_maksymalna+ "," +Const.czas_podrozy
					+ "," +Const.dystans+ "," +Const.srednie_spalanie + ")"+
					"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement prSt = getDbConnection().prepareStatement(insert);
				prSt.setString(1, predkosc);
				prSt.setString(2, przebieg_calkowity);
				prSt.setString(3, przebieg_dzienny);
				prSt.setBoolean(4, lewy_kierunkowskaz);
				prSt.setBoolean(5, prawy_kierunkowskaz);
				prSt.setBoolean(6, kontrolka_swiatel_pozycyjnych);
				prSt.setBoolean(7, kontrolka_swiatel_mijania);
				prSt.setBoolean(8, kontrolka_swiatel_drogowych);
				prSt.setBoolean(9, kontrolka_swiatel_przeciwmgielnych_przod);
				prSt.setBoolean(10,kontrolka_swiatel_przeciwmgielnych_tyl);
				prSt.setString(11, predkosc_srednia);
				prSt.setString(12, predkosc_maksymalna);
				prSt.setString(13, czas_podrozy);
				prSt.setString(14, dystans);
				prSt.setString(15, srednie_spalanie);
				prSt.execute();
				dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		}

}


