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

@WebServlet("/FiltriVendite")
public class FiltriVendite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FiltriVendite() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filtro = request.getParameter("filtro");
		String console = request.getParameter("console");
			try {
				if(filtro.equalsIgnoreCase("vendite")) {
					request.setAttribute("prodotti", ProdottoDAO.orderBySales(console));
				}
				if(filtro.equalsIgnoreCase("data")) {
					request.setAttribute("prodotti", ProdottoDAO.orderByReleaseDate(console));
				}
			} catch (SQLException e) {
				System.out.println("azz");
				e.printStackTrace();
			}
			
		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
