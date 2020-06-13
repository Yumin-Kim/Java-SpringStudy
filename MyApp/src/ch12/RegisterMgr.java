package ch12;

import java.sql.*;

public class RegisterMgr {
	
	private DBConnectionMgr pool;
	public RegisterMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e) {
			System.out.println("Error 커넥션 연결 실패");
		}
	}
	public boolean loginRegister(String id ,String pwd) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultset = null;
		boolean loginConnection = false;
		
		try {
			connection = pool.getConnection();
			String query = "select count(*) from tbladdress where id=? and password=?";
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			resultset = pstmt.executeQuery();
			
			if(resultset.next()&& resultset.getInt(1) > 0) {
				loginConnection = true;
			}
			
		}catch(Exception ex) {
			System.out.println("Exception Error"+ex);
		}finally {
			pool.freeConnection(connection,pstmt,resultset);
		}
		return loginConnection;
		
	}

}
