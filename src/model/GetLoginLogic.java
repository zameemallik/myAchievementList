package model;

import java.util.List;

import dao.AccountDAO;


//���[�U�[�f�[�^�ƃp�X���[�h�������Ă邩�m�F
public class GetLoginLogic {
	//���A�h�ƃp�X���[�h���󂯎��
	public User execute(String address, String pass) {
		AccountDAO acntDAO = new AccountDAO();
		List<User> userList = acntDAO.findAll();
		for(User user:userList) {
			if(user.getAddress().equals(address) && user.getPass().equals(pass)) {
				//��񂪂��ׂĐ������Ȃ�user��Ԃ�
				return user;
			}
		}
		return null;
	}
}
