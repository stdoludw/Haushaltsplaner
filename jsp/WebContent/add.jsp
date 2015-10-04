<%@page import="jsp.Controll_Main"%>
<%@page import="com.sun.corba.se.spi.ior.MakeImmutable"%>
<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.Model_Konto"%>
<%@page import="jsp.Model_Produkt"%>
<%@page import="jsp.Model_Einkauf"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<%



String auswahl = request.getParameter("file");

switch(auswahl)
{
case "produkt": 
{
	String produkt= (request.getParameter("produkt"));
	if(produkt.equals("i_produkt_add"))
	{
		String produktname = request.getParameter("i_produkt_name");
		String produktgewicht = request.getParameter("i_produkt_gewicht");
		String produktpreis = request.getParameter("i_produkt_preis");
		String produktid = request.getParameter("i_produkt_id");
		
		Model_Produkt mod = new Model_Produkt(produktname,Integer.valueOf(produktgewicht),Float.valueOf(produktpreis),Integer.valueOf(produktid));
		
	}
	else if(produkt.equals("i_produkt_change"))
	{
		//update
	}
}
	break;
case "konto": 
{
	String konto= (request.getParameter("konto"));
	if(konto.equals("i_konto_add"))
	{
		String Kontoinhaber = request.getParameter("i_konto_name");
		String Kontonummer = request.getParameter("i_konto_kontonummer");
		String kontoblz = request.getParameter("i_konto_bankleitzahl");
		String kontobetrag = request.getParameter("i_konto_betrag");
		String kontominimum = request.getParameter("i_konto_minimum");
		String kontoid = request.getParameter("i_konto_id");

				
		Model_Konto kon = new Model_Konto(Kontoinhaber, kontoblz, Kontonummer, kontobetrag, kontominimum, Integer.valueOf(kontoid));
	}
	else if(konto.equals("i_konto_change"))
	{
		
	}
}
	break;
case "markt": 
{
	String markt= (request.getParameter("markt"));
	if(markt.equals("i_markt_add"))
	{
		String marktname = request.getParameter("i_markt_name");
		String marktadresse = request.getParameter( "i_markt_adresse" );
		String marktplz = request.getParameter("i_marktpostleitzahl" );
		String marktentfernung = request.getParameter("i_marktentfernung");
		String marktid = request.getParameter("i_markt_ID");
		
		Model_Markt mar = new Model_Markt(marktname,marktplz,marktadresse, Integer.valueOf(marktentfernung), Integer.valueOf(marktid));
		
	}
	else if(markt.equals("i_markt_change"))
	{
		
	}
}
	break;
case "einkauf": 
{
	String einkauf= (request.getParameter("einkauf"));
	if(einkauf.equals("i_einkauf_add"))
	{
		String einkaufanzahl = request.getParameter("i_einkauf_anzahl");
		String einkaufdatum = request.getParameter("i_einkauf_datum");
		String einkaufid = request.getParameter("i_einkauf_id");
		
		String kontoname = request.getParameter("i_konto_cmb");
		String marktname = request.getParameter("i_markt_cmb");
		String produktname = request.getParameter("i_produkt_cmb");
		
		Model_Einkauf ein = new Model_Einkauf(Integer.valueOf(einkaufanzahl), einkaufdatum,Integer.valueOf(einkaufid));
		Model_Konto k = null; Model_Produkt p= null; Model_Markt m= null;
		
		for(int i=0;i< Controll_Main.mvecModel.size();i++)
		{
			
			 if (Controll_Main.mvecModel.get(i) instanceof Model_Markt)
			{
				 if (((Model_Markt)Controll_Main.mvecModel.get(i)).getMstrName().equals(marktname))
				 {
					 m = ((Model_Markt)Controll_Main.mvecModel.get(i));
				 }
			}
			else if (Controll_Main.mvecModel.get(i) instanceof Model_Produkt)
			{
				 if(((Model_Produkt)Controll_Main.mvecModel.get(i)).getMstrName().equals(produktname))
				 {
					 p = ((Model_Produkt)Controll_Main.mvecModel.get(i));
				 }

			}
			else if (Controll_Main.mvecModel.get(i) instanceof Model_Konto)
			{
				 if(((Model_Konto)Controll_Main.mvecModel.get(i)).getMstrName().equals(kontoname))
				 {
					 k = ((Model_Konto)Controll_Main.mvecModel.get(i));
				 }

			}
		}

		ein.ModelArray(k, p, m);

	
	}
}
	break;

}

%>





</body>
</html>