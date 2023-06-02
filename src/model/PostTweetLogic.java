package model;

import dao.TweetDAO;

public class PostTweetLogic {
	public void excecute(Tweet tweet) {
		TweetDAO tweetDao = new TweetDAO();
		tweetDao.postTweet(tweet);
	}
}
