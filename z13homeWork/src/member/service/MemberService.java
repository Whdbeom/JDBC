package member.service;

import java.sql.Connection;
import java.util.ArrayList;

import db.JDBCTemplate;
import member.dao.MemberDao;
import member.vo.MemberVo;

public class MemberService {

	private final MemberDao dao;
	
	public MemberService() {
		super();
		dao = new MemberDao();
	}

	public int join(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.join(conn, vo);
		
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
		
		MemberVo dbVo = dao.login(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return dbVo;
	}

	public ArrayList<MemberVo> getMemberList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<MemberVo> voList = dao.getMemberList(conn);
		
		JDBCTemplate.close(conn);
		
		return voList;
	}

	public int quit(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.quit(conn, vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int editPwd(MemberVo vo, String newPwd) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.editPwd(conn, vo, newPwd);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
