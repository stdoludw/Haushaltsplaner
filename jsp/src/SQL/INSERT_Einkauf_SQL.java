package SQL;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MODEL.MODEL_Einkauf;
import MODEL.MODEL_Konto;
import MODEL.MODEL_Markt;
import MODEL.MODEL_Produkt;

/**
 * Servlet implementation class Add_Produkt_SQL
 */
@WebServlet("/Add_Einkauf_SQL")
public class INSERT_Einkauf_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public INSERT_Einkauf_SQL() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		PrintWriter out = response.getWriter();

		String mstrKID_ = request.getParameter("i_einkauf_konto_cmb");
		String mstrMID_ = request.getParameter("i_einkauf_markt_cmb");
		String mstrPID_ = request.getParameter("i_einkauf_produkt_cmb");
		int mintKID = Integer.valueOf(mstrKID_.split(" ")[0]);
		int mintMID = Integer.valueOf(mstrMID_.split(" ")[0]);
		int mintPID = Integer.valueOf(mstrPID_.split(" ")[0]);

		MODEL_Konto k = null;
		MODEL_Markt m = null;
		MODEL_Produkt p = null;

		HttpSession session = request.getSession(true);
		@SuppressWarnings("unchecked")
		ArrayList<Object> tmp = (ArrayList<Object>) session.getAttribute("mvecModel");

		for (Object element : tmp) {
			if (element instanceof MODEL_Produkt) {
				if (((MODEL_Produkt) element).getMintID() == mintPID) {
					p = ((MODEL_Produkt) element);
				}

			} else if (element instanceof MODEL_Markt) {
				if (((MODEL_Markt) element).getMintID() == mintMID) {
					m = ((MODEL_Markt) element);
				}
			} else if (element instanceof MODEL_Konto) {
				if (((MODEL_Konto) element).getMintID() == mintKID) {
					k = ((MODEL_Konto) element);
				}
			}

			

		}
		
		session.setAttribute("Add_Einkauf",
				new MODEL_Einkauf(Integer.valueOf(request.getParameter("i_einkauf_anzahl")),
						request.getParameter("i_einkauf_datum"), -1, k, p, m));
		session.setAttribute("site","Add_Einkauf_SQL");


		out.println("Einkauf erfolgreich hinzugefuegt");
		response.sendRedirect("login-success.jsp");
	}

}
