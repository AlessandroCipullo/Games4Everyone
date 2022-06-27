package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ProdottoDAO {
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
	
	public static ProdottoBean retrievebyCodice(String cod) throws SQLException {
		ProdottoBean prodotto = new ProdottoBean();
		Connection con = null;
		PreparedStatement ps = null;
		String checksql = "SELECT * FROM prodotto WHERE Cod_Prodotto = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(checksql);
		ps.setString(1, cod);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			prodotto.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prodotto.setNome(rs.getString("Nome"));
			prodotto.setPiattaforma(rs.getString("Piattaforma"));
			prodotto.setDescrizione(rs.getString("Descrizione"));
			prodotto.setPrezzo(rs.getDouble("Prezzo"));
			prodotto.setSviluppatore(rs.getString("Sviluppatore"));
			prodotto.setImgPath(rs.getString("ImgPath"));
			prodotto.setQuantità(rs.getInt("Quantita"));
			prodotto.setIva(rs.getInt("Iva"));
			prodotto.setData_rilascio(rs.getDate("Data_Rilascio"));
			prodotto.setTrailer(rs.getString("Trailer"));
			prodotto.setGenere(rs.getString("Genere"));
		}
		con.close();
		return prodotto;
	}
	
	public static void saveProdotto(ProdottoBean prodotto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String insertsql = "INSERT INTO prodotto "
				+ "(Nome, Prezzo, Iva, Data_Rilascio, Quantita, Descrizione, Trailer, ImgPath, Sviluppatore, Genere, Piattaforma) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = ds.getConnection();
		con.setAutoCommit(false);
		ps = con.prepareStatement(insertsql);
		ps.setString(1, prodotto.getNome());
		ps.setDouble(2, prodotto.getPrezzo());
		ps.setInt(3, prodotto.getIva());
		ps.setDate(4, prodotto.getData_rilascio());
		ps.setInt(5, prodotto.getQuantità());
		ps.setString(6, prodotto.getDescrizione());
		ps.setString(7, prodotto.getTrailer());
		ps.setString(8, prodotto.getImgPath());
		ps.setString(9, prodotto.getSviluppatore());
		ps.setString(10, prodotto.getGenere());
		ps.setString(11, prodotto.getPiattaforma());
		
		if(ps.executeUpdate() > 0) {
			con.commit();
			return;
		}
		con.commit();
		throw new SQLException("Prodotto non inserito");
	}
	
	public static boolean deleteProdottobyCodice(String cod) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		int result = 0;
		String deleteSQL = "DELETE FROM prodotto WHERE Cod_Prodotto = ?";

		con = ds.getConnection();
		preparedStatement = con.prepareStatement(deleteSQL);
		preparedStatement.setString(1, cod);
		result = preparedStatement.executeUpdate();
		con.close();

		return (result != 0);
	}
	
	public static Collection<ProdottoBean> retrieveAll() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM prodotto";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> filterByConsole(String console) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM prodotto WHERE Piattaforma = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, console);
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setGenere(rs.getString("Genere"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> searchByText(String ricerca) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM prodotto WHERE (lower(Nome) LIKE ?)";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, "%" + ricerca + "%");
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> orderBySales(String console) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		con = ds.getConnection();
		String retrieve = "SELECT * FROM prodotto ORDER BY Vendite DESC";
		
		if(!console.equals("null")) {
			retrieve = "SELECT * FROM prodotto WHERE Piattaforma = ? ORDER BY Vendite DESC";
			ps = con.prepareStatement(retrieve);
			ps.setString(1, console);
		}else {
			ps = con.prepareStatement(retrieve);
		}
		
		ResultSet rs = ps.executeQuery();
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> orderByReleaseDate(String console) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		con = ds.getConnection();
		String retrieve = "SELECT * FROM prodotto WHERE Data_Rilascio < ? ORDER BY Data_Rilascio DESC";
		
		if(!console.equals("null")) {
			retrieve = "SELECT * FROM prodotto WHERE Data_Rilascio < ? AND Piattaforma = ? ORDER BY Data_Rilascio DESC";
			ps = con.prepareStatement(retrieve);
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setString(2, console);
		}else {
			ps = con.prepareStatement(retrieve);
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
		}
		
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> orderByPreorder(String console) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		con = ds.getConnection();
		String retrieve = "SELECT * FROM prodotto WHERE Data_Rilascio > ? ORDER BY Data_Rilascio DESC";
		
		if(!console.equals("null")) {
			retrieve = "SELECT * FROM prodotto WHERE Data_Rilascio > ? AND Piattaforma = ? ORDER BY Data_Rilascio DESC";
			ps = con.prepareStatement(retrieve);
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			ps.setString(2, console);
		}else {
			ps = con.prepareStatement(retrieve);
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
		}
;
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static Collection<ProdottoBean> filterByGenre(String console, String genere) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		con = ds.getConnection();
		String retrieve = "SELECT * FROM prodotto WHERE Genere = ? ORDER BY Data_Rilascio DESC";
		
		if(!console.equals("null")) {
			retrieve = "SELECT * FROM prodotto WHERE Genere = ? AND Piattaforma = ? ORDER BY Data_Rilascio DESC";
			ps = con.prepareStatement(retrieve);
			ps.setString(1, genere);
			ps.setString(2, console);
		}else {
			ps = con.prepareStatement(retrieve);
			ps.setString(1, genere);
		}
;
		ResultSet rs = ps.executeQuery();
		
		Collection<ProdottoBean> prodotti = new ArrayList<>();
		while(rs.next()) {
			ProdottoBean prod = new ProdottoBean();
			
			prod.setCod_prodotto(rs.getString("Cod_Prodotto"));
			prod.setNome(rs.getString("Nome"));
			prod.setGenere(rs.getString("Genere"));
			prod.setPiattaforma(rs.getString("Piattaforma"));
			prod.setDescrizione(rs.getString("Descrizione"));
			prod.setPrezzo(rs.getDouble("Prezzo"));
			prod.setSviluppatore(rs.getString("Sviluppatore"));
			prod.setImgPath(rs.getString("ImgPath"));
			prod.setQuantità(rs.getInt("Quantita"));
			prod.setIva(rs.getInt("Iva"));
			prod.setData_rilascio(rs.getDate("Data_Rilascio"));
			prod.setTrailer(rs.getString("Trailer"));
			
			prodotti.add(prod);
		}
		con.close();
		return prodotti;
	}
	
	public static void boughtOperation(String cod) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String updatesql = "UPDATE prodotto SET Quantita = Quantita - 1, Vendite = Vendite + 1 WHERE Cod_Prodotto = ?";
		con = ds.getConnection();
		con.setAutoCommit(false);
		ps = con.prepareStatement(updatesql);
		ps.setString(1, cod);
		
		if(ps.executeUpdate() > 0) {
			con.commit();
			return;
		}
		con.commit();
		throw new SQLException("Operazioni di incremento vendita non eseguite");
	}
}
