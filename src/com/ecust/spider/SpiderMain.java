package com.ecust.spider;


import java.util.ArrayList;
import java.util.Queue;

import com.ecust.spider.dao.database.SqlUtil;
import com.ecust.spider.fetcher.MapFetcher;
import com.ecust.spider.fetcher.itemFetcher.SNItemFetcher;
import com.ecust.spider.task.DailyTask;
import com.ecust.spider.task.SpiderTask;
import com.ecust.spider.util.ThreadCarveUtil;

public class SpiderMain {
	
	public static void main(String[] args) {
		SqlUtil mSqlUtil = new SqlUtil(Constants.DB_NAME,Constants.DB_USER_NAME,Constants.DB_USER_PASS);
		Value.setmSqlUtil(mSqlUtil);
		
//		System.getProperties().put("http.proxySet","ture");
//		System.getProperties().put("http.proxyHost","192.168.10.20");
//		System.getProperties().put("http.proxyProt","8080");
//		System.getProperties().put("http.proxyHosts","localhost|127.0.0.1");

		
//		new Thread(new SpiderTask(Constants.JD)
//		{}){}.start();
//		
//		new Thread(new SpiderTask(Constants.YHD)
//		{}){}.start();
		
		new Thread(new DailyTask()
		{}){}.start();

	}
}
