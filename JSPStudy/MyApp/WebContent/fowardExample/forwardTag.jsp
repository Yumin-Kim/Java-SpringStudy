<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    	String name = "JSP Study";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Forward Tag 예제 1_JSP1</h1>
Forward Tag 의 포워딩 되기전 페이지 입니다!!
<jsp:forward page="forwardTag_1.jsp">
	<jsp:param name="name" value="<%=name %>"/>
</jsp:forward>
</body>
</html>