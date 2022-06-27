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

import model.ProdottoBean;
import model.ProdottoDAO;

@WebServlet("/ControlloProdotti")
public class ControlloProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlloProdotti() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("read")) {
					String cod = request.getParameter("cod");
					request.removeAttribute("product");
					request.setAttribute("product", ProdottoDAO.retrievebyCodice(cod));
				} else if (action.equalsIgnoreCase("delete")) {
					String cod = request.getParameter("cod");
					ProdottoDAO.deleteProdottobyCodice(cod);
				} else if (action.equalsIgnoreCase("insert")) {
					String nome = request.getParameter("nome");
					String descrizione = request.getParameter("descrizione");
					Double prezzo = Double.parseDouble(request.getParameter("prezzo"));
					Integer quantita = Integer.parseInt(request.getParameter("quantita"));
					Integer iva = Integer.parseInt(request.getParameter("iva"));
					Date rilascio = java.sql.Date.valueOf(LocalDate.parse(request.getParameter("rilascio"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
					String genere = request.getParameter("genere");
					String piattaforma = request.getParameter("piattaforma");
					String sviluppatore = request.getParameter("dev");
					String trailer = request.getParameter("trailer");
					String imgpath = request.getParameter("imgpath");
					
					ProdottoBean bean = new ProdottoBean();
					bean.setNome(nome);
					bean.setDescrizione(descrizione);
					bean.setPrezzo(prezzo);
					bean.setQuantità(quantita);
					bean.setIva(iva);
					bean.setData_rilascio((java.sql.Date) rilascio);
					bean.setPiattaforma(piattaforma);
					bean.setSviluppatore(sviluppatore);
					bean.setTrailer(trailer);
					bean.setImgPath(imgpath);
					bean.setGenere(genere);
					ProdottoDAO.saveProdotto(bean);
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		try {
			request.removeAttribute("products");
			request.setAttribute("products", ProdottoDAO.retrieveAll());
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("GestioneProdotti.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
