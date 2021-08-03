<%@ page import="tutorial.study.servlet_nolecture.basic.domain.Member" %>
<%@ page import="tutorial.study.servlet_nolecture.basic.domain.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //request, response 사용 가능
    MemberRepository memberRepository = MemberRepository.getInstance();

 String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = Member.builder()
                .age(age)
                .username(username)
                .build();
        Member save = memberRepository.save(member);
    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>