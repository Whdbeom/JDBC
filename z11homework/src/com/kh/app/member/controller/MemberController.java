package com.kh.app.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.board.controller.BoardController;
import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

public class MemberController {

	private final Scanner sc;
	private final MemberService ms;
	private final BoardController bc;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
		bc = new BoardController();
	}

	public void selectMenu() {
		
		System.out.println("--메뉴선택--");
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.모든 회원 보기");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		}
	}

	private void join() {
		System.out.println("--회원가입--");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String nick = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		vo.setNick(nick);
		
		try {
			int result = ms.join(vo);
			if(result == 1) {
				System.out.println("회원가입 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("회원가입 실패");
			e.printStackTrace();
		}
		
	}

	private void login() {
		System.out.println("--로그인--");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : "); 
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
			
		MemberVo dbVo = null;
		try {
			dbVo = ms.login(vo);
			
			if(dbVo != null) {
				System.out.println(dbVo.getNick() + "님 반갑습니다.");
				while(true) {
					bc.boardMain(dbVo);
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}

	private void printMemberList() {
		System.out.println("-- 모든 회원 조회 --");
		
		try {
			ArrayList<MemberVo> voList = ms.getMemberList();
			
			for(MemberVo vo : voList) {
				System.out.println(vo.getId() + " / " + vo.getPwd() + " / " + vo.getNick());
			}
		} catch (Exception e) {
			System.out.println("회원 조회 실패");
			e.printStackTrace();
		}
		
	}

}
