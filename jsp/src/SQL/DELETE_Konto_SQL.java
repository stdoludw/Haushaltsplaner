package SQL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import CONTROLLER.CONTROLLER_Statments;


/**
 * Servlet implementation class Del_Konto_SQL
 */
@WebServlet("/DELETE_Konto_SQL")
public class DELETE_Konto_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DELETE_Konto_SQL() {
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

		int k_ID = Integer.valueOf(request.getParameter("k_ID"));	

		session.setAttribute("site",CONTROLLER_Statments.caller.Delete_Konto.toString()); 
		 session.setAttribute(CONTROLLER_Statments.session.Delete_Konto.toString(),k_ID);
		
		out.println("Konto erfolgreich entfernt");
		RequestDispatcher rd = request.getRequestDispatcher(CONTROLLER_Statments.redirect.Controller.toString());
		rd.include(request, response);
		
		response.sendRedirect(CONTROLLER_Statments.redirect.login_success.toString());		
		

	
	}

}
