package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Tweet;

//�o�^�ς݃A�J�E���g���̃f�[�^�x�[�X
public class TweetDAO {
	//�f�[�^�x�[�X�ڑ��Ɏg�p������
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myAchievementList";
	private final String DB_USER = "";
	private final String DB_PASS = "";

	public List<Tweet> findAll(String address){
		List<Tweet> tweetList = new ArrayList<>();

		//�f�[�^�x�[�X�ڑ�
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL���̏���
			String sql = "SELECT * FROM TWEET WHERE ADDRESS=? ORDER BY ID DESC";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//���݃��O�C�����̃A�h���X������
			pStmt.setString(1, address);

			//SQL���s
			ResultSet rs = pStmt.executeQuery();

			//���ʂ�ArrayList�ɕۑ�
			while(rs.next()) {
				Date date = rs.getDate("THEDAY");
				//date��calendar�ɕϊ�
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				String catagories = rs.getString("CATAGORIES");
				String detail = rs.getString("DETAIL");
				Tweet user = new Tweet(address, cal, catagories, detail);
				tweetList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tweetList;
	}

	public boolean postTweet(Tweet tweet) {
		//�f�[�^�x�[�X�ɐڑ�
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL���̏���
			String sql = "INSERT INTO TWEET(ADDRESS, CATAGORIES, DETAIL) VALUES (?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//�^����ꂽ����SQL���ɓ���
			pStmt.setString(1, tweet.getAddress());
			pStmt.setString(2, tweet.getCatagories());
			pStmt.setString(3, tweet.getDetail());

			//SQL���s
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
