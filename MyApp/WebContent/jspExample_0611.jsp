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
	ConnectionPool
	데이터 메모리 내에 있는 데이터베이스 와 연결을 해놓은 캐시들
	데이터 베이스 연결들은 데이터 에 대한 요청이 발생하면 재사용됨
	데이터 베이스 의 수행 능력을 향상 위해 사용
	
	웹 프로그램은 실질적으로 데이터 베이스에 연결하고 질의를 던지고 경과를 받아오는 부분에서 많은 시간을 소요
	이러한 과정이 서버에 많은 부하르 준다
	ConnectionPool을 사용해서 보다 효율적으로 데이터 베이스에 연동
	
	Pooling 기법
	효율족으로 목수의 사용자에게 서비스하기 위해 미리 데이터 배이스 연결을 위한 객체들을 생성
	Connectuin 객체의 재사용
	데이터 베이스 연결 객체를 매번 생성 사용 해체하기 않고 처음 만들어둔 데이터 베이스 연결 객체를 계속 사용
	사용자에게 필요한 응답을 주는데 걸리는 시간을 단축하고 시스템 부하를 줄임
	
	
	 
 --%>
</body>
</html>