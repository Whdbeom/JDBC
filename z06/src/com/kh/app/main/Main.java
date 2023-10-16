package com.kh.app.main;

import com.kh.app.controller.MemberController;

public class Main {

	public static void main(String[] args) {

		MemberController mc = new MemberController();
		
		while(true) {
			mc.selectMenu();
		}
	}

}
