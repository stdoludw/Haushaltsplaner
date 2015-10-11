

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.Controll_Statments;
import jsp.Model_Konto;
import jsp.Model_Markt;
import jsp.Model_Produkt;
import jsp.access;

/**
 * Servlet implementation class Add_Produkt_SQL
 */
@WebServlet("/Add_Einkauf_SQL")
public class Add_Einkauf_SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Einkauf_SQL() {
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
			
			
			String konto = request.getParameter("i_einkauf_konto_cmb");
			String markt = request.getParameter("i_einkauf_markt_cmb");
			String produkt= request.getParameter("i_einkauf_produkt_cmb");
			int k_ID = 0;
			int p_ID = 0;
			int m_ID = 0;
			
			ArrayList<Object> tmp = access.getMvecModel();
		      for (int i = 0;i<tmp.size();i++)
		      {     	
		      	if (tmp.get(i) instanceof Model_Konto) {
		      		
		      		if(((Model_Konto) tmp.get(i)).getMstrName() == konto)
		      		{
		      			k_ID = ((Model_Konto) tmp.get(i)).getMintID();
		      		}
		      		
		      	}
		      	
		      	if (tmp.get(i) instanceof Model_Markt) {
		      		
		      		if(((Model_Markt) tmp.get(i)).getMstrName() == markt)
		      		{
		      			m_ID = ((Model_Markt) tmp.get(i)).getMintID();
		      		}	      		
		      	}
		      	if (tmp.get(i) instanceof Model_Produkt) {
		      		
		      		if(((Model_Produkt) tmp.get(i)).getMstrName() == produkt)
		      		{
		      			p_ID = ((Model_Produkt) tmp.get(i)).getMintID();
		      		}		      		
		      	}
		      }
		      
		      
		      access.SQLModifizieren(
		  			Controll_Statments.AddEinkauf(
		  					Integer.valueOf(request.getParameter("i_einkauf_anzahl")), 
		  					request.getParameter("i_einkauf_datum"),
		  					k_ID, p_ID, m_ID));
			
			out.println("Einkauf erfolgreich hinzugefuegt");
	}

}
