<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8"); 
    	String name = request.getParameter("studentname");
    	String number= request.getParameter("number");
    	String gender = request.getParameter("gender");
    	String major = request.getParameter("major");
    	
   
    	String prtocol = request.getProtocol();
    	String sercerName = request.getServerName();
    	int serverPort = request.getServerPort();
    	String remoteAddr = request.getRemoteAddr();
    	String remoteHost = request.getRemoteHost();
    	String method = request.getMethod();
    	String requesturi = request.getRequestURI();
    	StringBuffer requesturl = request.getRequestURL();
    	String useBrowser = request.getHeader("User-Agent");
    	String filType = request.getHeader("Accept");
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request _ 02 JSP</title>
</head>
<body>
	<h1>Request Example</h1>
	섬영  : <%=name %><br>
	num  : <%=number%><br>
	major  : <%=major%><br>
	gender  : <%=gender %><br>
	<%=prtocol %>>>>>
	<%=sercerName %>>>>>
	<%=serverPort %>>>>>
	<%=remoteAddr %>>>>>
	<%=remoteHost %>>>>>
	<%=method %>>>>>
	requasdesturi::<%=requesturi %>>>>>
	requesasdasdturl::<%=requesturl %>>>>>
	<%=useBrowser %>>>>>
	<%=filType %>>>>>
	
</body>
</html>