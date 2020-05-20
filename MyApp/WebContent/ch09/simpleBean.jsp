<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean 
	id="test" 
	class="ch09.SimpleBean" 
	scope ="page"
	/>    
<jsp:setProperty 
	property="message" 
	name="test"
	value="빈을 쉽게 정복하자"
	/>		
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>간단한 빈 프로그래밍</h1>
<p> Message : <jsp:getProperty property="message" name="test" /> </p>

</body>
</html>