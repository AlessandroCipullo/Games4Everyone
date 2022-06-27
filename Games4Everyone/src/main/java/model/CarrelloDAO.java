package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CarrelloBean.ProdottoCarrello;

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

	public static void saveCarrello(CarrelloBean cart, String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO carrello "
				+ "(ID_Carrello, Cod_Prodotto, Selezionati) VALUES (?, ?, ?)";
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		Iterator<ProdottoCarrello> it = cart.getProdotti().iterator();
		while(it.hasNext()) {
			ProdottoCarrello p = it.next();
			ps.setString(1, id);
			ps.setString(2, p.prod.cod_prodotto);
			ps.setInt(3, p.quantita);
			ps.executeUpdate();
		}
		con.close();
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
		if(rs.next()) {
			con.close();
			return true;
		}
		con.close();
		return false;
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
	
	public static void setSelezionati(String id_carrello, String cod_prodotto, Integer selezionati) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String update = "UPDATE carrello SET Selezionati = ? WHERE ID_Carrello = ? AND Cod_Prodotto = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(update);
		ps.setInt(1, selezionati);
		ps.setString(2, id_carrello);
		ps.setString(3, cod_prodotto);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Quantità non modificata");
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
	
	public static void eraseCarrello(String id_carrello) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String delete = "DELETE FROM carrello WHERE Id_Carrello = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(delete);
		ps.setString(1, id_carrello);
		
		if(ps.executeUpdate() > 0) {
			con.close();
			return;
		}
		con.close();
		throw new SQLException("Prodotti non eliminati dal carrello!");
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
	public static Double totCarrello(String id_carrello) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String totsql = "SELECT SUM(prodotto.Prezzo * carrello.Selezionati) as Totale "
				+ "FROM carrello INNER JOIN prodotto ON carrello.Cod_Prodotto=prodotto.Cod_Prodotto "
				+ "WHERE ID_Carrello = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(totsql);
		ps.setString(1, id_carrello);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		Double totale = rs.getDouble("Totale");
		con.close();
		return totale;
	}
	
	public static Integer howManyLeft(ProdottoBean prod) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String check = "SELECT Quantita FROM prodotto WHERE Cod_Prodotto = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(check);
		ps.setString(1, prod.getCod_prodotto());
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		return rs.getInt("Quantita");
	}
}
