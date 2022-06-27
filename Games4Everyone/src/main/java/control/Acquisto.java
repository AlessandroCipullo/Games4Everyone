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
import model.OrdineDAO;
import model.PagamentoDAO;
import model.UtenteBean;

@WebServlet("/Acquisto")
public class Acquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Acquisto() {
        super();
    }

    // Ottiene l'utente dalla sessione, redirecta a login se l'utente non è loggato
    // Se è stata selezionata l'azione di save dal carrello, verifica che esista un carrello associato a quell'utente
    // Se non c'è lo crea ed inserisce tutti i prodotti che il cliente aveva precedentemente inserito
    
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
			// S per salva 	A per acquista
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
			if(request.getParameter("action").equals("a")) {
				Double totale = CarrelloDAO.totCarrello(id);
				String codicePag = request.getParameter("codice");
				String idPagUtente = PagamentoDAO.retrieveByCodice(codicePag).getId_pag_utente();
				if(PagamentoDAO.canAfford(totale, codicePag, id) == true) {
					PagamentoDAO.decreaseFromAccount(totale, codicePag, id);					
					String idOrdine = OrdineDAO.createOrdine(totale, id, idPagUtente);
					OrdineDAO.moveFromCarrello(id, idOrdine);
					CarrelloDAO.eraseCarrello(id);
				}
				/* else L'acquisto non è andato in porto */
				
				RequestDispatcher rd = request.getRequestDispatcher("AccountUtente.jsp");
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
