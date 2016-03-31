package com.ecust.spider.task;

import java.util.Calendar;
import java.util.Date;
import java.util.Queue;
import java.util.Timer;

import com.ecust.spider.Constants;
import com.ecust.spider.api.Task;

public class SpiderTask implements Task {
	private int type;

	public SpiderTask(int type) {
		this.type = type;
	}
	public SpiderTask() {
	}

	@Override
	public void run() {
		// dailyTask();
		// timeTask(12);
		runOnce();
	}

	private void runOnce() {
		new Thread(new DailyTask() {
		}) {
		}.start();
	}

	private void timeTask(int hour) {
		long time = hour * 60 * 60 * 1000;// 指定小时运行一次
		Timer timer = new Timer();
		timer.schedule(new DailyTask(type), time);
	}

	private void dailyTask() {

		int hour = 5;
		int min = 0;
		int sec = 0;

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, min);
		calendar.set(Calendar.SECOND, sec);
		Date time = calendar.getTime();

		Timer timer = new Timer();

		timer.schedule(new DailyTask(type), time);

	}
}
