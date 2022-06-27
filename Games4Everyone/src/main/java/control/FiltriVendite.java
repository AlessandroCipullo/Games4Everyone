package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProdottoDAO;

@WebServlet("/FiltriVendite")
public class FiltriVendite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FiltriVendite() {
        super();
    }

    // Crea dei filtri definiti in ProdottoDAO in base alla console correntemente selezionata dall'utente
    // Se nessuna console è selezionata, il filtro viene applicato su tutti i prodotti del catalogo

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		String console = request.getParameter("console");
		
			try {
				if(filtro.equalsIgnoreCase("vendite")) {
					request.setAttribute("prodotti", ProdottoDAO.orderBySales(console));
				}
				if(filtro.equalsIgnoreCase("data")) {
					request.setAttribute("prodotti", ProdottoDAO.orderByReleaseDate(console));
				}
				if(filtro.equalsIgnoreCase("preorder")) {
					request.setAttribute("prodotti", ProdottoDAO.orderByPreorder(console));
				}
				if(filtro.equalsIgnoreCase("genere")) {
					request.setAttribute("prodotti", ProdottoDAO.filterByGenre(console, request.getParameter("genere")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		request.setAttribute("console", console);
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
