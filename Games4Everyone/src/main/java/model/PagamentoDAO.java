package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PagamentoDAO {
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
	
	public static void saveCard(PagamentoBean pag) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO pagamentoutente "
				+ "(ID_Utente, Codice, Tipo, Provider, Scadenza, Ammontare) VALUES (?, ?, ?, ?, ?, 200)";
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		ps.setString(1, pag.getId_utente());
		ps.setString(2, pag.getCodice());
		ps.setString(3, pag.getTipo());
		ps.setString(4, pag.getProvider());
		ps.setDate(5, (java.sql.Date) pag.getScadenza());
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Metodo di pagamento non inserito!");
	}
	
	public static Collection<PagamentoBean> retrieveAll(String idutente) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM pagamentoutente WHERE ID_Utente = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, idutente);
		ResultSet rs = ps.executeQuery();
		
		Collection<PagamentoBean> metodi = new ArrayList<>();
		while(rs.next()) {
			PagamentoBean pag = new PagamentoBean();
			
			pag.setId_utente(rs.getString("ID_Utente"));
			pag.setTipo(rs.getString("Tipo"));
			pag.setProvider(rs.getString("Provider"));
			pag.setCodice(rs.getString("Codice"));
			pag.setScadenza(rs.getDate("Scadenza"));
			pag.setAmmontare(rs.getDouble("Ammontare"));
			
			metodi.add(pag);
		}
		con.close();
		return metodi;
	}
	
	public static Boolean canAfford(Double totale, String codice, String idutente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT Ammontare FROM pagamentoutente WHERE Codice = ? AND ID_Utente = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, codice);
		ps.setInt(2, Integer.parseInt(idutente));
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Double ammont = rs.getDouble("Ammontare");
			if(ammont > totale) {
				con.close();
				return true;
			}
		}
		con.close();
		return false;
	}
	
	public static void decreaseFromAccount(Double totale, String codice, String idutente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String update = "UPDATE pagamentoutente SET Ammontare = Ammontare - ? WHERE Codice = ? AND ID_Utente = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(update);
		ps.setDouble(1, totale);
		ps.setString(2, codice);
		ps.setInt(3, Integer.parseInt(idutente));
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Ammontare non scalato!");
	}
	
	public static PagamentoBean retrieveByCodice(String codice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String select = "SELECT * FROM pagamentoutente WHERE Codice = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(select);
		ps.setString(1, codice);
		
		ResultSet rs = ps.executeQuery();
		PagamentoBean pag = new PagamentoBean();
		
		while(rs.next()) {
			pag.setId_pag_utente(rs.getString("ID_Pag_Utente"));
			pag.setCodice(rs.getString("Codice"));
			pag.setAmmontare(rs.getDouble("Ammontare"));
			pag.setTipo(rs.getString("Tipo"));
			pag.setScadenza(rs.getDate("Scadenza"));
			pag.setProvider(rs.getString("Provider"));
		}
		con.close();
		return pag;
	}
	
	public static PagamentoBean retrieveById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String select = "SELECT * FROM pagamentoutente WHERE ID_Pag_Utente = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(select);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		PagamentoBean pag = new PagamentoBean();
		
		while(rs.next()) {
			pag.setId_pag_utente(rs.getString("ID_Pag_Utente"));
			pag.setCodice(rs.getString("Codice"));
			pag.setAmmontare(rs.getDouble("Ammontare"));
			pag.setTipo(rs.getString("Tipo"));
			pag.setScadenza(rs.getDate("Scadenza"));
			pag.setProvider(rs.getString("Provider"));
		}
		con.close();
		return pag;
	}
}
