package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.CarrelloBean.ProdottoCarrello;

public class OrdineDAO {
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
	
	public static String createOrdine(Double totale, String id_utente, String id_pag) throws SQLException {
		LocalTime now = LocalTime.now();
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO ordine "
				+ "(Data, Totale, ID_Utente, ID_Pag_Utente, Orario) "
				+ "VALUES (?, ?, ?, ?, ?)";
		String findsql = "SELECT ID_Ordine FROM ordine WHERE ID_Utente = ? AND ID_Pag_Utente = ? AND Orario = ?";
		
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		
		ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
		ps.setDouble(2, totale);
		ps.setInt(3, Integer.parseInt(id_utente));
		ps.setInt(4, Integer.parseInt(id_pag));
		ps.setTime(5, java.sql.Time.valueOf(now));
		
		if(ps.executeUpdate() > 0) {
			
			ps = con.prepareStatement(findsql);
			ps.setInt(1, Integer.parseInt(id_utente));
			ps.setInt(2, Integer.parseInt(id_pag));
			ps.setTime(3, java.sql.Time.valueOf(now));
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getString("ID_Ordine");
			}
			
			con.close();;
			throw new SQLException("Ordine non creato!");
		}
		con.close();
		throw new SQLException("Ordine non creato!");
	}
	
	public static void moveFromCarrello(String id_carrello, String id_ordine) throws SQLException {
		CarrelloBean cart = CarrelloDAO.retrieveByCodice(id_carrello);
		Iterator<ProdottoCarrello> it = cart.getProdotti().iterator();
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO prodottiordinati "
				+ "(Prezzo, Iva, Quantita, Prezzo_Totale, ID_Ordine, Cod_Prodotto, Nome) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		con = ds.getConnection();
		ps = con.prepareStatement(insertsql);
		
		while(it.hasNext()) {
			ProdottoCarrello p = it.next();
			ps.setDouble(1, p.prod.getPrezzo());
			ps.setInt(2, p.prod.getIva());
			ps.setInt(3, p.quantita);
			// Calcolo con Iva e sconto
			
			/*Double prezzoNetto = p.prod.getPrezzo() - p.prod.getSconto() + ((p.prod.getPrezzo() - p.prod.getSconto()) * p.prod.getIva() / 100);
			Double prezzoTot = prezzoNetto * p.quantita;*/
			
			// Calcolo senza Iva e sconto
			Double prezzoTot = p.prod.getPrezzo() * p.quantita;
			ps.setDouble(4, prezzoTot);
			ps.setString(5, id_ordine);
			ps.setInt(6, Integer.parseInt(p.prod.getCod_prodotto()));
			ps.setString(7, p.prod.getNome());
			ps.executeUpdate();
		}
		con.close();
	}
	
	public static Collection<OrdineBean> retrieveById(String id) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM ordine WHERE ID_Utente = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		Collection<OrdineBean> ordini = new ArrayList<>();
		while(rs.next()) {
			OrdineBean ord = new OrdineBean();
			
			ord.setData(rs.getDate("Data"));
			ord.setId_ordine(rs.getString("ID_Ordine"));
			ord.setId_pag_utente(rs.getString("ID_Pag_Utente"));
			ord.setTotale(rs.getDouble("Totale"));
			
			ordini.add(ord);
		}
		con.close();
		return ordini;
	}
}
