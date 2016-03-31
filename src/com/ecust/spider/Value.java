package com.ecust.spider;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import com.ecust.spider.dao.database.SqlUtil;

public class Value {
	// 此类保存运行状态
	private static boolean dbIsClear = false; // 数据库是否清空
	private static SqlUtil mSqlUtil;
	public static int count = 0;
	public static int threadNo = 0;
	public static int totleNum = 0;
	public static int doneNum = 0;
	public static Queue<String> totalQueue = new LinkedList<String>();
	public static int errNum = 0;

	public static void addQueue(Collection<String> queue) {
		totalQueue.addAll(queue);
	}

	public static boolean getDbState() {
		return dbIsClear;
	}

	public static void setDbState(boolean dbIsClear) {
		Value.dbIsClear = dbIsClear;
	}

	public static SqlUtil getmSqlUtil() {
		return mSqlUtil;
	}

	public static void setmSqlUtil(SqlUtil mSqlUtil) {
		Value.mSqlUtil = mSqlUtil;
	}

}
