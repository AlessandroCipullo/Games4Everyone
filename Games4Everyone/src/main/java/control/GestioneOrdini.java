package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdinatiDAO;
import model.OrdineDAO;
import model.UtenteBean;

@WebServlet("/GestioneOrdini")
public class GestioneOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GestioneOrdini() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
		String idUtente = utente.getId_utente();
		String action = request.getParameter("action");
		
		// V = visualizza ordini P = visualizza prodotti ordinati
		if(action.equalsIgnoreCase("v")) {
			try {
				request.setAttribute("ordini", OrdineDAO.retrieveById(idUtente));
				request.setAttribute("ordinati", null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(action.equalsIgnoreCase("p")) {
			try {
				request.setAttribute("ordinati", OrdinatiDAO.retrieveByOrdine(request.getParameter("ordineid")));
				request.setAttribute("ordini", null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("OrdiniUtente.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
