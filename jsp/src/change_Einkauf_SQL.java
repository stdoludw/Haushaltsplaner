

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
@WebServlet("/change_Einkauf_SQL")
public class change_Einkauf_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public change_Einkauf_SQL() {
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
		Controll_Statments.UpdateProdukt(
				request.getParameter("i_produkt_name"),		
				Integer.valueOf(request.getParameter("i_produkt_gewicht")),
				Float.valueOf(request.getParameter("i_produkt_preis")),
				Integer.valueOf(request.getParameter("i_produkt_id"))));
		
		out.println("Produkt erfolgreich geupdatet");
		
	}

}
