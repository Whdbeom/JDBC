package board.vo;

public class BoardVo {

	private String no;
	private String title;
	private String content;
	private String writer_no;
	private String enroll_date;
	private String del_Yn;
	private String edit_date;
	public BoardVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardVo(String no, String title, String content, String writer_no, String enroll_date, String del_Yn,
			String edit_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer_no = writer_no;
		this.enroll_date = enroll_date;
		this.del_Yn = del_Yn;
		this.edit_date = edit_date;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	public String getWriter_no() {
		return writer_no;
	}
	public void setWriter_no(String writer_no) {
		this.writer_no = writer_no;
	}
	public String getEnroll_date() {
		return enroll_date;
	}
	public void setEnroll_date(String enroll_date) {
		this.enroll_date = enroll_date;
	}
	public String getDel_Yn() {
		return del_Yn;
	}
	public void setDel_Yn(String del_Yn) {
		this.del_Yn = del_Yn;
	}
	public String getEdit_date() {
		return edit_date;
	}
	public void setEdit_date(String edit_date) {
		this.edit_date = edit_date;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", writer_no=" + writer_no
				+ ", enroll_date=" + enroll_date + ", del_Yn=" + del_Yn + ", edit_date=" + edit_date + "]";
	}
	
	
	
}
