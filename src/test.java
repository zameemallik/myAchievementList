import java.util.List;

import model.GetTweetListLogic;
import model.Tweet;
import model.UserCalendar;
import model.UserCalendarLogic;

public class test {
	public static void main(String[] args) {
		GetTweetListLogic list = new GetTweetListLogic();
		List<Tweet> twtList = list.execute("test01@gmai.com");
		UserCalendarLogic user = new UserCalendarLogic();
		UserCalendar usc = user.createUserCalendar(twtList);
		System.out.println(usc.getTweetDate());
		for(int i = 1; i < 32; i ++) {
			System.out.println(i + "“ú–Ú");
			if(usc.getTweetDate().get(i) != null) {
				List<Tweet> tweetList = usc.getTweetDate().get(i);
				for(Tweet tweet:tweetList) {
					System.out.println(tweet.getDate() + tweet.getDetail());;
				}
			}
		}
	}
}
