package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
	//conn 얻기
	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		conn.setAutoCommit(false);
		return conn;
	}
	
	//commit
	public static void commit() {
		// TODO Auto-generated method stub
		
	}
	
	
	// rollback
	public static void rollback(Connection conn) {
			try {
				if(conn != null) {
					conn.rollback();
				}
			} catch (SQLException e) {
			}
	}


	public static void close(PreparedStatement ps) {
		try {
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		} catch (SQLException e) {
		}
	}


	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
		}
	}

	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
		}
		
	}

}
