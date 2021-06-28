<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="regBean" class="ch09.Bean201610309" ></jsp:useBean>
<jsp:setProperty property="*" name="regBean"/>
<!DOCTYPE html>
<html lang="en">
  <title>회원가입 확인</title>
</head>

<body bgColor="#996600">
  <table width="80%" align="center" cellspacing="0" cellpadding="5">
    <tr>
      <td align="center" valign="middle" bgColor="#FFFFCC">
        <table width="90%" border="1" cellspacing="0" cellpadding="2" align="center">
          <form action="201610309Insert.jsp" name="regForm" method="post">
            <tr align="center" bgColor="#996600">
              <td colspan="3">
                <font color="#ffffff"><b>회원가입 by 김유민</b></font>
              </td>
            </tr>
            <tr>
              <td>아이디</td>
              <td>
              	<jsp:getProperty property="id" name="regBean"/>
              </td>
            </tr>
            <tr>
              <td>이름</td>
              <td>
              	<jsp:getProperty property="name" name="regBean"/>
              </td>
            </tr>
            <tr>
              <td>이메일</td>
              <td>
              	<jsp:getProperty property="email" name="regBean"/>
              </td>
            </tr>
            <tr>
              <tr>
              <td>연락처</td>
              <td>
              	<jsp:getProperty property="phonenumber" name="regBean"/>
              </td>
            </tr>
            <tr>
              <td>생년월일</td>
              <td>
              	<jsp:getProperty property="birthday" name="regBean"/>
              </td>
            </tr>
            <tr>
              <td colspan="3" align="center">
                  <input type="submit" value="확인완료" />
                  <input type="button" value="다시쓰기" />
              </td>
            </tr>
          </form>
        </table>
    </tr>
    </td>
  </table>
</body>

</html>