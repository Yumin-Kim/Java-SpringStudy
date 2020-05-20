<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%--
		내부 객체 
		JSP페이지를 작성 할때 특별한 기능을 제공하는 JSP컨테이너가 제공하는 특별한 객체
		JSP에서 선언하지 않고 사용 할 수 있는 객체
		스크립트 요소에서 내부 객체와 동일한 변수명으로 선언할 수 없다
		
		사용되는 번주 4 가지
		JSP페이지 입출련 내부 객체
		JSP패이지 외부 환경 정보 제공
		JSP페이지 서블릿 관련 내부 객체
		JSP 페이지 예외 관련 기본객체
		
		request javax.servelet.http.HttpServeletRequest >> 파라미터 포함한 요청을 담고 있는 객체
		response out session page config application 
		pageContext >> 해당 페이지 실행에 필요한 Context정보를 담고 있는 객체 
		exception java.Exception 안에
		request session application pageContext >> 공통메소드 가짐 >>setAttribute , getAttributeName , getAttribute ,  removeAttribute
		request 내부객체는 브라우저에서 jsp페이지로 전달되는 테이터의 묶음으로 HTTP헤더와 바디 로 구성!!
		
		out 내부 객체는 JSP페이지의 결과를 클라이언트에 전송해주는 출력 스트림을 나타내며 JSP페이지가 클라 에게 보내는 모든 정보는 out객체를 통해서 전달
		
		session 세션관리 웹서버가 요청한 클라 가 다른 크라와 다른것을 구별 session(서버가 정보 가짐) cookie(클라가 가짐) 방법있음
		클라 요청에 대한 context 정보의 세션과 관련된 정보를 저장하고 관리하는 객체
		
		page지시자의 session속성이 true로 되어 있어야함
						
		application 내부 객체는 서블릿 또는 어플리케이션 외부 환경 정보(context)를 나타내는 객체,서버의 정보와 자원 그리고 이벤트 로그 같은 정보
		
		pageContext 현재 JSP페이지의 context를 나타내며 pageContext 객체를 통해서 다른 내부객체에 접근함 >> forward , include사용!!
		
		page 내부객체는 jsp페이지 그자체를 나타내는 객체 this사용 잘 사용안함	 
		
		config 내부객체는 javax.servlet.ServeletConfig 클래스 타입 Servlet이 초기화 될때 참조 해야함 
				데이터 공유하는 객체 태그를 사용하여 web.xml에 대하여 저장해놓고 getInitParamter()사용하여 jsp,servler에서  데이터 공유!!
		
		exception
		
 --%>
<head>
<meta charset="UTF-8">
<title>jsp Example 05_05</title>
</head>
<body>
<h1>Request Example_01</h1>

</body>
</html>