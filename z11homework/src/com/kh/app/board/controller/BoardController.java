package com.kh.app.board.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.app.board.service.BoardService;
import com.kh.app.board.vo.BoardVo;
import com.kh.app.member.vo.MemberVo;

public class BoardController {

	private final Scanner sc;
	private final BoardService bs;
	
	public BoardController() {
		sc = new Scanner(System.in);
		bs = new BoardService();
	}
	
	public void boardMain(MemberVo dbVo) {
		System.out.println();
		System.out.println("[게시글 목록]");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-30s%-60s%-90s%-120s\n", "번호", "제목", "작성자", "날짜");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		
		
		try {
			ArrayList<BoardVo> voList = bs.printPost();
			for(BoardVo vo : voList) {
				System.out.printf("%-30s%-60s%-90s%-120s\n", vo.getNo(), vo.getTitle(), vo.getWriter(), vo.getDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		selectMenu(dbVo);
	}
	
	public void selectMenu(MemberVo dbVo) {
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("<메뉴 선택> 1. 게시글 작성  |  2. 게시글 조회  | 3. 게시글 삭제 ");
		
	
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : writePost(dbVo); break;
		case "2" : findPost(); break;
		case "3" : deletePost(dbVo); break;
		}
	}

	private void writePost(MemberVo dbVo) {
		System.out.println("-- 게시글 작성 --");
		System.out.print("제목 : ");
		String title = sc.nextLine();
		System.out.print("내용 : ");
		String content = sc.nextLine();
		
		BoardVo vo = new BoardVo();
		vo.setWriter(dbVo.getNick());
		vo.setTitle(title);
		vo.setContent(content);
		
		try {
			int result = bs.writePost(vo);
			
			if(result == 1) {
				System.out.println("게시글을 작성했습니다.");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println(" 게시글 작성 실패 ");
			e.printStackTrace();
		}
		
		
	}

	private void findPost() {
		System.out.println("[게시글 조회]");
		System.out.print("게시글 번호 : ");
		String postNum = sc.nextLine();
		
		try {
			
			ArrayList<BoardVo> voList = bs.findPost(postNum);
			
			if(voList != null) {
				for(BoardVo dbVo : voList) {
					System.out.println("-------------------------------------------------");
					System.out.println("제목 : " + dbVo.getTitle());
					System.out.println("작성자 : " + dbVo.getWriter() + "      작성일 : " + dbVo.getDate());
					System.out.println("-------------------------------------------------");
					System.out.println(dbVo.getContent());
					System.out.println("-------------------------------------------------");
				}
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("검색된 게시글이 없습니다");
			e.printStackTrace();
		}
		
	}


	private void deletePost(MemberVo dbVo) {
		System.out.println("[게시글 삭제]");
		System.out.print("삭제 할 게시글 번호 : ");
		String sel = sc.nextLine();
		
		try {
			int result = bs.deletePost(sel, dbVo);
			
			if(result == 1) {
				System.out.println("게시글이 삭제되었습니다.");
			} else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			System.out.println("게시글을 삭제 할 수 없습니다.");
			e.printStackTrace();
		}
	}
	
	
}
