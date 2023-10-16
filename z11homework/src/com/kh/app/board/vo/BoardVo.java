package com.kh.app.board.vo;

import java.sql.Date;

public class BoardVo {

	private String no;
	private String writer;
	private String title;
	private String content;
	private String date;
	
	
	
	
	
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardVo(String no, String writer, String title, String content, String date) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
	
}
