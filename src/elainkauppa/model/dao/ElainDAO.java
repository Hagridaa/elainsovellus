package elainkauppa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import elainkauppa.model.Elain;


/**
 * Elain-tietokohteen tietokantak‰sittelypalvelut kuten
	addElain() - lis‰‰ El‰in tietokantaan
	 * findAll() - hae kaikki El‰imet tietokannasta
	 * find() - hae yhden El‰imen tiedot annetulla elainid:ll‰
	 * updateElain() - p‰ivit‰ El‰imen tiedot tietokantaan
	 * removeElain() -poista El‰imen tiedot tietokannasta
 *
 */
public class ElainDAO extends DataAccessObject {
	
	/**
	 * 
	 */
	public void removeElain(Integer id) throws SQLException {
		// esitell‰‰n muuttujat
		Connection conn = null;
		PreparedStatement stmtRemove = null;
		try {
			// luodaan yhteys tietokantaan
			conn = getConnection();
			// luodaan sql komento, jolla poistetaan el‰in kannasta
			String sqlDelete = "DELETE FROM elain WHERE id = ?";
			stmtRemove = conn.prepareStatement(sqlDelete);
			stmtRemove.setInt(1, id);
			stmtRemove.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * addNew-metodi lis‰‰ INSERT-komennolla tietokantaan uuden el‰imen tiedot. 
	 * Metodi saa parametrina lis‰tt‰v‰n El‰in-luokan olion. 
	 */
	public void addNew(Elain elain) throws SQLException {
		// esitell‰‰n muuttujat
		Connection conn = null;
		PreparedStatement stmtInsert = null;
		try {
			// luodaan yhteys kantaan
			conn = getConnection();
			// luodaan sql komento, jolla lis‰t‰‰n el‰in kantaan
			//int id, String laji, String nimi, String kuvaus, double hinta
			String sql = "INSERT INTO elain(id, laji, nimi, kuvaus, hinta) VALUES(?,?,?,?,?)";
			// asetetaan arvot
			stmtInsert = conn.prepareStatement(sql);
			stmtInsert.setInt(1, elain.getId());
			stmtInsert.setString(2, elain.getLaji());
			stmtInsert.setString(3, elain.getNimi());
			stmtInsert.setString(4, elain.getKuvaus());
			stmtInsert.setDouble(5, elain.getHinta());
			// p‰ivitet‰‰n tiedot kantaan
			stmtInsert.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, conn);
		}
		
	}
	
	/**
	 *  findAll-metodi hakee SELECT-komennolla 
	 *  tietokannasta kaikki elain-taulun 
	 *  tietorivit ja palauttaa elainlistan 
	 *  (ArrayList)
	 */
	public ArrayList<Elain> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Elain> elaimet = new ArrayList<Elain>();
		Elain elain = null; 
		try {
			// Luodaan yhteys tietokantaan
			conn = getConnection();
			// luodaan sql komento
			// int id, String laji, String nimi, String kuvaus, double hinta
			String sqlKomento = "SELECT id, laji, nimi, kuvaus, hinta FROM elain;";
			// Valmistellaan sql komento
			stmt = conn.prepareStatement(sqlKomento);
			// L‰hetet‰‰n komento
			rs = stmt.executeQuery();
			// t‰ytet‰‰n lista, niin kauan kuin kannasta lˆytyy tavaraa
			while (rs.next()) {
				elain = readElain(rs);
				// Lis‰t‰‰n el‰in listaan
				elaimet.add(elain);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		
		return elaimet;
	}
	
	/**
	 * haetaan yksi el‰in kannasta ID:n perusteella.
	 * @return palauttaa yhden el‰imen
	 */
	public Elain find(Integer id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Elain elain = null; 
		try {
			// Luodaan yhteys tietokantaan
			conn = getConnection();
			// luodaan sql komento
			// int id, String laji, String nimi, String kuvaus, double hinta
			String sqlKomento = "SELECT id, laji, nimi, kuvaus, hinta FROM elain WHERE id = " + id + ";";
			// Valmistellaan sql komento
			stmt = conn.prepareStatement(sqlKomento);
			// L‰hetet‰‰n komento
			rs = stmt.executeQuery();
			// t‰ytet‰‰n lista, niin kauan kuin kannasta lˆytyy tavaraa
			while (rs.next()) {
				elain = readElain(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}
		
		return elain;
	}
		
		/**
		 * Lukee tietokannasta taulusta yhden tietorivin (el‰in tiedot). 
		 * Luo ja palauttaa tietojen perusteella El‰in-tyyppisen olion
		 * 
		 * @param rs tietokannasta kyselyll‰ haettu tulostaulu
		 * @return El‰in El‰in-olio
		 */
		private Elain readElain(ResultSet rs) {
			// Haetaan yhden el‰imen tiedot kyselyn tulostaulun 
			//(ResultSet-tyyppinen rs-olion) aktiiviselta tietorivilt‰
			try {
				// int id, String laji, String nimi, String kuvaus, double hinta
				int id = rs.getInt("id");
				String laji = rs.getString("laji");
				String nimi = rs.getString("nimi");
				String kuvaus = rs.getString("kuvaus");
				double hinta = rs.getDouble("hinta");
				
				// luodaan ja palautetaan uusi el‰in olio
				return new Elain(id, laji, nimi, kuvaus, hinta);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
}
