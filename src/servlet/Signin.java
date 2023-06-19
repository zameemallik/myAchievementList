package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.MakeNewAccount;
import model.User;

@WebServlet("/n/Signin")
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�A�J�E���g�쐬�y�[�W�Ƀt�H���[�h
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�l���擾
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");

		//�ȑO�̑S�A�J�E���g���擾
		AccountDAO accountDao = new AccountDAO();
		List<User> userList = accountDao.findAll();

		//�A�h���X�����łɎg�p����Ă��Ȃ������m�F
		for(User user: userList) {
			if(user.getAddress().equals(address)) {
				//�G���[���b�Z�[�W�����N�G�X�g�X�R�[�v�ɕۑ�
				request.setAttribute("errorMsg", "���̃��[���A�h���X�͂��łɎg�p����Ă��܂��B");
				//�A�J�E���g�쐬�y�[�W�Ƀt�H���[�h
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		//�V�K�A�J�E���g����User�C���X�^���X����
		User newUser = new User(address, name, pass);
		//�f�[�^�x�[�X�ɓo�^
		MakeNewAccount makeNewAccount = new MakeNewAccount();
		boolean rs = makeNewAccount.execute(newUser);
		//�����Ȃ�Z�b�V�����X�R�[�v�ɕۑ����Č��ʉ�ʕ\��
		if(rs) {
			HttpSession session = request.getSession();
			session.setAttribute("user", newUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultSignin.jsp");
			dispatcher.forward(request, response);
		}else {
			//���s�Ȃ烆�[�U�[���Z�b�V�����X�R�[�v�ɕۑ������Ɍ��ʉ�ʕ\��
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultSignin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
