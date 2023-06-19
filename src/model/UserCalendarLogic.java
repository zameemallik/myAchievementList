package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCalendarLogic {
	//カレンダーインスタンスを生成するメソッド
	public UserCalendar createUserCalendar(List<Tweet> tweetList, int... args) {
		//UserCalendarインスタンス生成
		UserCalendar uc = new UserCalendar();
		//現在日時でカレンダーインスタンス生成
		Calendar cal = Calendar.getInstance();
		//2つ引数がきてたら
		if(args.length == 2) {
			//最初の引数で年を設定
			cal.set(Calendar.YEAR, args[0]);
			//二つ目の引数で月を設定
			cal.set(Calendar.MONTH, args[1]-1);
		}
		//UserCalendarに年を設定
		uc.setYear(cal.get(Calendar.YEAR));
		//月を設定（1~12）
		uc.setMonth(cal.get(Calendar.MONTH) + 1);
		//曜日と日にちを合わせるために日付を１日にする
		cal.set(Calendar.DATE, 1);
		//１日がその週の何日目かをだしてそれまでの日数を保持する
		int before = cal.get(Calendar.DAY_OF_WEEK)-1;
		//月が何日までかを出す
		int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//最終日の曜日をしらべるために最終日に設定
		cal.set(Calendar.DATE, daysCount);
		//最終日が何曜日かをだしてその後の日数を保持
		int after = 7 - cal.get(Calendar.DAY_OF_WEEK);
		//その月のすべての要素数をだす
		int total = before + daysCount + after;
		//表示カレンダーの行数を出す
		int rows = total/7;
		//rowsの行数で２次元配列を作成
		String[][] data = new String[rows][7];
		//今見ているカレンダーが今月かどうかを調べるため、この瞬間のカレンダーインスタンスをもう一つ生成
		Calendar now = Calendar.getInstance();
		//縦はカレンダーの行数文、横は曜日(7日)文の２次元配列に情報を入れる
		for(int i=0; i < rows; i++) {
			for(int j = 0; j< 7; j++) {
				if(i==0 && j<before || i == rows -1 && j >=(7-after)) {
					//カレンダーの前後を空欄にする
					data[i][j] = "";
				}else {
					//カウンター変数を実際の日付に変換
					int date = i * 7 + j + 1 - before;
					//配列に日付を入れる
					data[i][j] = String.valueOf(date);
					//今作業しているマイカレンダーが今月のカレンダーだったら今日の日付の先頭に*を付与する
					if(now.get(Calendar.DATE)== date && now.get(Calendar.MONTH)==uc.getMonth()-1  && now.get(Calendar.YEAR)==uc.getYear()) {
						data[i][j]="*"+data[i][j];
					}
				}

			}
		}
		//作成した２次元配列UserCalendarにセット
		uc.setData(data);

		//tweetDateを作成
		Map<String, List<Tweet>> tweetDate = new HashMap<>();
		//tweetListのすべてのリストを確認
		for(Tweet tweet: tweetList) {
			//年、月が一緒ならtweetDateに追加
			if(tweet.getMonth() == uc.getMonth() && tweet.getYear() == uc.getYear()) {
				List<Tweet> twl = new ArrayList<>();
				//追加するtweetをリストに保存
				twl.add(tweet);
				//追加日を保持
				int theDay = tweet.getDate();
				String s_theDay = String.valueOf(theDay);

				//今日だったら＊を追加
				if(now.get(Calendar.DATE)== theDay && now.get(Calendar.MONTH)==uc.getMonth()-1  && now.get(Calendar.YEAR)==uc.getYear()) {
					s_theDay = "*" + s_theDay;
				}

				//追加日にもとからリストがあるかを確認
				if(tweetDate.get(s_theDay) != null) {
				//あったらそれもまとめてtwlに保持
					for(Tweet oldTweets:tweetDate.get(s_theDay)) {
						twl.add(oldTweets);
					}
				}

				//追加
				tweetDate.put(s_theDay, twl);
			}
		}
		uc.setTweetDate(tweetDate);

		return uc;
	}
}
