<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">


  <title>Hello World!</title>
</head>

<body bgColor="#996600">
  <table width="500" align="center" cellspacing="0" cellpadding="5">
    <tr>
      <td align="center" valign="middle" bgColor="#FFFFCC">
        <table border="1" cellspacing="0" cellpadding="2" align="center">
          <form action="201610309Proc.jsp" name="regForm" method="post">
            <tr align="center" bgColor="#996600">
              <td colspan="3">
                <font color="#ffffff"><b>회원가입 by 김유민</b></font>
              </td>
            </tr>
            <tr>
              <td width="100">아이디</td>
              <td width="200"><input name="id" size = "15" /></td>
              <td width="300">아이디를 적어주세요</td>
            </tr>
            <tr>
              <td>패스워드</td>
              <td ><input type="password" name="pwd" size = "15" /></td>
              <td>패스워드를 적어주세요</td>
            </tr>
            <tr>
              <td>패스워드확인</td>
              <td ><input type="password" name="pwd" size = "15" /></td>
              <td>패스워드를 확인합니다</td>
            </tr>
            <tr>
              <td>생년월일</td>
              <td ><input name="birthday" size = "20" /></td>
              <td>생년월일을 적어주세요</td>
            </tr>
            <tr>
              <td>이름</td>
              <td ><input name="name" size = "15" /></td>
              <td>이름을 적어주세요</td>
            </tr>
            <tr>
              <td>이메일</td>
              <td ><input  name="email" size = "27" /></td>
              <td>이메일를 적어주세요</td>
            </tr>
            <tr>
              <td>연락처</td>
              <td ><input name="phonenumber" size = "20" /></td>
              <td>연락처를 적어주세요</td>
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