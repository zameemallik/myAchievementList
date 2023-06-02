package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetLoginLogic;
import model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/n/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ʑJ��(���O�C�����)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���N�G�X�g�p�����[�^�̎擾
		String address = request.getParameter("address");
		String pass = request.getParameter("pass");

		//���O�C������
		GetLoginLogic checkLogin = new GetLoginLogic();
		User user = new User();
		user = checkLogin.execute(address, pass);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/myAchievementList/User/Main");
		}else {
			request.setAttribute("errorMsg", "���[���A�h���X�܂��̓p�X���[�h���Ԉ���Ă��܂��B");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}

	}

}
