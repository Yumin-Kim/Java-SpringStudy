<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Error JSP</h1>
다음과 같이 에러가 발생 했습니다!!
<p>
	<%=exception.getMessage() %>
</p>

</body>
</html>