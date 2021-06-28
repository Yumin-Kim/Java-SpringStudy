<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	request.setCharacterEncoding("utf-8"); 
    	String name = "Hello";
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>include Tag Example 1_Jsp</h1>
	<jsp:include page="includeTagTop.jsp" /><br>
	include Action Tag 의 Body입니다!!<br>
</body>
</html>