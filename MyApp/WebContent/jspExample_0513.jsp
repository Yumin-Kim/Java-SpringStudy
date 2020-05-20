<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	 서블릿 주요 클래스
	 HttpServlet : 서블릿을 만들기 위해 반드시 상속 해야 할 필수 클래스
	 HttpServletRqeust : 클라이언트가 데이터를 입력하거나 또는 정보에 대한 요청 값으르 가지고 있는 클래스
	 HttpServletResponse : 클라이언트가 요청한 정보를 처리하고 다시 응답하기 위한 정보를 담고 있는 클래스
	 HttpSession : 클라이언트가 세션의 정보를 저장하고 세션 기능을 유지 하기 위해서 제공되는 클래스 
	 
	 HttpServlet : doGet(req,res)get방식 doPost(req,res)post방식
	 
	  서블릿 라이플 사이클
	  클라이언트 서블릿 요청 >> init(최초 한번 호출) >> service doGet doPost(반복저으로 실행) >> destory(마지막 한번 호출)
	  init : 서블릿이 메모리에 로드 될때 실행
	  destroy : 언로드 되기전에 수행되는 콜백 
	  service메소드는 HTTP method 타입에 따라 doGet doPost를 호출한다
	  
	  servlet 과 jsp 차이
	  servlet은 .java 확장자 명을 가지며 java코드가 주가 됩니다
	  그 코드안에 HTML을 작성할 수 있는 클래스를 사용하여 HTML을 작성합니다
	  java코드 속에 html들이 섞여 있게 되어 코드 가독성이 떠럴진다
	    클라이언트의 요청에 대해 동적으로 작동하는 웹 어플리케이션 컴포넌트
	  
	  jsp는  .jsp의 확장자를 가지며 html안에 java코드를 삽입하는 형식이다
	  servlet보다 복잡성이 줄어들게된다 .
	  Servlet를 보완하고 기술을 확장한 스크립트 방식 표준이라고 한다
	  
	  
 --%>
</body>
</html>