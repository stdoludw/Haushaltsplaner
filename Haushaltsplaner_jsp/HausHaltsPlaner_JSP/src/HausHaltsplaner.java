

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/HausHaltsplaner")
public class HausHaltsplaner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public HausHaltsplaner() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Controll_Main coMa = new Controll_Main();
		coMa.acces();
		//coMa.auslesen();
		
		
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<html>");
		writer.println("<head><title>Hello World Servlet</title></head>");
		writer.println("<body>");
		writer.println("	<h1>Hello World from a Sevlet!</h1>");
		

		/*if( coMa.getMvecModel().get(0) instanceof Model_Produkt)
		{
			writer.println("<p>"+((Model_Produkt) coMa.getMvecModel().get(0)).getMstrName() +"</p>");
		}
		else if( coMa.getMvecModel().get(0) instanceof Model_Konto)
		{
			writer.println("<p>"+((Model_Konto) coMa.getMvecModel().get(0)).getMstrName()+"</p>");

		}
		else if( coMa.getMvecModel().get(0) instanceof Model_Markt)
		{
			writer.println("<p>"+((Model_Markt) coMa.getMvecModel().get(0)).getMstrName()+"</p>");

		}
		else if( coMa.getMvecModel().get(0) instanceof Model_Einkauf)
		{
			writer.println("<p>"+((Model_Einkauf) coMa.getMvecModel().get(0)).getMintID()+"</p>");

		}*/
		
		writer.print("<p>"+coMa.getMvecModel().size()+"</p>");
		writer.println("<body>");
		writer.println("</html>");
			
		writer.close();	
	
	
	
	
	
	
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
