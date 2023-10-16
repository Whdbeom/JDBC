package com.kh.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.vo.MemberVo;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "INSERT INTO MEMBER(ID, PWD) VALUES(?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());

		int result = ps.executeUpdate();
		JDBCTemplate.close(ps);
		
		return result;
	}

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {

		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());
		
		ResultSet rs = ps.executeQuery();
		
		MemberVo dbVo = null;
		if(rs.next()) {
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			dbVo = new MemberVo();
			dbVo.setId(id);
			dbVo.setPwd(pwd);
		}
		JDBCTemplate.close(rs);
		JDBCTemplate.close(ps);
		
		return dbVo;
	}

	public ArrayList<MemberVo> getMemberList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM MEMBER";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
		
		while(rs.next()) {
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(ps);
		
		return voList;
	}

}
