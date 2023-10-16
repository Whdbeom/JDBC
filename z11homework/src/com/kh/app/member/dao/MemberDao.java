package com.kh.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	

	public int join(MemberVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());
		ps.setString(3, vo.getNick());
		
		int result = ps.executeUpdate();
		
		JDBCTemplate.close(ps);
		
		return result;
	}

	public MemberVo login(MemberVo vo, Connection conn) throws Exception {

		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());
		
		ResultSet rs = ps.executeQuery();
		MemberVo dbVo = null;

		if(rs.next()) {
			dbVo = new MemberVo();
			dbVo.setId(rs.getString("ID"));
			dbVo.setPwd(rs.getString("PWD"));
			dbVo.setNick(rs.getString("NICK"));
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
			String nick = rs.getString("NICK");
			
			MemberVo vo = new MemberVo(id, pwd, nick);
			
			voList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(ps);
		
		return voList;
	}



}
