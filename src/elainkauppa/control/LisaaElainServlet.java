package elainkauppa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elainkauppa.model.Elain;
import elainkauppa.model.dao.ElainDAO;

/**
 * Servlet implementation class LisaaElainServlet
 */
@WebServlet("/lisaa-elain")
public class LisaaElainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * Toteuta LisaaUusiPizzaServletin doGet ja doPost-metodit. 
	 * Tieto tallennetaan tietokantaan  
	 * doPostmetodin lopussa metodi kutsuu response-olion sendRedirect-metodilla listaa-elaimet-servlettiä. 
	 */
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
	   	// lähetä selaimelta tullut pyyntö servletiltä edelleen jsp:lle
			String jsp = "/view/elainlomake.jsp"; 
			RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
			dispather.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String virheViesti = "Virhe tapahtunut!";
		try {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// Haetaan lomakkeella syötetyn henkilon tiedot request-oliolta
		String nimi = request.getParameter("nimi"); // eläimen nimi
		String laji = request.getParameter("laji"); // eläimen laji
		String kuvaus = request.getParameter("kuvaus"); // eläimen kuvaus
		String hintaStr = request.getParameter("hinta"); // eläimen hinta
		// muutetaan pilkut pisteiksi, jotta ohjelma ei kaatuisi
		hintaStr = hintaStr.replace(",", ".");
		Double hinta = new Double(hintaStr);
		if(hinta < 0) {
			response.sendRedirect("listaa-elain?viesti=" + "Hinta ei voi olla negatiivinen!");
			return;
		}
		
		// luodaan uusi eläin
		Elain uusiElain = new Elain(nimi, laji, kuvaus, hinta);
		
		// luodaan uusi eläinDao
		ElainDAO elainDao = new ElainDAO();
		
		try {
			elainDao.addNew(uusiElain);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// doPostmetodin lopussa metodi kutsuu response-olion sendRedirect-metodilla listaa-elaimet-servlettiä.
		String viesti = "nimi=";
		
		
		response.sendRedirect("listaa-elain?" + viesti + uusiElain.getNimi());	
		
		}  catch (Exception e) {
			response.sendRedirect("listaa-elain?=" + virheViesti);
		}
		
	}

}
