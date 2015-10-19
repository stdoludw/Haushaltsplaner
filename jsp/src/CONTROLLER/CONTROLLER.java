package CONTROLLER;

import java.io.IOException;
import java.util.Vector;

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

@WebServlet("/Controller")
public class CONTROLLER extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CONTROLLER_Access as;
	private HttpSession session;

	public CONTROLLER() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		session = request.getSession(true);
		String site = (String) session.getAttribute("site");

		

		if (site.equals("index.jsp")) {
			
			String name = request.getParameter("i_username");
			String password = request.getParameter("i_passwort");
			String db = request.getParameter("i_datenbank");

			name = "bro";
			password = "";
			db = "HausHaltsPlaner_dlu";
			
			as = CONTROLLER_Access.init();
			as.login(name, password, db);
			as.auslesen();

			if (as.status()) {

				session.setAttribute("mvecMODEL", as.getMvecModel());
				session.setAttribute("mvStatistik", as.getStatistik());
				response.sendRedirect("login-success.jsp");
			} else {
				response.sendRedirect("error.jsp");
			}
		}
		else if(site.equals("Add_Einkauf_SQL"))
		{
			as.SQLModifizieren(((MODEL_Einkauf)session.getAttribute("Add_Einkauf")).SQlinsert());
			
		}
		else if(site.equals("Change_Einkauf_SQL"))
		{
			as.SQLModifizieren(((MODEL_Einkauf)session.getAttribute("Change_Einkauf")).SQlupdate());
		}
		else if(site.equals("Del_Einkauf_SQL"))
		{
			MODEL_Einkauf tmp = new MODEL_Einkauf();
			tmp.setMintID((int)session.getAttribute("Del_Einkauf"));
			as.SQLModifizieren(tmp.SQldelete());
		}
		else if(site.equals("Add_Konto_SQL"))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute("Add_Konto")).SQLinsert(as.getAes()));
		}
		else if(site.equals("Change_Konto_SQL"))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute("Change_Konto")).SQLUpdate(as.getAes()));
		}
		else if(site.equals("Del_Konto_SQL"))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute("Del_Konto")).SQLdelete());
		}
		else if(site.equals("Add_Markt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Markt)session.getAttribute("Add_Markt")).SQLinsert());
		}
		else if(site.equals("Change_Markt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Markt)session.getAttribute("Change_Markt")).SQLupdate());
		}
		else if(site.equals("Del_Markt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Markt)session.getAttribute("Del_Markt")).SQLdelete());
		}
		
		
		else if(site.equals("Add_Produkt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Produkt)session.getAttribute("Add_Produkt")).SQLinsert());
		}
		else if(site.equals("Change_Produkt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Produkt)session.getAttribute("Change_Produkt")).SQLupdate());
		}
		else if(site.equals("Del_Produkt_SQL"))
		{
			as.SQLModifizieren(((MODEL_Produkt)session.getAttribute("Del_Produkt")).SQLdelete());
		}
		else if(site.equals("usermanagement"))
		{
			String[] tmp = (String[])session.getAttribute("usermanagement_data");
					
			Vector<String> user =         	CONTROLLER_Statments.createUser(tmp[0], tmp[1], tmp[2]);
        	Vector<String> datenbank =     	CONTROLLER_Statments.createDatenbank(tmp[2]);
        	
        
        	for(int i=0;i<datenbank.size();i++)
        	{
        	as.SQLModifizieren(datenbank.get(i));
        	}
        	
        	for(int i=0;i<user.size();i++)
        	{
        	as.SQLModifizieren(user.get(i));
        	}
			
			
			
			
		}

	}

}
