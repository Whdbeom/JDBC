package main;

import member.MemberController;

public class Main {

	public static void main(String[] args) {

		System.out.println("회원관리 프로그램");
		
		MemberController mc = new MemberController();
		while(true) {
			mc.menu();
		}
		
	}

}
