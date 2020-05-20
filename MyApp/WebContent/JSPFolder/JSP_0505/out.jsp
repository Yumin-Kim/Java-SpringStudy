<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	int totalBuffer = out.getBufferSize();
	int remainBuffer = out.getRemaining();
	int useBuffer = totalBuffer - remainBuffer;
%>
<h2>buffer Size</h2>
print Buffer = <%=totalBuffer %>byte<br>
remain Buffer = <%=remainBuffer %>byte<br>
current Buffer = <%=useBuffer%>byte<br>
<%out.clearBuffer(); %>
print Buffer = <%=out.print(totalBuffer+"" )%>byte<br>
remain Buffer = <%=out.print(remainBuffer+"")%>byte<br>
current Buffer = <%=out.print(useBuffer+"")%>byte<br>

</body>
</html>