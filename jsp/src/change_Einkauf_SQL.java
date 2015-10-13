

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
		  
		
		int ID = Integer.valueOf(request.getParameter("i_einkauf_id"));
		int anzahl = Integer.valueOf(request.getParameter("i_einkauf_anzahl"));
		String datum = request.getParameter("i_einkauf_datum");
		String k_ID_ = request.getParameter("i_einkauf_konto_cmb");
		String m_ID_ = request.getParameter("i_einkauf_markt_cmb");
		String p_ID_ = request.getParameter("i_einkauf_produkt_cmb");
		int k_ID = Integer.valueOf(k_ID_.split(" ")[0]);
		int m_ID = Integer.valueOf(m_ID_.split(" ")[0]);
		int p_ID = Integer.valueOf(p_ID_.split(" ")[0]);

	   
	      access.SQLModifizieren(
	    			Controll_Statments.UpdateEinkauf(anzahl, datum, k_ID, p_ID, m_ID, ID));
	      
		
		out.println("Einkauf erfolgreich geupdatet");
		access.auslesen();
		 request.getRequestDispatcher("login-success.jsp").forward(request, response);
		
	}

}
