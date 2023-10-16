package com.kh.app.main;

import com.kh.app.board.controller.BoardController;
import com.kh.app.member.controller.MemberController;

public class Main {

	public static void main(String[] args) {

		MemberController mc = new  MemberController();
		BoardController bc = new BoardController();
			while(true) {
			mc.selectMenu();
//			bc.boardMain();
			}
	}
}
