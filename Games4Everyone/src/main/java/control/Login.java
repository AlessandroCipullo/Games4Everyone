package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UtenteDAO;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
        super();
    }

    // Al momento del login, se l'utente non possiede già un carrello, i prodotti che ha inserito mentre non era loggato
    // diventeranno parte del suo carrello.

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		request.getSession().setAttribute("adminRoles", false);
		
		String redirectedPage;
		try {
			checkLogin(username, password);
			request.getSession().setAttribute("loggedUserData", UtenteDAO.retrievebyUsername(username));
			if(request.getSession().getAttribute("cart") != null) {
				response.sendRedirect("Acquisto?action=s");
				return;
			}
			redirectedPage = "/Home.jsp";
			if(UtenteDAO.isAdmin(username)) {
				redirectedPage = "/GestioneProdotti.jsp";
				request.getSession().setAttribute("adminRoles", true);
			}
		} catch (Exception e) {
			request.getSession().removeAttribute("loggedUserData");
			redirectedPage = "/LoginError.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}
	
	private void checkLogin(String username, String password) throws Exception {
		if(UtenteDAO.canAccess(username, password)) {
			return;
		}else {			
			throw new Exception("Credenziali sbagliate");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
