package com.kh.mini.main;

import java.util.Scanner;

import com.kh.jdbc.JDBCTemplate;
import com.kh.mini.board.controller.BoardController;
import com.kh.mini.member.controller.MemberController;
import com.kh.mini.member.vo.MemberVo;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginUser = null;
	
	public static void main(String[] args) {

		System.out.println("---미니프로젝트---");
		
		// 객체
		MemberController mc = new MemberController();
		BoardController bc =new BoardController();
		
		while(true) {
			// 메뉴판
			System.out.println("1.멤버");
			System.out.println("2.게시판");
			System.out.println("3.나갈래");
			
			// 메뉴 선택
			String num = Main.SC.nextLine();
			switch(num) {
			case "1" : mc.selectMenu(); break;
			case "2" : bc.selectMenu(); break;
			case "3" : return;
			default : System.out.println("없음");
			}
		}
	}

}
