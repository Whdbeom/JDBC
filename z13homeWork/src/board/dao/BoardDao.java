package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.vo.BoardVo;
import db.JDBCTemplate;
import main.Main;

public class BoardDao {

	public ArrayList<BoardVo> printPost(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM BOARD WHERE DEL_YN = 'N' ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
		
		BoardVo vo = null;
		
		while(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbTitle = rs.getString("TITLE");
			String dbContent = rs.getString("CONTENT");
			String dbWriterNo = rs.getString("WRITER_NO");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbDel_Yn = rs.getString("DEL_YN");
			String dbEditDate = rs.getString("EDIT_DATE");
			
			vo = new BoardVo();
			vo.setNo(dbNo);
			vo.setTitle(dbTitle);
			vo.setContent(dbContent);
			vo.setWriter_no(dbWriterNo);
			vo.setEnroll_date(dbEnrollDate);
			vo.setDel_Yn(dbDel_Yn);
			vo.setEdit_date(dbEditDate);
			
			voList.add(vo);
		}
		
		
		return voList;
	}

	public int writePost(BoardVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARDNO.NEXTVAL, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, Main.loginMember.getNo());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public BoardVo showPost(String selectNo, Connection conn) throws Exception {
		
		String sql = "SELECT * FROM BOARD WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, selectNo);
		ResultSet rs = pstmt.executeQuery();
		
		
		BoardVo vo = null;
		if(rs.next()) {
			String dbNo = rs.getString("NO");
			String dbTitle = rs.getString("TITLE");
			String dbContent = rs.getString("CONTENT");
			String dbWriterNo = rs.getString("WRITER_NO");
			String dbEnrollDate = rs.getString("ENROLL_DATE");
			String dbDel_Yn = rs.getString("DEL_YN");
			String dbEditDate = rs.getString("EDIT_DATE");
			
			vo = new BoardVo();
			vo.setNo(dbNo);
			vo.setTitle(dbTitle);
			vo.setContent(dbContent);
			vo.setWriter_no(dbWriterNo);
			vo.setEnroll_date(dbEnrollDate);
			vo.setDel_Yn(dbDel_Yn);
			vo.setEdit_date(dbEditDate);
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return vo;
	}

	public int deletePost(String selectPost, Connection conn) throws Exception {
		
		String sql = "UPDATE BOARD SET DEL_YN = 'Y'  WHERE NO = ? AND WRITER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		String loginUser = Main.loginMember.getNo();
		pstmt.setString(1, selectPost);
		pstmt.setString(2, loginUser);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}
	

}
