

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Controll_Statments;
import jsp.access;

/**
 * Servlet implementation class change_Produkt_SQL
 */
@WebServlet("/change_Konto_SQL")
public class change_Konto_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public change_Konto_SQL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		  PrintWriter out = response.getWriter(  ); 
		  
		access.SQLModifizieren(
		Controll_Statments.UpdateKonto(
				request.getParameter("i_konto_kontoinhaber"),		
				request.getParameter("i_konto_kontonummer"),
				request.getParameter("i_konto_bankleitzahl"),
				request.getParameter("i_konto_betrag"),
				request.getParameter("i_konto_minimum"),
				Integer.valueOf(request.getParameter("i_konto_id")), 
				access.getAes()));

		
		out.println("Konto erfolgreich geupdatet");
		
	}

}
