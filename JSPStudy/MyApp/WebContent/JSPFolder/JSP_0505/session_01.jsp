<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    session="true"
    pageEncoding="UTF-8"%>
    <%-- session="true" 가장 중요!!session사용시 필수!!--%>
    <%
    	request.setCharacterEncoding("UTF-8");
    	String id = request.getParameter("id");
    	String pwd = request.getParameter("password"); 
    	session.setAttribute("idKey",id); //id 값을 세션으로 만듬
    	session.setMaxInactiveInterval(60*5); // expire , age 처럼 지속 시간 만들어줌
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Session_JSP</h1>
<form method="post" action="session_02.jsp" >
	<h2>1.가장 제일 좋아하는 계절은?</h2>
	<input type="radio" name="season" value="봄" />봄
	<input type="radio" name="season" value="여름" />여름
	<input type="radio" name="season" value="가을" />가을
	<input type="radio" name="season" value="겨울" />겨울
	
	<h2>2.가장 제일 좋아하는 음식은?</h2>
	<input type="radio" name="fruit" value="watermalon" />watermalon
	<input type="radio" name="fruit" value="melon" />melon
	<input type="radio" name="fruit" value="apple" />apple
	<input type="radio" name="fruit" value="orange" />orange
	
	<input type="submit" value="go result" />
</form>
</body>
</html>