

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
 * Servlet implementation class Del_Markt_SQL
 */
@WebServlet("/Del_Markt_SQL")
public class Del_Markt_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Del_Markt_SQL() {
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
		// TODO Auto-generated method stub
		doGet(request, response);
		
	
		
		PrintWriter out = response.getWriter(  ); 		
		int m_ID = Integer.valueOf(request.getParameter("m_ID"));	

	      access.SQLModifizieren(
	  			Controll_Statments.DeleteMarkt(m_ID));
		
		out.println("Markt erfolgreich entfernt");
		access.auslesen();
		 request.getRequestDispatcher("login-success.jsp").forward(request, response);
		
		

	}

}
