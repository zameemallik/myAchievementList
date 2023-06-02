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
		// 画面遷移(ログイン画面)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		String address = request.getParameter("address");
		String pass = request.getParameter("pass");

		//ログイン処理
		GetLoginLogic checkLogin = new GetLoginLogic();
		User user = new User();
		user = checkLogin.execute(address, pass);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/myAchievementList/User/Main");
		}else {
			request.setAttribute("errorMsg", "メールアドレスまたはパスワードが間違っています。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/login.jsp");
			dispatcher.forward(request, response);
		}

	}

}
