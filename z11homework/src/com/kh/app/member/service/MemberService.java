package com.kh.app.member.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.member.dao.MemberDao;
import com.kh.app.member.vo.MemberVo;

public class MemberService {

	private final MemberDao dao;

	public MemberService() {
		dao = new MemberDao();
	}

	public int join(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.join(vo, conn);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo login(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo dbVo = dao.login(vo, conn);
		
		JDBCTemplate.close(conn);
		
		return dbVo;
	}

	public ArrayList<MemberVo> getMemberList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<MemberVo> voList = dao.getMemberList(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

}
