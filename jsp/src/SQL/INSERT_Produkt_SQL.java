package SQL;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MODEL.MODEL_Produkt;



/**
 * Servlet implementation class Add_Produkt_SQL
 */
@WebServlet("/Add_Produkt_SQL")
public class INSERT_Produkt_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public INSERT_Produkt_SQL() {
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
		
		session.setAttribute("Add_Produkt",new MODEL_Produkt(
					request.getParameter("i_produkt_name"),		
					Integer.valueOf(request.getParameter("i_produkt_gewicht")),
					Float.valueOf(request.getParameter("i_produkt_preis"))
					,-1));
			
		session.setAttribute("site","Add_Produkt_SQL");
			out.println("Produkt erfolgreich hinzugefuegt");
			response.sendRedirect("login-success.jsp");

	}

}
