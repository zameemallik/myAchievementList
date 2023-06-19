package model;

import java.io.Serializable;
import java.util.Calendar;

public class Tweet implements Serializable {
	private String address;
	private int year;
	private int month;
	private int date;
	private String catagories;
	private String detail;

	public Tweet() {}

	public Tweet(String address, String catagories, String detail) {
		this.address = address;
		this.catagories = catagories;
		this.detail = detail;
	}

	public Tweet(String address, Calendar cal, String catagories, String detail) {
		this.address = address;
		this.catagories = catagories;
		this.detail = detail;
		this.year = cal.get(Calendar.YEAR);
		this.month = cal.get(Calendar.MONTH) + 1;
		this.date = cal.get(Calendar.DAY_OF_MONTH);
	}
	public String getAddress() {
		return address;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDate() {
		return this.date;
	}
	public String getCatagories() {
		return catagories;
	}
	public String getDetail() {
		return detail;
	}

}
