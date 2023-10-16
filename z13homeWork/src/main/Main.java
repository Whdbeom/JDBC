package main;

import board.controller.BoardController;
import member.controller.MemberController;
import member.vo.MemberVo;

public class Main {
	
	public static MemberVo loginMember;

	public static void main(String[] args) {
		
		MemberController mc = new MemberController();
		BoardController bc = new BoardController();
		
			mc.selectMenu();
			System.out.println(loginMember);
			bc.boardMain();
		
	}

}
