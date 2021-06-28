<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%
	String serverInfo = application.getServerInfo();
	String mimeType = application.getMimeType("request.html");
	String realPath = application.getRealPath("/");
	application.log("application 내부 객체 로그 테스트");
	log("This is another log message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Application example</title>
</head>
<body>
<h1>Application Example</h1>
serveletContainer name and version : <%=serverInfo %></p>
request1.html of MIME Type : <%=mimeType %></p>
local file system path : <%=realPath %> </p>
</body>
</html>