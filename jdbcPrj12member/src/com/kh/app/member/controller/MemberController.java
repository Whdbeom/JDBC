package com.kh.app.member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.member.service.MemberService;
import com.kh.app.member.vo.MemberVo;

public class MemberController {

	private final Scanner sc;
	private final MemberService ms;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}

	public void showMenu() {
		
		System.out.println("-----메뉴선택-----");
		System.out.println("1. 회원 가입");
		System.out.println("2. 로그인");
		System.out.println("3. 모든 회원 조회");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. 비밀번호 변경");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		case "4" : quit(); break;
		case "5" : editPwd(); break;
		default : System.out.println("잘못선택");
		}
		
	}

	private void join() {
		System.out.println("-----회원 가입-----");
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
		System.out.println("-----로그인-----");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		MemberVo dbVo;
		try {
			dbVo = ms.login(vo);
			if(dbVo != null) {
				System.out.println("로그인 성공 / " + dbVo.getNick() + " 님 ㅎㅇ");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
	}

	private void printMemberList() {
		System.out.println("-----모든 회원 조회-----");
		
		try {
			ArrayList<MemberVo> voList = ms.getMemberList();
			
			for(MemberVo vo : voList) {
				System.out.println(vo.getId() + " / " + vo.getPwd() + " / " + vo.getNick());
			}
		} catch (Exception e) {
			System.out.println("회원조회실패");
			e.printStackTrace();
		}
	}
	
	private void quit() {
		System.out.println("----회원 탈퇴-----");
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			MemberVo voNick = ms.login(vo);
			String nick = voNick.getNick();
			
			int result = ms.quit(vo);
			
			if(result == 1 ) {
				System.out.println(nick + "님 ㅂㅂ");
				System.out.println("회원탈퇴 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("탈퇴실패");
			e.printStackTrace();
		}
		
	}

	private void editPwd() {
		System.out.println("-----비밀번호 변경-----");
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
				
				System.out.println(dbVo.getNick() + "님 ㅎㅇ");
				System.out.print("변경할 비밀번호 : ");
				String newPwd = sc.nextLine();
				dbVo.setPwd(newPwd);
//				System.out.println(vo.getPwd() + vo.getId());
				int result = ms.editPwd(dbVo);
				
				if(result == 1) {
					System.out.println("비밀번호 변경 완료");
				} else {
					throw new Exception();		
				}
				
			} else {
				System.out.println("아이디 또는 패스워드가 일치하지 않음");
			}
			
		} catch (Exception e) {
			System.out.println("비밀번호 변경 실패");
			e.printStackTrace();
		}
		
	
	}
	
}
