package com.kh.app.board.service;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.util.ArrayList;

import com.kh.app.board.dao.BoardDao;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class BoardService {

	private final BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}

	public int writePost(BoardVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.writePost(vo, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<BoardVo> findPost(String postNum) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<BoardVo> voList = dao.findPost(postNum, conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	public ArrayList<BoardVo> printPost() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<BoardVo> voList = dao.printPost(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	public int deletePost(String sel, MemberVo dbVo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.deletePost(sel, dbVo, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


}
