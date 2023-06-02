package model;

import java.util.List;

import dao.TweetDAO;

public class GetTweetListLogic {
	public List<Tweet> execute(String address){
		TweetDAO tweetdao = new TweetDAO();
		List<Tweet> tweetList = tweetdao.findAll(address);
		return tweetList;
	}
}
