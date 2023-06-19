package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetTweetListLogic;
import model.PostTweetLogic;
import model.Tweet;
import model.User;

/**
 * Servlet implementation class Main
 */
@WebServlet("/User/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//DBからユーザーのTweet一覧を取得
		GetTweetListLogic getTweetList = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetList.execute(user.getAddress());

		//セッションスコープに保存
		session.setAttribute("tweetList", tweetList);

		//カテゴリーリストを作成
		List<String> catagoriesList = new ArrayList<>();
		//全ツイートを検証
		for(Tweet tweet: tweetList) {
			//ツイートのカテゴリーとカテゴリーリストを比較
			if(catagoriesList.size() == 0) {
				catagoriesList.add(tweet.getCatagories());
			}
			for(int i=0; i < catagoriesList.size(); i++) {
				//同じカテゴリーがすでにあったら終了
				if(tweet.getCatagories().equals(catagoriesList.get(i))) {
					break;
				}
				//最後までなかったらカテゴリーリストにカテゴリーを追加
				if(i == catagoriesList.size()-1) {
					catagoriesList.add(tweet.getCatagories());
				}
			}

		}
		//全カテゴリーを追加後、セッションスコープにカテゴリー情報を追加
		session.setAttribute("catagoriesList", catagoriesList);

		//main.jsp(投稿／一覧画面)にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//postされた値を取得
		String catagories = request.getParameter("catagories");
		String detail = request.getParameter("detail");

		//セッションスコープからUser情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//Userのアドレスを取得
		String address = user.getAddress();

		//取得した値でtweetインスタンスを生成
		Tweet tweet = new Tweet(address, catagories, detail);

		//データベースに保存
		PostTweetLogic postTweet = new PostTweetLogic();
		postTweet.excecute(tweet);

		//メインサーブレットのdoGetを呼び出し
		doGet(request, response);
	}

}
