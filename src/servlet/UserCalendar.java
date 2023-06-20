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

		//�����Ƃ̓��e�J�e�S���[�����N�G�X�g�X�R�[�v�ɕێ�����v���O�����B���Ԃ�͂Ȃ�
		//2�d�z���for���ł܂킷
		//�e�s�̒l��row�ɑ��
		for(String[] row: uc.getData()){
			//row���e���ɂ���col�ɑ��
	      for(String day:row) {
	    	  //���ɂ����Ƃ̓��e���X�g���擾
	      		for(String key : uc.getTweetDate().keySet()) {
	      			//�������Ȃ�
	      			if(day.equals(key)){
	      				//���Ԃ���Ȃ�����Set����錾
		      			Set<String> x = new HashSet<>();
		      			//���̓��̓��e���ɃJ�e�S���[���擾
		      			for(Tweet tweet: uc.getTweetDate().get(key)){
		      				//Set�ɒǉ��i���Ԃ��Ă���ǉ�����Ȃ��j
		      				x.add(tweet.getCatagories());
		      			}
		      			//���N�G�X�g�X�R�[�v�ɕۑ�
		      			request.setAttribute("tweetday" + day, x);
	      			}
	      		}
	      }
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/userCalendar.jsp");
		dispatcher.forward(request, response);
	}
}
