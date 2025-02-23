package member.controller;

import java.util.ArrayList;
import java.util.Scanner;

import main.Main;
import member.service.MemberService;
import member.vo.MemberVo;

public class MemberController {

	private final Scanner sc;
	private final MemberService ms;
	
	public MemberController() {
		sc = new Scanner(System.in);
		ms = new MemberService();
	}

	public void selectMenu() {
		
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 회원목록 조회");
		System.out.println("4. 회원 탈퇴");
		System.out.println("5. 비밀번호 변경 ");
		
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : printMemberList(); break;
		case "4" : quit(); break;
		case "5" : editPwd(); break;
		default : System.out.println("그런 메뉴 업슴");
		}
	}

	private void join() {

		System.out.println("---회원가입---");
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
		
		System.out.println("---로그인---");
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
				Main.loginMember = dbVo;
				System.out.println("로그인 성공");
				System.out.println(dbVo.getNick() + "님 안녕하세요");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("로그인 실패");
			e.printStackTrace();
		}
		
		
		
	}

	private void printMemberList() {
		
		System.out.println("---멤버 리스트---");
		try {
			ArrayList<MemberVo> voList = ms.getMemberList();
			
			if(voList.isEmpty()) {
				System.out.println("회원 없음ㅠㅠ");
				return;
			}
			
			for(MemberVo vo : voList) {
				System.out.println(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	private void quit() {
		System.out.println("---회원 탈퇴---");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			int result = ms.quit(vo);
			
			if(result == 1) {
				System.out.println("회원탈퇴 성공");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원탈퇴 실패");
		}
		
		
	}

	private void editPwd() {
		System.out.println("---비밀번호 변경---");
		
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		System.out.print("변경할 비밀번호 : ");
		String newPwd = sc.nextLine();
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(pwd);
		
		try {
			int result = ms.editPwd(vo, newPwd);
			
			if(result == 1) {
				System.out.println("비밀번호 변경 성공");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("변경 실패");
			e.printStackTrace();
		}
		
	}

}
