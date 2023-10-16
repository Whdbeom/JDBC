package com.kh.app.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.app.board.vo.BoardVo;
import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class BoardDao {

	public int writePost(BoardVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO BOARD(B_NO, B_WRITER, B_TITLE, B_CONTENT, B_DATE) VALUES (B_NO.NEXTVAL, ?, ?, ?, SYSDATE)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getWriter());
		ps.setString(2, vo.getTitle());
		ps.setString(3, vo.getContent());
		
		int result = ps.executeUpdate();
		
		JDBCTemplate.close(ps);
		
		return result;
	}

	public ArrayList<BoardVo> findPost(String postNum, Connection conn) throws Exception {
		
		String sql = "SELECT * FROM BOARD WHERE B_NO = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, postNum);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<BoardVo> voList = null;
		
		while(rs.next()) {
			 voList = new ArrayList<BoardVo>();
			String no = rs.getString("B_NO");
			String writer = rs.getString("B_WRITER");
			String title = rs.getString("B_TITLE");
			String content = rs.getString("B_CONTENT");
			String date = rs.getString("B_DATE");
			
			BoardVo dbVo = new BoardVo();
			dbVo.setContent(content);
			dbVo.setTitle(title);
			dbVo.setWriter(writer);
			dbVo.setDate(date);
			dbVo.setNo(no);
			
			voList.add(dbVo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(ps);
		
		return voList;
	}

	public ArrayList<BoardVo> printPost(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM BOARD";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
	
		while(rs.next()) {
//			voList = new ArrayList<BoardVo>();
			String no = rs.getString("B_NO");
			String writer = rs.getString("B_WRITER");
			String title = rs.getString("B_TITLE");
			String content = rs.getString("B_CONTENT");
			String date = rs.getString("B_DATE");
			
			BoardVo vo = new BoardVo(no, writer, title, content, date);
			
			voList.add(vo);
		}

		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return voList;
	}

	public int deletePost(String sel, MemberVo dbVo, Connection conn) throws Exception {
		
		String sql = "DELETE FROM BOARD WHERE B_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, sel);
//		pstmt.setString(2, dbVo.getId());
//		pstmt.setString(3, dbVo.getPwd());
		
		int result = pstmt.executeUpdate();

		JDBCTemplate.close(pstmt);
		
		return result;
	}

}
