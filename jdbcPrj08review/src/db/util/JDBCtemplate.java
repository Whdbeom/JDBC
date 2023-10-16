package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCtemplate {

	public static Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "C##KH_JDBC";
		String userPwd = "1234";		
		Connection conn = DriverManager.getConnection(url, userName, userPwd);
		conn.setAutoCommit(false);
		
		return conn;
	}
	
}
