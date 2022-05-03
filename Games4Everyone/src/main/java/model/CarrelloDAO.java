package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CarrelloDAO {
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
	
	public static void createCarrello(String id_utente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO carrello "
				+ "(ID_Carrello) VALUES (?)";
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		ps.setString(1, id_utente);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Carrello non creato");
	}
	
	public static Boolean checkCarrello(String id_utente) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT * FROM carrello "
				+ "WHERE ID_Carrello = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, id_utente);
		
		ResultSet rs = ps.executeQuery();
		// Inserire il con.close
		return rs.next();
	}
	
	public static void addToCarrello(String id_carrello, String cod_prodotto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insert = "INSERT INTO carrello (ID_Carrello, Cod_Prodotto, Selezionati) "
				+ "VALUES (?, ?, 1)";
		String add = "UPDATE carrello SET Selezionati = Selezionati + 1 WHERE ID_Carrello = ? AND Cod_Prodotto = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(add);
		ps.setString(1, id_carrello);
		ps.setString(2, cod_prodotto);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		
		ps = con.prepareStatement(insert);
		ps.setString(1, id_carrello);
		ps.setString(2, cod_prodotto);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Prodotto non inserito nel carrello");
	}
	
	public static void removeFromCarrello(String id_carrello, String cod_prodotto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String delete = "DELETE FROM carrello WHERE Id_Carrello = ? AND Cod_Prodotto = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(delete);
		ps.setString(1, id_carrello);
		ps.setString(2, cod_prodotto);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Voce non eliminata dal carrello");
	}
	public static CarrelloBean retrieveByCodice(String id_carrello) throws SQLException {
		CarrelloBean cart = new CarrelloBean();
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM carrello INNER JOIN prodotto ON carrello.Cod_Prodotto=prodotto.Cod_Prodotto "
				+ "WHERE ID_Carrello = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, id_carrello);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			ProdottoBean prodotto = new ProdottoBean();
				prodotto.setCod_prodotto(rs.getString("Cod_Prodotto"));
				prodotto.setNome(rs.getString("Nome"));
				prodotto.setGenere(rs.getString("Genere"));
				prodotto.setPiattaforma(rs.getString("Piattaforma"));
				prodotto.setDescrizione(rs.getString("Descrizione"));
				prodotto.setPrezzo(rs.getDouble("Prezzo"));
				prodotto.setSviluppatore(rs.getString("Sviluppatore"));
				prodotto.setImgPath(rs.getString("ImgPath"));
				prodotto.setQuantità(rs.getInt("Quantita"));
				prodotto.setIva(rs.getInt("Iva"));
				prodotto.setData_rilascio(rs.getDate("Data_Rilascio"));
				prodotto.setTrailer(rs.getString("Trailer"));
				
			cart.setId_carrello(rs.getString("ID_Carrello"));
			cart.setProdotti(prodotto, rs.getInt("Selezionati"));
		}
		con.close();
		return cart;
	}
}
