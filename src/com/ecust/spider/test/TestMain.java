package com.ecust.spider.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;

import com.ecust.spider.Constants;
import com.ecust.spider.Value;
import com.ecust.spider.dao.database.SqlUtil;
import com.ecust.spider.task.SpiderExecuter;

public class TestMain {

	static HashSet<String> mHashSet;
	static ArrayList<Queue<String>> treadQueues;

	public static void main(String[] args) {
		SqlUtil mSqlUtil = new SqlUtil(Constants.DB_NAME,
				Constants.DB_USER_NAME, Constants.DB_USER_PASS);
		Value.setmSqlUtil(mSqlUtil);
		if (!Value.getDbState() && Value.getmSqlUtil() != null) {
			Value.getmSqlUtil().deleteAll(Constants.JD_TABLE); // 清空数据库
			Value.getmSqlUtil().deleteAll(Constants.YHD_TABLE);
			Value.getmSqlUtil().deleteAll(Constants.SN_TABLE);
		}

		ArrayList<String> queue = new ArrayList<String>();

		//单一list爬取测试
		queue.add("http://list.suning.com/0-20006-0-0-0-9264.html#sourceUrl4Sa=http://www.suning.com/emall/pgv_10052_10051_1_.html");
		queue.add("http://list.suning.com/0-258003-0-0-0-9264.html#sourceUrl4Sa=http://www.suning.com/emall/pgv_10052_10051_1_.html");
		Value.addQueue(queue);//

		for (int i = 0; i < queue.size(); i++) {
			new Thread(new SpiderExecuter() {
			}) {
			}.start();
		}
		System.out.println(Value.totalQueue.size());
	}

}
