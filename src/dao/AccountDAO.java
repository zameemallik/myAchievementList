package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

//�o�^�ς݃A�J�E���g���̃f�[�^�x�[�X
public class AccountDAO {
	//�f�[�^�x�[�X�ڑ��Ɏg�p������
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/myAchievementList";
	private final String DB_USER = "";
	private final String DB_PASS = "";

	public List<User> findAll(){
		List<User> userList = new ArrayList<>();

		//�f�[�^�x�[�X�ڑ�
		try(Connection con = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			//SQL���̏���
			String sql = "SELECT * FROM ACCOUNT";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//SQL���s
			ResultSet rs = pStmt.executeQuery();

			//���ʂ�ArrayList�ɕۑ�
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
			//SQL���̏���
			String sql = "INSERT INTO ACCOUNT(ADDRESS, NAME, PASS) VALUES (?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			//�^����ꂽ����SQL���ɑ}��
			pStmt.setString(1, newUser.getAddress());
			pStmt.setString(2, newUser.getName());
			pStmt.setString(3, newUser.getPass());

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
