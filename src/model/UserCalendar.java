package model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCalendar {
	//�J�����_�[�̔N
	private int year;
	//�J�����_�[�̌�
	private int month;
	//���t���d�z��ŕێ�����
	private String[][] data;
	//���ɂ����̓��e���X�g��ێ�����}�b�v
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
