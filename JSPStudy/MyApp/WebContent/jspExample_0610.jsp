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

	JDBC 데이터 베이스르 다루기 위한 자바(API(Application Programming Interface))
	JDBC를 통해서 DBMS의 종류와 관계없이 질의문을 던져서 데이터를 수신
	각 데이터베이스의 접속에 대한 상세한 정보가 불필요
	
	JDBC Interface : 프로그래머에게 쉬운 데이터 베시으솨 연동 되는 프로그램을 작성할수 있게 하는 도구
	JDBC Driver : JDBC Interface를 구현하여 실제로 DBMS르 작동시켜서 질의 던지고 결과를 받음
	
	자바 프로그램  << >> JDBC(전체적인 코드)  << >> DBMS (Oracle MySQL)
	JSP,server		JDBC interface >> JDBC Drive(이거만 사용할줄 알면됨)
	
	JDBC API (java.sql 패키지)
	Driver : 모든 드라이버 클래스들이 구현 해야 하는 인터페이스
	DriverManager : 드라이버 를 로드하고 데이터베이스에 연결
	Connection : 특정 데이터 베이스와의 연결
	Statement : SQL문을 실행해 작성된 결과를 반환 
	preparedStatement : 사전에 컴파일 된 SQL문을 실행
	ResultSet : SQL문에에 대한 결과를 얻어냄
	
	JDBC Programming Step
	
	1.Create JDBC Driver Interface 
		Class.forName("Driver_name") "Driver_name" >> "org.gjt.mm.mysql.Driver"
	2.Create Connection to the DBMS through the JDBC Driver Interface
		Connection con = DriverManager.getConnection("DateBase_URL","Accout_ID(root)","Account_pwd(123123123)")
	3.Create Statement
		Statement = con.createStatement();
	4.Receive Result by executed SQLquerySetence or ResultSet
		ResultSet rs = statement.excuteQuery("SELCT * from tabelName");
	5.Close Resultset
		rs.close();
	6.Close Statement
		statement.close();
	7.Close connection Database		
		connection.close();
			
	
			
		
 --%>
</body>
</html>