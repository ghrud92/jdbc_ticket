package com.estsoft.ticket.vo;

public class ReserveVO {
	private Long no;
	private String phone;
	private int count;
	private Long movie_no;
	private String movie_name;
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(Long movie_no) {
		this.movie_no = movie_no;
	}
	@Override
	public String toString() {
		return movie_name + " : " + count;
	}
}
