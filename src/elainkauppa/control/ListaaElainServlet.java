package elainkauppa.control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elainkauppa.model.Elain;
import elainkauppa.model.dao.ElainDAO;

/**
 * Servlet implementation class ListaaElainServlet
 */
@WebServlet(description = "Listaa el�imi�", urlPatterns = { "/listaa-elain" })
public class ListaaElainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * ListaaElainServletin doGet-metodi lis�� request-olion attribuutiksi elainlistan (ArrayList) 
ja kutsuu elainlista.jsp:t�.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String jsp = "/view/elainlista.jsp";
		// luodaan ElainDAO
		ElainDAO elainDao = new ElainDAO();
		// luodaan lista tietokannasta l�ytyneist� el�imist�
		ArrayList<Elain> elaimet = elainDao.findAll();
		
		// Talletetaan request-olion alle pizzalista, jotta tiedot ovat k�yt�ss� jsp:ll�
		request.setAttribute("elaimet", elaimet);
		
		// l�het� selaimelta tullut pyynt� servletilt� edelleen jsp:lle
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(jsp);
		dispather.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
