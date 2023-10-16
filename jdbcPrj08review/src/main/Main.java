package main;

import member.MemberController;

public class Main {

	public static void main(String[] args) {

		System.out.println("회원가입, 로그인");
		
		MemberController mc;
		try {
			mc = new MemberController();
			mc.selectMenu();
		} catch (Exception e) {
			e.printStackTrace();
		}


		
	}
	

}
