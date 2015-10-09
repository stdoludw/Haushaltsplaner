<%@page import="jsp.Model_Markt"%>
<%@page import="jsp.access"%>
<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<header>
<h2>Alles um deine Maerkte</h2>
     
</header>


<table bgcolor="#E0F2F7" border="1" cellspacing="1" cellpadding="1">
<tr> 
<th> M_ID</th> 
<th> Marktname</th> 
<th> Postleitzahl </th> 
<th> Adresse</th> 
<th> Entfernung</th> 
</tr> 

<%

//access bean=(access)request.getAttribute("bean");  
//Vector<Object> tmp = bean.getMvecModel();
Vector<Object> tmp = access.getMvecModel();

for (int i = 0;i<tmp.size();i++)
{
	
	
	if (tmp.get(i) instanceof Model_Markt) {
	%>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMintID());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrName());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrPLZ());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMstrAdr());
	%>
	</td>
	<td>
	<%
	out.print(((Model_Markt)tmp.get(i)).getMintEntfernung());
	%>
	</td>
	<%
	}
	
	%>
	</tr>
	<%
	
}

%>
</table>
<form action="Change_Markt.jsp" method="POST">
Markt ID Eingeben: <input type="text" name="m_ID">
<input class="w3-btn w3-color-teal" type="submit" value="Bearbeiten">
</form>

<form action="Add_Markt.jsp" method="POST">
<input class="w3-btn w3-color-teal" type="submit" value="Hinzufuegen">
</form>
</html>