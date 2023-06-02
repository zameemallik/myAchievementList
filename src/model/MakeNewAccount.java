package model;

import dao.AccountDAO;

public class MakeNewAccount {
	public boolean execute(User newUser) {
		AccountDAO acntDao = new AccountDAO();
		boolean rs  = acntDao.newAccount(newUser);
		return rs;
	}
}
