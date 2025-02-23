package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCTemplate;
import member.vo.MemberVo;
import oracle.jdbc.proxy.annotation.Pre;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK) VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();

		MemberVo dbVo = null;
		if(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbQuitYn = rs.getString("QUIT_YN");
			
			dbVo = new MemberVo();
			dbVo.setNo(dbNo);
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
			dbVo.setNick(dbNick);
			dbVo.setEnrollDate(dbEnrollDate);
			dbVo.setQuitYn(dbQuitYn);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return dbVo;
	}

	public ArrayList<MemberVo> getMemberList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM MEMBER";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		
		ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
		while(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbId = rs.getString("ID");
			String dbPwd = rs.getString("PWD");
			String dbNick = rs.getString("NICK");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbQuitYn = rs.getString("QUIT_YN");
			String dbQuitDate = rs.getString("QUIT_DATE");
			
			MemberVo dbVo = new MemberVo();
			dbVo.setNo(dbNo);
			dbVo.setId(dbId);
			dbVo.setPwd(dbPwd);
			dbVo.setNick(dbNick);
			dbVo.setEnrollDate(dbEnrollDate);
			dbVo.setQuitYn(dbQuitYn);
			dbVo.setQuitDate(dbQuitDate);
			
			voList.add(dbVo);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return voList;
	}

	public int quit(Connection conn, MemberVo vo) throws SQLException {
		
		String sql = "UPDATE MEMBER SET QUIT_YN = ?, QUIT_DATE = SYSDATE WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "Y");
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int editPwd(Connection conn, MemberVo vo, String newPwd) throws Exception {
		
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, newPwd);
		pstmt.setString(2, vo.getId());
		pstmt.setString(3, vo.getPwd());
		
		int result = pstmt.executeUpdate();
	
		JDBCTemplate.close(pstmt);
		
		return result;
	}



}
