<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Example</title>
</head>
<body>
<h1>Script Example</h1>
<h2>선언문 JSP < %! % > 빈칸을 제거하고 사용한다</h2>
<h2>멤버 변수라고 부름 </h2>
<h3>선언문에서 선언된 변수는 JSP페이지가 서블릿 코드로 변환이 되면서 서블릿 클래스의 멤버 변수로 변환 됨</h3>
<h3>로직이 시작되기전에 호이스팅으로 첨으로 인식한다!! </h3>

<%! String declaration = "Delclaration!! JSP에서 출력된 값입니다**************"; %>
<%! public String decMethod(){
	return declaration;	
}%>
<p>
<%
	String scriptlet = "scriptlet";
	String comment = "Comment";
	out.println("내장 객체를 이용한 출력:"+declaration + "</p>");
%>
<ul>
	<li>선언문의 출력 1!!! : <%=declaration %></li>
	<li>선언문의 출력 2 : <%=decMethod() %></li>
	<li>scriptLet의 출력: <%=scriptlet%></li>
</ul>
<%!
	String team = "Method 선언 < %! % >";
	
%>
<%!
	String name = team + "Fighting";
	public String getName(){
		return team;
	}
%>
<%
String declaration = "Hekki";
%>
<%=name %></br>
<%=getName()%>

<h1>실습 2 </h1>
<%!
	int one, two =1;
	public int plusMethod(){
		return one + two;
	}
	String msg;
	int three;
%>
<p>
one 과 two의 합은 ?<%=plusMethod() %>
</p>
<p>String msg의 값은 ? <%=msg %></p>
<h2>스크립트릿이란 JSP페이지가 서블릿으로 변환되고 요청 될때 마다 _jspService(Tomcat기준으로 ) 메소드 안에 선언되는 요소</h2>
<h2>스크립트릿은 선언문과 달리 선언된 변수는 지역변수로 선언이되고 메소드 선언으 할수 없음 >> < % %> 빈칸을 제거후에 저 방식으로 선언 된다</h2>
	
<%!
	int one1;
	String msgOne;
%>

<%
	int two = 31;
	String msgTwo = "Scriptlet Example";	
%>
<%=two + ":" + msgTwo %></br>
서블릿 코드로 변형된 파일 위치~>><%=application.getRealPath("/") %>
<%
	float f = 2.3f;
	int i = Math.round(f);
	java.util.Date date = new java.util.Date(); 
%>

<p>
	실수 f의 반올림 값은 <%=i %></br>
	현재의 날짜와 시간은 <%=date.toString() %></br>
	변수 출력시 < %= %>사용  스크립트 릿은 out객체를 사용하여 브라우저에 출력 가능
</p>

<%
	String name[] = {"Java" , "JSP","Android","Struts"};
%>
<h2>Express pug , ejs 와 비슷한  Templete Engine과 같이 문법이 이루어진것 같다!!</h2>
<ul>
<%for(int j = 0  ; j < name.length ; j ++) {%>
<li><%=i %> : <%=name[j] %></li>
<%}%>
</ul>

<%
	java.util.Date date1 = new java.util.Date();
	int hour = date1.getHours();
	int one1 = 10;
	int two1 = 12;
%>
<%!
	public int operatrion(int i , int j){
		return i > j ? j : j;
}
%>
<p>
	지금은 오전일까요 오후일까요 >> <%=(hour > 12) ? "오후" : "오전"%> </br>
	지역 변수인 one1 과 two1중 누가 크나여?? operation함수는 선언문 식으로 작성!!<%=operatrion(one1,two1)/*이건 주석 입니다!!*/ %>
</p>
<%-- jsp 주석 <!--HTML 주석 --> --%>
<script>console.log("Hello")</script>
<form method="post" action="jspExample_sub_0420.jsp">
	이름 :<input name="name" /></br>
	좋아하는 색깔은 : 
	<select name="color" >
		<option value="blue" selected >파란색</option>
		<option value="red" >붉은색</option>
		<option value="orange" >오렌지색</option>
		<option value="etc" >기타</option>
	</select>
	<input type="submit" value="보내기"  >
</form>
</body>
</html>