<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="init.Manage"%>

<%
	request.setCharacterEncoding("UTF-8");

	Manage manage = Manage.getInstance();

	String json = request.getParameter("json");

	String result = manage.method(json);

	out.print(result);
%>
<!DOCTYPE html>
<html lang="eu">
    <title>JSP Page</title>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    </head>
    <body>
    <h1>게시판 토이 프로젝트</h1>
        <h2>Member Page</h2>
         <h3>Create Member</h3>
              <form action="<%= request.getContextPath() %>/member/create.do" method="POST">
                <p>
                  <label> 이름</label>
                  <input type="text" placeholder="이름" name="name" required="required" />
                </p>
                <p>
                  <label> 도시</label>
                  <input type="text" placeholder="도시" name="city" />
                </p>
                <p>
                  <label> 우편 번호</label>
                  <input type="text" placeholder="우편 번호" name="cityCode" />
                </p>
                <p>
                  <label> 자세한 도시 정보</label>
                  <input type="text" placeholder="자세한 도시 정보" name="detailCity" />
                </p>
                <p>
                  <label> 나이</label>
                  <input type="number" placeholder="나이" name="age" />
                </p>
                <p>
                  <label> 이메일</label>
                  <input type="text" placeholder="이메일" name="email" />
                </p>
                <p>
                  <input type="submit" />
                </p>
              </form>
    </body>
</html>