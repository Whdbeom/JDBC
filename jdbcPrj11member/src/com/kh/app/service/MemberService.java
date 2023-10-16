package com.kh.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.kh.app.dao.MemberDao;
import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.vo.MemberVo;

public class MemberService {

	//필드  == 멤버변수
	private final MemberDao dao;
	
	// 기본 생성자
	public MemberService() {
		dao = new MemberDao();
	}

	public int join(MemberVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		// Dao 호출 (sql)
		int result = dao.join(conn, vo);

		// tx
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	
		// close
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 호출
		MemberVo dbVo = dao.login(conn, vo);
		
		// tx
		
		// close
		JDBCTemplate.close(conn);
		
		return dbVo;
	}

	public ArrayList<MemberVo> getMemberList() throws Exception {
		// conn
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		ArrayList<MemberVo> voList = dao.getMemberList(conn);
		
		// tx
		
		// close
		JDBCTemplate.close(conn);
		
		return voList;
	}
	
}
