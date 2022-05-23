package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CarrelloDAO;

@WebServlet("/TotaleCarrello")
public class TotaleCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TotaleCarrello() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Double totCarrello = CarrelloDAO.totCarrello(request.getParameter("id"));
			response.getWriter().write(String.valueOf(totCarrello));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
