package SQL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class Del_Produkt_SQL
 */
@WebServlet("/Del_Produkt_SQL")
public class DELETE_Produkt_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DELETE_Produkt_SQL() {
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
		HttpSession session = request.getSession(true);		
		int p_ID = Integer.valueOf(request.getParameter("p_ID"));
		
		session.setAttribute("site","Del_Produkt_SQL"); 
		 session.setAttribute("Del_Produkt",p_ID);
		
		out.println("Produkt erfolgreich entfernt");
    	response.sendRedirect("login-success.jsp");

		

	}

}
