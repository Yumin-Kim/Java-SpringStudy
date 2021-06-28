<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp Example Sub Page</title>
</head>
<body>
<h1>if - else Example JSP</h1>

<%! String msg; %>

<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String color = request.getParameter("color");
	
	if(color.equals("blue")){
		msg = "Blue Selected";
	}else if(color.equals("red")){
		msg = "Red Selected";
	}else if(color.equals("orange")){
		msg = "Orange Selected";
	}else{
		color = "black";
		msg = "Not Found";
	}
	
%>
<%
	String name1 = "Hello1Scriptlet";
%>

<%!
	String name1 = "Hello";
%>





<%=name1 %>
<p>당신의 이름은? <%=name %></p>

<p style="color:<%=color %>; font-size:20px "  > 당신이 선택한 컬러는????  <%=msg %> </p>

<%
	int i = 2;
	int j = 1;
	while( i < 10 ){%>
	<h2> <%=i%>단 </h2>
	<ul>	
	<%while( j < 10 ){%>
			<li> <%=i%> X <%=j%> = <%=j*i%> </li>			
			<%j++;
		}%>
		</ul>
		<%i++;
		j = 1;
}%>






</body>
</html>