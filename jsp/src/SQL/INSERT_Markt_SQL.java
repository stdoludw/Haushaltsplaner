package SQL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MODEL.MODEL_Markt;



/**
 * Servlet implementation class Add_Produkt_SQL
 */
@WebServlet("/Add_Markt_SQL")
public class INSERT_Markt_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public INSERT_Markt_SQL() {
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

			session.setAttribute("Add_Markt",new MODEL_Markt(
							request.getParameter("i_markt_name"),
							request.getParameter("i_markt_plz"),
							request.getParameter("i_markt_adresse"),
							Integer.valueOf(request.getParameter("i_markt_entfernung")),-1));
			
			session.setAttribute("site","Add_Markt_SQL"); 

			out.println("Markt erfolgreich hinzugefuegt");
			response.sendRedirect("login-success.jsp");
			
			
	}

}
