package SQL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MODEL.MODEL_Konto;

/**
 * Servlet implementation class Add_Produkt_SQL
 */
@WebServlet("/Add_Konto_SQL")
public class INSERT_Konto_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public INSERT_Konto_SQL() {
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

			session.setAttribute("Add_Konto",new MODEL_Konto(
							request.getParameter("i_konto_kontoinhaber"),		
							request.getParameter("i_konto_bankleitzahl"),
							request.getParameter("i_konto_kontonummer"),
							request.getParameter("i_konto_betrag"),
							request.getParameter("i_konto_minimum"),-1));
			session.setAttribute("site","Add_Konto_SQL"); 

			
			out.println("Konto erfolgreich hinzugefuegt");
			response.sendRedirect("login-success.jsp");
	}

}
