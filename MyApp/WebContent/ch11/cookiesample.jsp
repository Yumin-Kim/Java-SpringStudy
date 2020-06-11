<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션사용예제</title>
</head>
<body>
<%
	String id = "apple";
String pwd = "1234";
session.setAttribute("idkey",id);
session.setAttribute("pwdKey",pwd);
Cookie info = new Cookie("test","Cookie");
response.addCookie(info);
%>

세션이 생성됩니다</br>
<a href="viewSessionInfo.jsp">세션 정보를 확인하는 페이지로 이동</a>

</body>
</html>