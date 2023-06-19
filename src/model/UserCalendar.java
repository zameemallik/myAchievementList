package model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCalendar {
	//カレンダーの年
	private int year;
	//カレンダーの月
	private int month;
	//日付を二重配列で保持する
	private String[][] data;
	//日にち事の投稿リストを保持するマップ
	private Map<String, List<Tweet>> tweetDate = new HashMap<>();

	//setter & getter
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = data;
	}
	public Map<String, List<Tweet>> getTweetDate() {
		return tweetDate;
	}
	public void setTweetDate(Map<String, List<Tweet>> tweetDate) {
		this.tweetDate = tweetDate;
	}
}
