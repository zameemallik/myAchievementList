package servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tweet;
import model.UserCalendarLogic;

/**
 * Servlet implementation class UserCalendar
 */
@WebServlet("/User/UserCalendar")
public class UserCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");
		UserCalendarLogic logic = new UserCalendarLogic();
		model.UserCalendar uc = null;
		HttpSession session = request.getSession();
		List<Tweet> tweetList = (List<Tweet>)session.getAttribute("tweetList");
		if(s_year != null && s_month != null) {
			int year = Integer.parseInt(s_year);
			int month = Integer.parseInt(s_month);
			if(month == 0) {
				month = 12;
				year --;
			}
			if(month == 13) {
				month = 1;
				year ++;
			}
			uc = logic.createUserCalendar(tweetList, year, month);
		}else {
			uc = logic.createUserCalendar(tweetList);
		}
		session.setAttribute("userCalendar", uc);

		//日ごとの投稿カテゴリーをリクエストスコープに保持するプログラム。かぶりはなし
		//2重配列をfor文でまわす
		//各行の値をrowに代入
		for(String[] row: uc.getData()){
			//rowを各日にちのcolに代入
	      for(String day:row) {
	    	  //日にちごとの投稿リストを取得
	      		for(String key : uc.getTweetDate().keySet()) {
	      			//同じ日なら
	      			if(day.equals(key)){
	      				//かぶりを省くためSetリを宣言
		      			Set<String> x = new HashSet<>();
		      			//その日の投稿事にカテゴリーを取得
		      			for(Tweet tweet: uc.getTweetDate().get(key)){
		      				//Setに追加（かぶってたら追加されない）
		      				x.add(tweet.getCatagories());
		      			}
		      			//リクエストスコープに保存
		      			request.setAttribute("tweetday" + day, x);
	      			}
	      		}
	      }
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userCalendar.jsp");
		dispatcher.forward(request, response);
	}
}
