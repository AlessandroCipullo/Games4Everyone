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

/**
 * Servlet implementation class FiltroConsole
 */
@WebServlet("/FiltroConsole")
public class FiltroConsole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FiltroConsole() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("prodotti", ProdottoDAO.filterByConsole(request.getParameter("console").toString()));
			request.setAttribute("console", request.getParameter("console").toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
