<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import = "java.util.*,ch11.*"%>
<jsp:useBean id = "regMgr" class = "ch11.RegisterMgr" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connection JSP & DataBase </title>
</head>
<body bgColor="#c9c9c9" >
<h2>Using Bean to connection database</h2>
<h3>Student Information</h3>
<table border="777777"  border="1">
	<tr>
		<td><strong>ID</strong></td>
		<td><strong>Password</strong></td>
		<td><strong>Name</strong></td>
		<td><strong>Number</strong></td>
		<td><strong>PhoneNumber</strong></td>
		<td><strong>Comments</strong></td>
	</tr>
	
	<%
		Vector<RegisterBean> vList = regMgr.getRegisterList();
		int counter = vList.size();
		for(int i = 0 ; i < counter ; i++){
			RegisterBean regBean = vList.get(i);
	%>
<tr>
	<td><%=regBean.getId() %></td>
	<td><%=regBean.getPassword() %></td>
	<td><%=regBean.getName() %></td>
	<td><%=regBean.getNum() %></td>
	<td><%=regBean.getPhone() %></td>
	<td><%=regBean.getInfo() %></td>
	<%} %>
</tr>	
</table>

</br>
총 학생 수는 : <%=counter %>

</body>
</html>