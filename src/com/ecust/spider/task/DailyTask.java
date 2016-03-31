package com.ecust.spider.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.TimerTask;

import com.ecust.spider.Constants;
import com.ecust.spider.Value;
import com.ecust.spider.fetcher.MapFetcher;

public class DailyTask extends TimerTask {

	static HashSet<String> mHashSet;
	static ArrayList<Queue<String>> treadQueues;
	private int type;

	public DailyTask(int type) {
		this.type = type;
	}

	public DailyTask() {
	}

	@Override
	public void run() {
		if (!Value.getDbState() && Value.getmSqlUtil() != null) {
			Value.getmSqlUtil().deleteAll(Constants.JD_TABLE); // 清空数据库
			Value.getmSqlUtil().deleteAll(Constants.YHD_TABLE);
			Value.getmSqlUtil().deleteAll(Constants.SN_TABLE);
		}
		
		addMapToQueue(Constants.JD_MAP_URL);
//		addMapToQueue(Constants.YHD_MAP_URL);  一号店反爬虫
		addMapToQueue(Constants.SN_MAP_URL);
		
		for (int i = 0; i < Constants.THREAD_NUM; i++) {
			new Thread(new SpiderExecuter() {
			}) {
			}.start();
		}
		System.out.println(Value.totalQueue.size());
	}

	private void addMapToQueue(String map) {
		mHashSet = MapFetcher.praseArray(map);
		Value.totleNum += mHashSet.size();
		Value.addQueue(mHashSet);
	}

}
