package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarrelloBean;
import model.CarrelloDAO;
import model.UtenteBean;

@WebServlet("/Acquisto")
public class Acquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Acquisto() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
		String id = "";
		
		if(utente != null) {
			id = utente.getId_utente();		
		}
		
		if(id.isEmpty()){
			response.sendRedirect(request.getContextPath() + "/Login-Form.jsp");
			return;
		}
		try {
			if(request.getParameter("action").equals("s")) {
				if(CarrelloDAO.checkCarrello(id) == false) {
					CarrelloBean cart = (CarrelloBean) request.getSession().getAttribute("cart");
					if(cart != null) {
						CarrelloDAO.saveCarrello(cart, id);	
					}
					request.getSession().removeAttribute("cart");
					request.setAttribute("cart", CarrelloDAO.retrieveByCodice(id));					
				}
				
				RequestDispatcher rd = request.getRequestDispatcher("Carrello.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
