package model;

import java.util.List;

import dao.AccountDAO;


//ユーザーデータとパスワードがあってるか確認
public class GetLoginLogic {
	//メアドとパスワードを受け取る
	public User execute(String address, String pass) {
		AccountDAO acntDAO = new AccountDAO();
		List<User> userList = acntDAO.findAll();
		for(User user:userList) {
			if(user.getAddress().equals(address) && user.getPass().equals(pass)) {
				//情報がすべて正しいならuserを返す
				return user;
			}
		}
		return null;
	}
}
