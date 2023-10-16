package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String userPwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, userName, userPwd);
		conn.setAutoCommit(false);
		return conn;
	}
	
	// connection close
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			
		}
	}

	// statement close
	// preparedStatement close
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (Exception e) {
			
		}
	}
	
	// ResultSet close
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			
		}
	}
	
	// commit 
	public static void commit(Connection conn) {
		try {
			if(conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			
		}
	}
	
	// rollback
	public static void rollback(Connection conn) {
		try {
			if(conn != null) {
				conn.rollback();
			}
		} catch (Exception e) {
			
		}
	}
	
}
