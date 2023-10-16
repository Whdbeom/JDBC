package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			String select = sc.nextLine();
			
	
			switch(select) {
			case "1" : Join.join(); break;
			case "2" : Login.login(); break;
			default : System.out.println("잘못입력");
			}
		}
	}




}
