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
import model.ProdottoDAO;

@WebServlet("/CarrelloOp")
public class Carrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Carrello() {
        super();
    }

    // Aggiunge, rimuove o setta la quantità di un determinato prodotto all'interno di un carrello loggato o di un carello temporaneo
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String cod = request.getParameter("cod");
		String action = request.getParameter("action");
		
		Boolean voidcart = false;
		CarrelloBean vcart = null;
		
		if(id.equals("null")) {
			voidcart = true;
			vcart = (CarrelloBean) request.getSession().getAttribute("cart");
			if(vcart == null) {
				vcart = new CarrelloBean();				
			}
		}
		// A = aggiungi R = rimuovi S = setta la quantità
		try {
			if(!CarrelloDAO.checkCarrello(id) && voidcart == false) {
				CarrelloDAO.createCarrello(id);
			}
			if(action.equals("a")) {
				if(voidcart == true) {
					if(CarrelloBean.contiene(vcart, ProdottoDAO.retrievebyCodice(cod))) {
						CarrelloBean.getProdotto(vcart, ProdottoDAO.retrievebyCodice(cod)).quantita++;
					}else {
						vcart.setProdotti(ProdottoDAO.retrievebyCodice(cod), 1);						
					}
				}else {
					CarrelloDAO.addToCarrello(id, cod);					
				}
			}
			if(action.equals("r")) {
				if(voidcart == true) {
					CarrelloBean.removeProdotto(vcart, ProdottoDAO.retrievebyCodice(cod));
				}else {
					CarrelloDAO.removeFromCarrello(id, cod);					
				}
			}
			if(action.equals("s")) {
				if(voidcart == true) {
					CarrelloBean.getProdotto(vcart, ProdottoDAO.retrievebyCodice(cod)).quantita = Integer.parseInt(request.getParameter("sel"));
				}
				else {					
					CarrelloDAO.setSelezionati(id, cod, Integer.parseInt(request.getParameter("sel")));
				}
			}
			
			CarrelloBean cart = CarrelloDAO.retrieveByCodice(id);
			if(voidcart == true) {
				request.getSession().setAttribute("cart", vcart);
			}else {
				request.setAttribute("cart", cart);				
			}
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
