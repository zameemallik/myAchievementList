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

//登録済みアカウント情報のデータベース
public class TweetDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myAchievementList";
	private final String DB_USER = "";
	private final String DB_PASS = "";

	public List<Tweet> findAll(String address){
		List<Tweet> tweetList = new ArrayList<>();

		//データベース接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文の準備
			String sql = "SELECT * FROM TWEET WHERE ADDRESS=? ORDER BY ID DESC";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//現在ログイン中のアドレスを検索
			pStmt.setString(1, address);

			//SQL実行
			ResultSet rs = pStmt.executeQuery();

			//結果をArrayListに保存
			while(rs.next()) {
				Date date = rs.getDate("THEDAY");
				//dateをcalendarに変換
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
		//データベースに接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文の準備
			String sql = "INSERT INTO TWEET(ADDRESS, CATAGORIES, DETAIL) VALUES (?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//与えられた情報をSQL文に投入
			pStmt.setString(1, tweet.getAddress());
			pStmt.setString(2, tweet.getCatagories());
			pStmt.setString(3, tweet.getDetail());

			//SQL実行
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
