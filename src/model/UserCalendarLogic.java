package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCalendarLogic {
	//�J�����_�[�C���X�^���X�𐶐����郁�\�b�h
	public UserCalendar createUserCalendar(List<Tweet> tweetList, int... args) {
		//UserCalendar�C���X�^���X����
		UserCalendar uc = new UserCalendar();
		//���ݓ����ŃJ�����_�[�C���X�^���X����
		Calendar cal = Calendar.getInstance();
		//2���������Ă���
		if(args.length == 2) {
			//�ŏ��̈����ŔN��ݒ�
			cal.set(Calendar.YEAR, args[0]);
			//��ڂ̈����Ō���ݒ�
			cal.set(Calendar.MONTH, args[1]-1);
		}
		//UserCalendar�ɔN��ݒ�
		uc.setYear(cal.get(Calendar.YEAR));
		//����ݒ�i1~12�j
		uc.setMonth(cal.get(Calendar.MONTH) + 1);
		//�j���Ɠ��ɂ������킹�邽�߂ɓ��t���P���ɂ���
		cal.set(Calendar.DATE, 1);
		//�P�������̏T�̉����ڂ��������Ă���܂ł̓�����ێ�����
		int before = cal.get(Calendar.DAY_OF_WEEK)-1;
		//���������܂ł����o��
		int daysCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//�ŏI���̗j��������ׂ邽�߂ɍŏI���ɐݒ�
		cal.set(Calendar.DATE, daysCount);
		//�ŏI�������j�����������Ă��̌�̓�����ێ�
		int after = 7 - cal.get(Calendar.DAY_OF_WEEK);
		//���̌��̂��ׂĂ̗v�f��������
		int total = before + daysCount + after;
		//�\���J�����_�[�̍s�����o��
		int rows = total/7;
		//rows�̍s���łQ�����z����쐬
		String[][] data = new String[rows][7];
		//�����Ă���J�����_�[���������ǂ����𒲂ׂ邽�߁A���̏u�Ԃ̃J�����_�[�C���X�^���X�����������
		Calendar now = Calendar.getInstance();
		//�c�̓J�����_�[�̍s�����A���͗j��(7��)���̂Q�����z��ɏ�������
		for(int i=0; i < rows; i++) {
			for(int j = 0; j< 7; j++) {
				if(i==0 && j<before || i == rows -1 && j >=(7-after)) {
					//�J�����_�[�̑O����󗓂ɂ���
					data[i][j] = "";
				}else {
					//�J�E���^�[�ϐ������ۂ̓��t�ɕϊ�
					int date = i * 7 + j + 1 - before;
					//�z��ɓ��t������
					data[i][j] = String.valueOf(date);
					//����Ƃ��Ă���}�C�J�����_�[�������̃J�����_�[�������獡���̓��t�̐擪��*��t�^����
					if(now.get(Calendar.DATE)== date && now.get(Calendar.MONTH)==uc.getMonth()-1  && now.get(Calendar.YEAR)==uc.getYear()) {
						data[i][j]="*"+data[i][j];
					}
				}

			}
		}
		//�쐬�����Q�����z��UserCalendar�ɃZ�b�g
		uc.setData(data);

		//tweetDate���쐬
		Map<String, List<Tweet>> tweetDate = new HashMap<>();
		//tweetList�̂��ׂẴ��X�g���m�F
		for(Tweet tweet: tweetList) {
			//�N�A�����ꏏ�Ȃ�tweetDate�ɒǉ�
			if(tweet.getMonth() == uc.getMonth() && tweet.getYear() == uc.getYear()) {
				List<Tweet> twl = new ArrayList<>();
				//�ǉ�����tweet�����X�g�ɕۑ�
				twl.add(tweet);
				//�ǉ�����ێ�
				int theDay = tweet.getDate();
				String s_theDay = String.valueOf(theDay);

				//�����������灖��ǉ�
				if(now.get(Calendar.DATE)== theDay && now.get(Calendar.MONTH)==uc.getMonth()-1  && now.get(Calendar.YEAR)==uc.getYear()) {
					s_theDay = "*" + s_theDay;
				}

				//�ǉ����ɂ��Ƃ��烊�X�g�����邩���m�F
				if(tweetDate.get(s_theDay) != null) {
				//�������炻����܂Ƃ߂�twl�ɕێ�
					for(Tweet oldTweets:tweetDate.get(s_theDay)) {
						twl.add(oldTweets);
					}
				}

				//�ǉ�
				tweetDate.put(s_theDay, twl);
			}
		}
		uc.setTweetDate(tweetDate);

		return uc;
	}
}
