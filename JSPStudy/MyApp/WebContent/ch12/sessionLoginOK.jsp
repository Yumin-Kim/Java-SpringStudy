<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgColor="#996600">
<%
String id = (String)session.getAttribute("idkey");
if(id == null){
%>

<script>
	alert("로그인 되지않앗습니다");
	location.href = "sessionLogin.jsp";
</script>
<%} %>

<table border="1" cellspacing="0" cellpadding="2" align="center" bgColor="#996666" style="margin-top:200px; padding:100px;">
         
            <tr align="center" bgColor="#996600">
              <td colspan="3">
                <font color="#ffffff"><b>회원가입 by 김유민</b></font>
              </td>
            </tr>
            <tr>
              <td width="200"><%=id %>님 로그인 되었습니다</td>
              <td>
              	<a href="sessionLogout.jsp">Logout</a>
              </td>
            </tr>

 
  </table>

</body>

</html>