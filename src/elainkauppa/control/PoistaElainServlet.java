package elainkauppa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elainkauppa.model.Elain;
import elainkauppa.model.dao.ElainDAO;

/**
 * Servlet implementation class PoistaElainServlet
 */
@WebServlet(description = "T�m� servlet vastaanottaa selaimesta pyynn�n poistaa el�in ja ohjaa sen daolle", urlPatterns = { "/poista-elain" })
public class PoistaElainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * Vastaan ottaa selaimelta tulevan get pyynn�n poistaa el�in.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			String elainid = request.getParameter("id");
			Integer elainnumero = Integer.parseInt(elainid);
			ElainDAO elainDao = new ElainDAO();
			Elain haluttuElain = elainDao.find(elainnumero);
			// Poistetaan el�imen tiedot tietokannasta
			elainDao.removeElain(elainnumero);
			response.sendRedirect("listaa-elain?viesti=" + "Poistettu elain: " + haluttuElain.getNimi());
			System.out.println("Poistettu elain id-numerolla " + elainnumero);	
		} catch (SQLException e) {
			throw new RuntimeException("Sovelluksessa tapahtui virhe " + e.getMessage());
		}
		
	}


}
