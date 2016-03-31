package com.ecust.spider.fetcher;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecust.spider.Constants;
import com.ecust.spider.util.ListFilter;

public class MapFetcher {

	public static LinkedHashMap<String, String> prase(String url) {
		LinkedHashMap<String, String> mMap = new LinkedHashMap<String, String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements lists = doc.getElementsByClass("mc");
			Elements links = lists.select("a[href]");
			for (Element link : links) {
				mMap.put(link.text(), link.attr("abs:href"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mMap;
	}

	public static LinkedBlockingQueue<String> praseQueue(String url) {
		LinkedBlockingQueue<String> mQueue = new LinkedBlockingQueue<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements lists = doc.getElementsByClass("mc");
			Elements links = lists.select("a[href]");
			for (Element link : links) {
				if (ListFilter.UrlJudge(link.attr("abs:href"), ListFilter.LIST)) {
					mQueue.add(link.attr("abs:href"));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mQueue;
	}

	public static HashSet<String> praseArray(String url) {
		if (url.equals(Constants.YHD_MAP_URL)) {
			return praseYHDArray(url);
		}else if (url.equals(Constants.SN_MAP_URL)) {
			return praseSNArray(url);
		}
		
		HashSet<String> mQueue = new HashSet<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements lists = doc.getElementsByClass("mc");
			Elements links = lists.select("a[href]");
			for (Element link : links) {
				if (link.attr("abs:href").startsWith("http://list.jd.com")) {
					mQueue.add(link.attr("abs:href"));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mQueue;
	}

	private static HashSet<String> praseYHDArray(String url) {
		HashSet<String> mQueue = new HashSet<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements lists = doc.getElementsByTag("em");
			Elements links = lists.select("span").select("a[href]");
			for (Element link : links) {
				if (link.attr("abs:href").startsWith("http://list.yhd.com")) {
					mQueue.add(link.attr("abs:href"));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mQueue;
	}
	
	private static HashSet<String> praseSNArray(String url) {
		HashSet<String> mQueue = new HashSet<String>();
		try {
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("span").select("a.searchCity").select("a[href]");
			for (Element link : links) {
				if (link.attr("abs:href").startsWith("http://list.suning.com")) {
					mQueue.add(link.attr("abs:href"));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mQueue;
	}

	public static void main(String[] args) {
//		ArrayList<String> mArrayList = praseYHDArray("http://www.yhd.com/marketing/allproduct.html");
//		System.out.print(mArrayList);
	}

}