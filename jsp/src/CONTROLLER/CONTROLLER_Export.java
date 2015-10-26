package CONTROLLER;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/CONTROLLER_Export")
public class CONTROLLER_Export extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CONTROLLER_Export() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		 HttpSession session = request.getSession(true);

		session.setAttribute("site",CONTROLLER_Statments.caller.CONTROLLER_Export.toString()); 

		 RequestDispatcher rd = request.getRequestDispatcher(CONTROLLER_Statments.redirect.Controller.toString());
 		rd.include(request, response);
 		
		response.sendRedirect(CONTROLLER_Statments.redirect.data_xml.toString());

 				
		
	}

}
