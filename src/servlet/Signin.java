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
		//アカウント作成ページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//値を取得
		String address = request.getParameter("address");
		String name = request.getParameter("name");
		String pass = request.getParameter("password");

		//以前の全アカウントを取得
		AccountDAO accountDao = new AccountDAO();
		List<User> userList = accountDao.findAll();

		//アドレスがすでに使用されていないかを確認
		for(User user: userList) {
			if(user.getAddress().equals(address)) {
				//エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMsg", "このメールアドレスはすでに使用されています。");
				//アカウント作成ページにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/signin.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		//新規アカウント情報でUserインスタンス生成
		User newUser = new User(address, name, pass);
		//データベースに登録
		MakeNewAccount makeNewAccount = new MakeNewAccount();
		boolean rs = makeNewAccount.execute(newUser);
		//成功ならセッションスコープに保存して結果画面表示
		if(rs) {
			HttpSession session = request.getSession();
			session.setAttribute("user", newUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultSignin.jsp");
			dispatcher.forward(request, response);
		}else {
			//失敗ならユーザーをセッションスコープに保存せずに結果画面表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/resultSignin.jsp");
			dispatcher.forward(request, response);
		}
	}

}
