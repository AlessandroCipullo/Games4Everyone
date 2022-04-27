package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class UtenteDAO {
	private static DataSource ds;
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/g4eDB");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public static boolean isAdmin(String username) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT IsAdmin FROM utente WHERE Username = ?";
		
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			if(rs.getInt("IsAdmin") == 1) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public static boolean canAccess(String username, String password) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT * FROM utente WHERE Username = ? AND Password = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			con.close();
			return true;
		}
		con.close();
		return false;
	}
	
	public static UtenteBean retrievebyUsername(String username) throws SQLException {
		UtenteBean utente = new UtenteBean();
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT * FROM utente WHERE Username = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			utente.setId_utente(rs.getString("ID_Utente"));
			utente.setNome(rs.getString("Nome"));
			utente.setCognome(rs.getString("Cognome"));
			utente.setCellulare(rs.getString("Cellulare"));
			utente.setUsername(rs.getString("Username"));
			utente.setPassword(rs.getString("Password"));
		}
		con.close();
		return utente;
	}
	
	public static void saveUser(UtenteBean utente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO utente (Nome, Cognome, Cellulare, Username, Password) "
				+ "VALUES (?, ?, ?, ?, ?)";
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		ps.setString(1, utente.getNome());
		ps.setString(2, utente.getCognome());
		ps.setString(3, utente.getCellulare());
		ps.setString(4, utente.getUsername());
		ps.setString(5, utente.getPassword());
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Utente non inserito");
	}
}
