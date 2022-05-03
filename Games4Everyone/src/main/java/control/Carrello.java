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

@WebServlet("/CarrelloOp")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Carrello() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String cod = request.getParameter("cod");
		String action = request.getParameter("action");
		if(id.equals("null")) {
			response.sendRedirect("Login-Form.jsp");
			return;
		}
		
		// A = aggiungi R = rimuovi
		try {
			if(!CarrelloDAO.checkCarrello(id)) {
				CarrelloDAO.createCarrello(id);
			}
			if(action.equals("a")) {
				CarrelloDAO.addToCarrello(id, cod);
				
			}
			if(action.equals("r")) {
				CarrelloDAO.removeFromCarrello(id, cod);
			}
			
			CarrelloBean cart = CarrelloDAO.retrieveByCodice(id);
			request.setAttribute("cart", cart);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("Carrello.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
