package com.ecust.spider.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.ecust.spider.Constants;

public class ThreadCarveUtil {

	public static final int SIZE = 0;
	public static final int NUM = 1;

	public static ArrayList<Queue<String>> Carve(ArrayList<String> queue,
			int mode) {

		switch (mode) {
		case SIZE:
			return carveBySize(queue);
		case NUM:
			return carveByNum(queue);
		default:
			return null;
		}
	}

	public static ArrayList<Queue<String>> carveByNum(ArrayList<String> queue) {
		ArrayList<Queue<String>> queues = new ArrayList<Queue<String>>();
		int threadNum = Constants.THREAD_NUM;
		if (queue.size() / threadNum == 0) {
			Queue<String> tempQueue = new LinkedList<String>(queue);
			queues.add(tempQueue);
			return queues;
		}
		int threadSize = queue.size() / threadNum + 1;
		for (int i = 0; i < threadNum; i++) {
			if (queue.size() >= (i + 1) * threadSize - 1) {
				Queue<String> tempQueue = new LinkedList<String>(queue.subList(
						i * threadSize, (i + 1) * threadSize - 1));
				queues.add(tempQueue);
			} else {
				Queue<String> tempQueue = new LinkedList<String>(queue.subList(
						i * threadSize, queue.size() - 1));
				queues.add(tempQueue);
				break;
			}
		}
		return queues;
	}

	public static ArrayList<Queue<String>> carveBySize(ArrayList<String> queue) {

		int threadSize = Constants.THREAD_SIZE;
		ArrayList<Queue<String>> queues = new ArrayList<Queue<String>>();
		int threadNum = getTreadNum(queue.size(), threadSize);
		if (threadNum == 1) {
			Queue<String> tempQueue = new LinkedList<String>(queue);
			queues.add(tempQueue);
			return queues;
		}
		for (int i = 0; i < threadNum; i++) {
			if (queue.size() >= (i + 1) * threadSize - 1) {
				Queue<String> tempQueue = new LinkedList<String>(queue.subList(
						i * threadSize, (i + 1) * threadSize - 1));
				queues.add(tempQueue);
			} else {
				Queue<String> tempQueue = new LinkedList<String>(queue.subList(
						i * threadSize, queue.size() - 1));
				queues.add(tempQueue);
				break;
			}
		}
		queue = null;
		return queues;
	}

	public static int getTreadNum(int queueSize, int threadSize) {
		int intSize = queueSize / threadSize;
		if (intSize == 0) {
			return 1;
		}
		float floatSize = (float) queueSize / threadSize;
		if (floatSize == intSize) {
			return intSize;
		}
		return intSize + 1;

	}
}
