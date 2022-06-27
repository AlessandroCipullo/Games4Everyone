package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PagamentoBean;
import model.PagamentoDAO;
import model.UtenteBean;

@WebServlet("/MetodiPagamento")
public class MetodiPagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MetodiPagamento() {
        super();
    }

    // Aggiunge una carta tramite la form con action add
    // Visualizza tutti i metodi di pagamento dell'utente nel caso in cui richieda di visualizzarlo dalla sua pagina personale
    // Redirecta alla pagina di selezione del metodo di pagamento nel caso di action choose
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		UtenteBean utente = (UtenteBean) request.getSession().getAttribute("loggedUserData");
		String idUtente = utente.getId_utente();
		String redirect = "AccountUtente.jsp";
		
		try {
			if(action != null) {
				if(action.equals("add")) {
					String tipo = request.getParameter("tipo");
					String provider = request.getParameter("provider");
					String codice = request.getParameter("codice");
					Date scadenza = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("scadenza"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					
					PagamentoBean pag = new PagamentoBean();
					pag.setId_utente(idUtente);
					pag.setTipo(tipo);
					pag.setProvider(provider);
					pag.setCodice(codice);
					pag.setScadenza(scadenza);
					
					PagamentoDAO.saveCard(pag);
				}
				if(action.equals("choose")) {
					redirect = "SceltaPagamento.jsp";
				}
			}
		}catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}	
		
		try {
			request.removeAttribute("metodi");
			request.setAttribute("metodi", PagamentoDAO.retrieveAll(idUtente));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirect);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
