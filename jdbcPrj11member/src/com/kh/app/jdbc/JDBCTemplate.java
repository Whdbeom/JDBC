package com.kh.app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// connection
	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String id = "C##KH_JDBC";
		String pwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	// close (conn)
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
		}
	}
	
	// close (stmt)
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
		}
	}
	
	// close (rs)
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
		}
	}
	
	// commit
	public static void commit(Connection conn) {
			try {
				if(conn != null) {
					conn.commit();
				}
			} catch (SQLException e) {
		}
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
	
}
