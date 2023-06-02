package model;

import java.io.Serializable;
import java.sql.Date;

public class Tweet implements Serializable {
	private String address;
	private Date date;
	private String title;
	private String detail;

	public Tweet() {}

	public Tweet(String address, String title, String detail) {
		this.address = address;
		this.title = title;
		this.detail = detail;
	}

	public Tweet(String address, Date date, String title, String detail) {
		this.address = address;
		this.date = date;
		this.title = title;
		this.detail = detail;
	}
	public String getAddress() {
		return address;
	}
	public Date getDate() {
		return date;
	}
	public String getTitle() {
		return title;
	}
	public String getDetail() {
		return detail;
	}

}
