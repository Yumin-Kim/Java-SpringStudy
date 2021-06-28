<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.*"
	session="true"
	buffer="16kb"
	trimDirectiveWhitespaces="false"
	isThreadSafe="true"
	
%>
    <%--
    	JSP 지시자 >> page include taglib
    	page 지시자 속성
    	info 속성을 사용시 개발 페이지에 부가적인 정보 설정 가능!! 
    	import는 import 하는것인데여러번 사용 가능!!
    	extends , session(http 세션 여부) , buffer(버퍼 크기 설정 기본값은 8KB >> codesplitig 같은 느낌) ,
		autoFlash , isThreadSafe 동시에 여러 페이지를 보여주는것? 처리시간 여부 확인??
		trimDirectiveWhiteSpace 문자 공백 가능!! >> page 속성을 지워 버림 '
		errorPage 와 isErrorPage 속성이 있는데 같이 사용함
		errorPage="이동할 페이지" >> 에러 발생시 다른 페이지에서 에러 처리하는 속성이며
		에러 담당하는 페이지에 isErrorPage="true"사용 해줘야 한다!!
		pageEncoding
		
		include 지시자
		여러가지 페이지를 하나로 뭉쳐서 만드는 지시자!!
		<%@ include file="top.jsp" %> 이런식으로 사용!!
		엄청 신기!!
		
		액션태그 6가지 있음!!
		include , forward , plug-in , getProperty , setProperty , useBean 이 있다!!
		<jsp:____ > 이런식으로 사용!!
		
		include 액션 태그!!
		다른페이지를 현재 페이지에 포함시킴
		include 속성과 다르게 포함 시킬 처리 결과를 포함!!
		<jsp:include page="로컬 URL" flush ="true"/> // include 지시자(페이지 자체를 가지고 옴!!)와는 다르게 선언된다!! 
		
		<% request.setCharacterEncoding("utf-8"); %> form이용하여 input값을 보내줄때 tag를 불러 와주는 곳에 사용 되어야함!!
		html input 에서 name으로 값을 넘기고 넘어 가는 jsp가 존재시 html input name은 전역변수 취급 jsp에서 name 선언시 지역변수(스크립릿)으로 영향을 주지 못한다!!
		 
		forward 액션 태그
		현재 페이지에서 다른 페이지로 이동할때 사용!
		모든 내용을 버리고 지정된 다른 페이지로 이동
		forward param 속성을 이용하여 html에서 작성하지 않은 값을 넘길수 있다!!
		엄청 동적이다!!
		
		plug-in 액션태그
		자바 플로그인을 사용하여 자바 애플릿을 jsp페이지에서 실행할 수 있게
		
		useBean 액션태그
		
		스크립트 요소 대체 사용
		<jsp:declaration>__</jsp:declaration> >> <%! %>
		<jsp:scriptlet>__</jsp:scriptlet> >> <% %>
		<jsp:expresstion>__</jsp:expresstion> >> <%= %>
		<jsp:directive.page.contentType = "text/html; charset:UTF-8"/> >> <%@page contentType="" %>
		
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 0427</title>
</head>
<body>
	<%-- import "java.util" 해서 Date객체 사용 가능!! --%>
	<%
		Date date = new Date();
	%>
	현재 날짜와 시간은<%=date.toLocaleString() %>
</body>
</html>