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
import MODEL.MODEL_Markt;



/**
 * Servlet implementation class change_Produkt_SQL
 */
@WebServlet("/UPDATE_Markt_SQL")
public class UPDATE_Markt_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UPDATE_Markt_SQL() {
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
		 HttpSession session = request.getSession(true);
		 
		 session.setAttribute(CONTROLLER_Statments.session.Update_Markt.toString(),new MODEL_Markt(
							request.getParameter("i_markt_name"),
							request.getParameter("i_markt_plz"),
							request.getParameter("i_markt_adresse"),
							Integer.valueOf(request.getParameter("i_markt_entfernung")),
							Integer.valueOf(request.getParameter("i_markt_id"))));
				
			session.setAttribute("site",CONTROLLER_Statments.caller.Update_Markt.toString()); 
			out.println("Markt erfolgreich geandert");
	    	
	    	RequestDispatcher rd = request.getRequestDispatcher(CONTROLLER_Statments.redirect.Controller.toString());
			rd.include(request, response);
			
			response.sendRedirect(CONTROLLER_Statments.redirect.login_success.toString());
	}

}
