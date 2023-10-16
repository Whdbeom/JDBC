package com.kh.mini.member.controller;

import java.util.Scanner;

import com.kh.mini.main.Main;
import com.kh.mini.member.serivce.MemberService;
import com.kh.mini.member.vo.MemberVo;

public class MemberController {

	private final MemberService ms;
	
	public MemberController() {
		ms = new MemberService();
	}
	
	public void selectMenu() {
		System.out.println("---메뉴 선택");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		String select = Main.SC.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		default : System.out.println("그런거없음");
		}
		
	}
	
	// 회원 가입
	public void join() {

		try {
			System.out.println("-----회원가입-----");
			
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			System.out.print("닉네임 : ");
			String nick = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			vo.setNick(nick);

			int result = ms.join(vo);
			if(result != 1) {
				throw new Exception();
			}
			System.out.println("회원가입 성공!");
		
		} catch (Exception e) {
			System.out.println("회원 가입 실패");
		}

		
	}
	/**
	 * 로그인
	 * 
	 * 아이디 , 비번 입력받기
	 * DB에서 1개 행 조회
	 * 
	 * SELECT * FROM MEMBER
	 * WHERE ID = 아이디 AND PWD = 패스워드
	 */
	public void login() {
		try {
			System.out.println("-----로그인-----");
			
			System.out.print("아이디 : ");
			String id = Main.SC.nextLine();
			System.out.print("비밀번호 : ");
			String pwd = Main.SC.nextLine();
			
			MemberVo vo = new MemberVo();
			vo.setId(id);
			vo.setPwd(pwd);
			
			MemberVo dbVo = ms.login(vo);
			if(dbVo != null) {
				Main.loginUser = dbVo;
				System.out.println("로그인 성공");
				System.out.println(dbVo.getNick() + "님 안녕하셍ㅊ요");
			} else {
				throw new Exception();
			}
				
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}
	// 회원 탈퇴
	public void quit() {
		System.out.println("-----회원 탈퇴-----");
		
		
	}
	
	// 마이페이지 (현재 로그인 한 본인 정보 조회)
	public void myPage() {
		System.out.println("-----마이페이지-----");
		System.out.println("아이디 : " + Main.loginUser.getId() );
		System.out.println("닉네임 : " + Main.loginUser.getNick());
		System.out.println("가입일 : " + Main.loginUser.getEnrollDate());
		
		
		
		
	}
	// 비밀번호 수정
	// 닉네임 수정
	
	// 전체 회원 조회 (관리자)
	// 회원 조회 - 번호
	// 회원 조회 - 아이디
	// 회원 조회 - 닉네임
	// 회원 강제탈퇴
	
	
}
