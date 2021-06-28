<%@ page language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("UTF-8");
    	String season = request.getParameter("season");
    	String fruit = request.getParameter("fruit"); 
    	String id = (String)session.getAttribute("idKey");
    	String sessionID = session.getId();
    	int intervalTime = session.getMaxInactiveInterval();
    	if(id!= null){
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Session_JSP_02</h1>
<%=id %>님이 좋아하시는 계절과 과일은 </p>
<%=season %> and <%=fruit %></p>
sessionID : <%=sessionID %><br>
Interval session Time :<%=intervalTime %></p> 
세션 종료 로직!!session.invalidate();
<%
	}
else{
	out.println("세션 시간이 경과 되었습니다!!");	
} %>

</body>
</html>