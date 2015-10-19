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
 * Servlet implementation class usermanagment
 */
@WebServlet("/usermanagment")
public class CREATE_User extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CREATE_User() {
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
		HttpSession session = request.getSession(true);

		String name = request.getParameter("i_username");
		String password = request.getParameter("i_passwort");
		String password2 = request.getParameter("i_passwort2");
		String db = request.getParameter("i_datenbank");

		if (password.equals(password2)) {

			session.setAttribute("site","usermanagement");
			out.println("User und Datenbank erfolgreich geupdatet");
			out.println("Du hast folgenden User angelegt: " + name);
			out.println("Mit folgendem Passwort: " + password);
			out.println("Auf folgender Datenbank: HausHaltsPlaner_" + name);

			String[] tmp = new String[3];
			tmp[0] = name;
			tmp[1] = password;
			tmp[2] = db;

			session.setAttribute("usermanagement_data", tmp);
			response.sendRedirect("login-success.jsp");
		} else {
			response.sendRedirect("error.jsp");

		}
	}
}
