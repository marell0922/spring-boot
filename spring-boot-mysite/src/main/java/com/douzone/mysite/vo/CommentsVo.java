package com.douzone.mysite.vo;

public class CommentsVo {
	private long no;
	private String content;
	private String write_date;
	private long user_no;
	private String user_name;
	private long board_no;
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWrite_date() {
		return write_date;
	}
	public void setWrite_date(String write_date) {
		this.write_date = write_date;
	}
	public long getUser_no() {
		return user_no;
	}
	public void setUser_no(long user_no) {
		this.user_no = user_no;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public long getBoard_no() {
		return board_no;
	}
	public void setBoard_no(long board_no) {
		this.board_no = board_no;
	}
	@Override
	public String toString() {
		return "CommentsVo [no=" + no + ", content=" + content + ", write_date=" + write_date + ", user_no=" + user_no
				+ ", user_name=" + user_name + ", board_no=" + board_no + "]";
	}
	
	
}
