

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Controll_Statments;
import jsp.access;
/**
 * Servlet implementation class usermanagment
 */
@WebServlet("/usermanagment")
public class usermanagment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public usermanagment() {
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

		
		String name=request.getParameter("i_username");  
        String password=request.getParameter("i_passwort");  
        String password2=request.getParameter("i_passwort2");  
        String db=request.getParameter("i_datenbank");  

        if(password.equals(password2))
        {
        	Vector<String> user =         	Controll_Statments.createUser(name, password, db);
        	Vector<String> datenbank =     	Controll_Statments.createDatenbank(name);
        	
        
        	for(int i=0;i<datenbank.size();i++)
        	{
        	access.SQLModifizieren(datenbank.get(i));
        	}
        	
        	for(int i=0;i<user.size();i++)
        	{
        	access.SQLModifizieren(user.get(i));
        	}
        	
        	
        }
		
    	out.println("User und Datenbank erfolgreich geupdatet");
    	out.println("Du hast folgenden User angelegt: "+name);
    	out.println("Mit folgendem Passwort: "+password);
    	out.println("Auf folgender Datenbank: HausHaltsPlaner_"+name);

		request.getRequestDispatcher("login-success.jsp").forward(request, response);
		
	}

}
