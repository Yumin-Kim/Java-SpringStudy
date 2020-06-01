<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.sql.*"
    %>
<%@page import="java.util.*,ch11.*" %>

<%
	Class.forName("org.gjt.mm.mysql.Driver");
	Connection con = null;
	Statement state = null;
	ResultSet rs = null;
	
	String pwd = "" , userId = "" , address = "" , num1 ="" , num2 = "";
	int counter = 0;
	state = con.createStatement();// Create Statemtnt
	rs = state.executeQuery("SELECT * from tdregister"); //
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	if(rs != null){
		while(rs.next()){
			userId =  rs.getString("userId");
			pwd =  rs.getString("pwd");
			num1 =  rs.getString("num1");
			num2 =  rs.getString("num2");
			address =  rs.getString("address");
%>
<tr>
	<td>UserID : <%=userId%></td>
	<td>PassWord : <%=pwd%></td>
	<td>first Social Security Number: <%=num1%></td>
	<td>Second Social Security Number:<%=num2%></td>
	<td>Address : <%=address%></td>
<%
counter++;
	}
}
%>
</tr>
<div>
<%=counter %>
</div>
</body>
</html>