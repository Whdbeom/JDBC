package com.kh.mini.member.serivce;

import java.sql.Connection;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.member.dao.MemberDao;
import com.kh.mini.member.vo.MemberVo;

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

}
