package com.kh.mini.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.vo.MemberVo;

public class MemberDao {

	public int join(MemberVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES (SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}


	public MemberVo login(MemberVo vo, Connection conn) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo dbVo = null;
		// rs -> vo
		if(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbModifyDate = rs.getString("MODIFY_DATE");
			
			dbVo = new MemberVo();
			dbVo.setNo(dbNo);
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
			dbVo.setNick(dbNick);
			dbVo.setEnrollDate(dbEnrollDate);
			dbVo.setModifyDate(dbModifyDate);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return dbVo;
	}

}
