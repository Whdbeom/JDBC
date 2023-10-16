package com.kh.app.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.service.MemberService;
import com.kh.app.vo.MemberVo;

public class MemberController {


	private Scanner sc;
	private final MemberService ms;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}

	public void selectMenu() {
		System.out.println("=====메뉴 선택=====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 모든 회원 조회");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		default : System.out.println("잘못입력");
	
		}
		
		
	}

	private void join() {
		System.out.println("-----회원가입-----");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			int result = ms.join(vo);
			if(result == 1) {
				System.out.println("회원가입 성공!");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("회원가입 실패,,");
			e.printStackTrace();
		}
		
		
	}

	private void login() {
		System.out.println("-----로그인-----");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			MemberVo dbVo = ms.login(vo);
			if(dbVo != null) {
				System.out.println("로그인 성공!");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("로그인 실패,,");
			e.printStackTrace();
		}
		
	}

	private void printMemberList() {
		System.out.println("-----모든 회원 보기-----");
		
		try {
			ArrayList<MemberVo> voList = ms.getMemberList();
			for(MemberVo vo : voList) {
				System.out.println(vo.getId() + " / " + vo.getPwd());
			}
		} catch (Exception e) {
			System.out.println("회원 조회 실패");
			e.printStackTrace();
		}
		
		
	}

	
}
