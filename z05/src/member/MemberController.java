package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.JDBCTemplate;

public class MemberController {
	// 필드(멤버변수)
	private Scanner sc;
	
	// 기본생성자
	public MemberController() {
		sc = new Scanner(System.in);
	}
	
	
	// 메뉴 선택
	public void selectMenu() {
		System.out.println("=======메뉴 선택=======");
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.유저 보기");
		String select = sc.nextLine();
		
		switch(select) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : showUser(); break;
		default : System.out.println("잘못입력"); break;
		}
	}

	// 회원가입
	private void join() {
		System.out.println("-----회원 가입-----");
		// 데이터 입력받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			// conn 얻기
			conn = JDBCTemplate.getConnection();

			// 쿼리 실행.
			String sql = "INSERT INTO MEMBER(ID,PWD) VALUES(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			int result = ps.executeUpdate();
			
			// 결과처리
			if(result == 1) {
				JDBCTemplate.commit();
				System.out.println("회원가입 성공!");
			}
		} catch (Exception e) {
			JDBCTemplate.rollback(conn);
			System.out.println("회원가입 실패..");
			e.printStackTrace();
		} finally {
			// 자원반납
			JDBCTemplate.close(ps);
			JDBCTemplate.close(conn);
		}
	}
	
	// 로그인
	
	private void login() {
		System.out.println("-----로그인-----");
		
		// 데이터 입력받기
		System.out.print("아이디 : ");
		String id = sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = sc.nextLine();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// conn 얻기
			conn = JDBCTemplate.getConnection();
			
			// 쿼리 실행
			String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			
			// 결과처리
			if(rs.next()) {
				System.out.println("로그인 성공!");
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("로그인 실패..");
			e.printStackTrace();
		} finally {
			// 자원반납
			JDBCTemplate.close(conn);
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
	}
	
	// 전체 회원 목록 조회
	private void showUser() {
		System.out.println("-----전체 회원 목록 조회-----");
		
		// 데이터 입력받기
		// 전부 다 조회하면 되니까 입력받을 건 없음 ,,,
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// conn 얻기
			conn = JDBCTemplate.getConnection();
			
			// 쿼리 실행
			String sql = "SELECT * FROM MEMBER";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			// 결과처리
			while(rs.next()) {
				//rs 의 모든 행을 읽어오기
				String dbId = rs.getString("ID");
				String dbPwd = rs.getString("PWD");
				System.out.println(dbId + " / " + dbPwd);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			JDBCTemplate.close(conn);
			JDBCTemplate.close(ps);
			JDBCTemplate.close(rs);
		}
		
				
	}

}
