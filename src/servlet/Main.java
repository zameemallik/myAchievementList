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
		//�Z�b�V�����X�R�[�v���烆�[�U�[�����擾
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//DB���烆�[�U�[��Tweet�ꗗ���擾
		GetTweetListLogic getTweetList = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetList.execute(user.getAddress());

		//�Z�b�V�����X�R�[�v�ɕۑ�
		session.setAttribute("tweetList", tweetList);

		//main.jsp(���e�^�ꗗ���)�Ƀt�H���[�h
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post���ꂽ�l���擾
		String title = request.getParameter("title");
		String detail = request.getParameter("detail");

		//�Z�b�V�����X�R�[�v����User�����擾
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//User�̃A�h���X���擾
		String address = user.getAddress();

		//�擾�����l��tweet�C���X�^���X�𐶐�
		Tweet tweet = new Tweet(address, title, detail);

		//�f�[�^�x�[�X�ɕۑ�
		PostTweetLogic postTweet = new PostTweetLogic();
		postTweet.excecute(tweet);

		//���C���T�[�u���b�g��doGet���Ăяo��
		doGet(request, response);
	}

}
