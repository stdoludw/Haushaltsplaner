package jsp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Controller() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
		
		String name=request.getParameter("i_username");  
        String password=request.getParameter("i_passwort");  
        String db=request.getParameter("i_datenbank");  

        access as = new access();
        as.login(name, password, db);
        access.auslesen();
        
                
        if(as.status())
        {  
        	response.sendRedirect("login-success.jsp");
        }  
        else if(!as.status())
        {  
        	response.sendRedirect("login-error.jsp");

        } 
        
   
     

	
	}

}
