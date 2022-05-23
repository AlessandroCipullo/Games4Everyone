package control;

import java.io.IOException;
import java.sql.SQLException;

import model.UtenteDAO;
import model.UtenteBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUp() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtenteBean user = new UtenteBean();
		
		user.setNome(request.getParameter("nome"));
		user.setCognome(request.getParameter("cognome"));
		user.setCellulare(request.getParameter("cellulare"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		
		String redirectedPage;
		try {
			UtenteDAO.saveUser(user);
			request.getSession().setAttribute("loggedUserData", UtenteDAO.retrievebyUsername(user.getUsername()));
			redirectedPage = "/Home.jsp";
		} catch (SQLException e) {
			redirectedPage = "/SignUp-Form.jsp";
		}
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Boolean email = UtenteDAO.checkEmail(request.getParameter("email"));
			if(email == true) {
				response.getWriter().write("Questa email e' gia' esistente!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
