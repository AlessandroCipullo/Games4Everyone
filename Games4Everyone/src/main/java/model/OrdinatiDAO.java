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

public class OrdinatiDAO {
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
	
	public static Collection<OrdinatiBean> retrieveByOrdine(String id) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String retrieve = "SELECT * FROM prodottiordinati WHERE ID_Ordine = ?";
		con = ds.getConnection();
		ps = con.prepareStatement(retrieve);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		
		Collection<OrdinatiBean> ordinati = new ArrayList<>();
		while(rs.next()) {
			OrdinatiBean ord = new OrdinatiBean();
			
			ord.setPrezzo(rs.getDouble("Prezzo"));;
			ord.setIva(rs.getInt("Iva"));
			ord.setQuantita(rs.getInt("Quantita"));
			ord.setCod_prodotto(rs.getString("Cod_Prodotto"));
			ord.setNome(rs.getString("Nome"));
			
			ordinati.add(ord);
		}
		con.close();
		
		return ordinati;
	}
}
