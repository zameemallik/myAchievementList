package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

//登録済みアカウント情報のデータベース
public class AccountDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myAchievementList";
	private final String DB_USER = "";
	private final String DB_PASS = "";

	public List<User> findAll(){
		List<User> userList = new ArrayList<>();

		//データベース接続
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文の準備
			String sql = "SELECT * FROM ACCOUNT";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//SQL実行
			ResultSet rs = pStmt.executeQuery();

			//結果をArrayListに保存
			while(rs.next()) {
				String address = rs.getString("ADDRESS");
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");
				User user = new User(address, name, pass);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return userList;
	}

	public boolean newAccount(User newUser) {
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL文の準備
			String sql = "INSERT INTO ACCOUNT(ADDRESS, NAME, PASS) VALUES (?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//与えられた情報をSQL文に挿入
			pStmt.setString(1, newUser.getAddress());
			pStmt.setString(2, newUser.getName());
			pStmt.setString(3, newUser.getPass());

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
