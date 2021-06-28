<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import ="java.util.*,ch11.*" %>

<jsp:useBean id = "regMgr" class="ch11.RegisterMgrPool"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bean과 커넥션 풀을 사용한 데이터베이스 연동예제</title>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {background-color: #f2f2f2;}
</style>
</head>
<body>

<h1>Bean과 커넥션 풀을 사용한 데이터베이스 연동예제</h1>

<table border="#0000ff"  border="1">
<tr>
		<td><strong>ID</strong></td>
		<td><strong>Password</strong></td>
		<td><strong>Name</strong></td>
		<td><strong>Number</strong></td>
		<td><strong>PhoneNumber</strong></td>
		<td><strong>Comments</strong></td>
	</tr>
<%
	Vector <RegisterBean> vlist = regMgr.getRegisterList();
int counter =vlist.size();
for(int i = 0; i < vlist.size() ; i++){
	RegisterBean regBean = vlist.get(i);
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
</body>
</html>