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
if(id!=null){
%>
<script>
	alert("로그인 하셨습니다");
	location.href = "sessionLoginOK.jsp";
</script>
<%} %>

<table border="1" width="75%"  cellspacing="0" style="margin-top:200px;" cellpadding="2" align="center" bgColor="#996666">
          <form action="sessionLoginProc.jsp" method="post" >
            <tr align="center" bgColor="#996666">
              <td colspan="3">
                <font color="#ffffff"><b>회원가입 by 김유민</b></font>
              </td>
            </tr>
            <tr>
              <td width="100">아이디</td>
              <td width="200"><input name="id" size = "15" required /></td>
              <td width="300">아이디를 적어주세요</td>
            </tr>
            <tr>
              <td>패스워드</td>
              <td ><input name="pwd" size = "15" required /></td>
              <td>패스워드를 적어주세요</td>
            </tr>
       <tr>
              <td colspan="3" align="center">
                  <input type="submit" value="회원가입" />
                  <input type="reset" value="다시쓰기" />
              </td>
              </tr>
          </form>
        </table>
    </tr>
    </td>
  </table>

</body>
</html>