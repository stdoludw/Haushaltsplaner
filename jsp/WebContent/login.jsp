<%@page import="jsp.Controll_Main"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><h1>Login erfolgreich</h1></head>

<%
Controll_Main ctm = new Controll_Main();
ctm.acces();
ctm.auslesen();

%>

<p> <a href="/jsp/View_Produkt.jsp">View_Produkt</a></p>
<p> <a href="/jsp/View_Markt.jsp">View_Markt</a></p>
<p> <a href="/jsp/View_Konto.jsp">View_Konto</a></p>
<p> <a href="/jsp/View_Einkauf.jsp">View_Einkauf</a></p>
 <p> <a href="jsp/View_Statistik.jsp">View_Statisik</a></p>
 
 </html>
