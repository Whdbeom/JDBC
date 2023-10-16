package com.kh.app.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;
import javax.xml.xpath.XPathEvaluationResult.XPathResultType;

import com.kh.app.jdbc.JDBCTemplate;
import com.kh.app.member.vo.MemberVo;

public class MemberDao {

	public int join(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "INSERT INTO MEMBER(ID, PWD, NICK) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());
		ps.setString(3, vo.getNick());
		
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
			String nick = rs.getString("NICK");
			
			dbVo = new MemberVo();
			
			dbVo.setId(id);
			dbVo.setPwd(pwd);
			dbVo.setNick(nick);
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
			
			MemberVo dbVo = new MemberVo();
			dbVo.setId(id);
			dbVo.setPwd(pwd);
			dbVo.setNick(nick);
			
			voList.add(dbVo);
		}
		
		JDBCTemplate.close(ps);
		JDBCTemplate.close(rs);
		
		return voList;
	}

	public int quit(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "DELETE FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement ps = conn.prepareStatement(sql); 
		ps.setString(1, vo.getId());
		ps.setString(2, vo.getPwd());
		
		int result = ps.executeUpdate();
		
		JDBCTemplate.close(ps);
		
		return result;
	}
	
	
	
	public int editPwd(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, vo.getPwd());
		ps.setString(2, vo.getId());

		int result = ps.executeUpdate();
		
		JDBCTemplate.close(ps);
		
		return result;
	}




}
