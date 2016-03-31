package com.ecust.spider.task;

import java.util.Queue;

import com.ecust.spider.Value;
import com.ecust.spider.api.Task;
import com.ecust.spider.util.factory.ListFetcherFactory;

public class SpiderExecuter implements Task {

	private Queue<String> mQueue;

	public SpiderExecuter(Queue<String> mQueue) {
		this.mQueue = mQueue;
	}

	public SpiderExecuter() {
		this.mQueue = Value.totalQueue;
	}

	@Override
	public void run() {
		if (Value.getDbState()) {
			while (!mQueue.isEmpty()) {
				String url = mQueue.poll();
				new ListFetcherFactory().getListFetcher(url).ExcuteList(url);
				System.out.println(++Value.doneNum + "/" + Value.totleNum);
			}
		} else {
			System.out.println("数据库未清空！");
		}
	}

}
