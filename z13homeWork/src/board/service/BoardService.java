package board.service;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.BoardDao;
import board.vo.BoardVo;
import db.JDBCTemplate;

public class BoardService {

	private final BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}

	public ArrayList<BoardVo> printPost() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<BoardVo> voList = dao.printPost(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
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

	public BoardVo showPost(String selectNo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		BoardVo vo = dao.showPost(selectNo, conn);

		JDBCTemplate.close(conn);
		
		return vo;
	}

	public int deletePost(String selectPost) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.deletePost(selectPost, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
