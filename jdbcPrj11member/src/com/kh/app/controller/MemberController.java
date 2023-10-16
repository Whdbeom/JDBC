package com.kh.app.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.dao.MemberDao;
import com.kh.app.service.MemberService;
import com.kh.app.vo.MemberVo;

public class MemberController {

	// 멤버변수 == 필드
	private Scanner sc;
	private final MemberService ms;
	
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}

	// 메뉴 선택
	public void selectMenu() {
		System.out.println("메뉴선택");
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.전체회원조회");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		default : System.out.println("잘못입력");
		}
		
	}
	
	
	// 회원가입
	private void join() {
		System.out.println("----회원가입-----");
		// 데이터 준비
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			int result = ms.join(vo);
			
			// 결과 처리
			if(result == 1) {
				System.out.println("회원가입 성공");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("회원가입 실패...");
			e.printStackTrace();
		} 
		
	}

	// 로그인
	private void login() {
		System.out.println("-----로그인-----");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		// 서비스 호출
		try {
			MemberVo dbVo = ms.login(vo);
			// result ( tx, rs )
			if(dbVo != null) {
				System.out.println("로그인 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("로그인 실패..");
			e.printStackTrace();
		}
		

	}

	// 전체회원조회
	private void printMemberList() {
		System.out.println("-----전체회원조회-----");
		
		// 데이터 준비
		
		try {
			// 서비스 호출
			ArrayList<MemberVo> voList = ms.getMemberList();
			
			// 결과 처리
			for(MemberVo vo : voList) {
				System.out.println(vo.getId() +" / "+ vo.getPwd());
			}
			
		} catch (Exception e) {
			System.out.println("회원 목록 조회 실패...");
			e.printStackTrace();
		}
		
	}
	

	
}
