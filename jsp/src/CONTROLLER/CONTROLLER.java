package CONTROLLER;

import java.io.IOException;
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

		

		if (site.equals(CONTROLLER_Statments.caller.index.toString())) {
			
			String name = request.getParameter("i_username");
			String password = request.getParameter("i_passwort");
			String db = request.getParameter("i_datenbank");

			name = "bro";
			password = "P@ssw0rd";
			db = "HausHaltsPlaner_dlu";
			
			as = CONTROLLER_Access.init();
			as.login(name, password, db);
			as.auslesen();

			if (as.status()) {

				session.setAttribute(CONTROLLER_Statments.session.mvecModel.toString(), as.getMvecModel());
				session.setAttribute(CONTROLLER_Statments.session.mvStatistik.toString(), as.getStatistik());
				response.sendRedirect(CONTROLLER_Statments.redirect.login_success.toString());
			} else {
				response.sendRedirect(CONTROLLER_Statments.redirect.error.toString());
			}
		}
		else if(site.equals(CONTROLLER_Statments.caller.Insert_Einkauf.toString()))
		{
			as.SQLModifizieren(((MODEL_Einkauf)session.getAttribute(CONTROLLER_Statments.session.Insert_Einkauf.toString())).SQlinsert());
			
		}
		else if(site.equals(CONTROLLER_Statments.caller.Update_Einkauf.toString()))
		{
			as.SQLModifizieren(((MODEL_Einkauf)session.getAttribute(CONTROLLER_Statments.session.Update_Einkauf.toString())).SQlupdate());
		}
		else if(site.equals(CONTROLLER_Statments.caller.Delete_Einkauf.toString()))
		{
			MODEL_Einkauf tmp = new MODEL_Einkauf();
			tmp.setMintID((int)session.getAttribute(CONTROLLER_Statments.session.Delete_Einkauf.toString()));
			as.SQLModifizieren(tmp.SQldelete());
		}
		
		
		
		
		else if(site.equals(CONTROLLER_Statments.caller.Insert_Konto.toString()))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute(CONTROLLER_Statments.session.Insert_Konto.toString())).SQLinsert(as.getAes()));
		}
		else if(site.equals(CONTROLLER_Statments.caller.Update_Konto.toString()))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute(CONTROLLER_Statments.session.Update_Konto.toString())).SQLUpdate(as.getAes()));
		}
		else if(site.equals(CONTROLLER_Statments.caller.Delete_Konto.toString()))
		{
			as.SQLModifizieren(((MODEL_Konto)session.getAttribute(CONTROLLER_Statments.session.Delete_Konto.toString())).SQLdelete());
		
			MODEL_Konto tmp = new MODEL_Konto();
			tmp.setMintID((int)session.getAttribute(CONTROLLER_Statments.session.Delete_Konto.toString()));
			as.SQLModifizieren(tmp.SQLdelete());
		
		
		}
		
		
		
		else if(site.equals(CONTROLLER_Statments.caller.Insert_Markt.toString()))
		{
			as.SQLModifizieren(((MODEL_Markt)session.getAttribute(CONTROLLER_Statments.session.Insert_Markt.toString())).SQLinsert());
			as.auslesen();
		}
		else if(site.equals(CONTROLLER_Statments.caller.Update_Markt.toString()))
		{
			as.SQLModifizieren(((MODEL_Markt)session.getAttribute(CONTROLLER_Statments.session.Update_Markt.toString())).SQLupdate());
			as.auslesen();
			
		}
		else if(site.equals(CONTROLLER_Statments.caller.Delete_Markt.toString()))
		{
			MODEL_Markt tmp = new MODEL_Markt();
			tmp.setMintID((int)session.getAttribute(CONTROLLER_Statments.session.Delete_Markt.toString()));
			as.SQLModifizieren(tmp.SQLdelete());
			as.auslesen();
		
		}
		
		
		
		else if(site.equals(CONTROLLER_Statments.caller.Insert_Produkt.toString()))
		{
			as.SQLModifizieren(((MODEL_Produkt)session.getAttribute(CONTROLLER_Statments.session.Insert_Produkt.toString())).SQLinsert());
		}
		else if(site.equals(CONTROLLER_Statments.caller.Update_Produkt.toString()))
		{
			as.SQLModifizieren(((MODEL_Produkt)session.getAttribute(CONTROLLER_Statments.session.Update_Produkt.toString())).SQLupdate());
		}
		else if(site.equals(CONTROLLER_Statments.caller.Delete_Produkt.toString()))
		{
			MODEL_Produkt tmp = new MODEL_Produkt();
			tmp.setMintID((int)session.getAttribute(CONTROLLER_Statments.session.Delete_Produkt.toString()));
			as.SQLModifizieren(tmp.SQLdelete());
		}
		
		
		else if(site.equals(CONTROLLER_Statments.caller.usermanagement.toString()))
		{
			String[] tmp = (String[])session.getAttribute(CONTROLLER_Statments.session.usermanagement_data.toString());
					
			ArrayList<String> user =         	CONTROLLER_Statments.createUser(tmp[0], tmp[1], tmp[2]);
			ArrayList<String> datenbank =     	CONTROLLER_Statments.createDatenbank(tmp[2]);
        	
        
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
