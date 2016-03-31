package com.ecust.spider.fetcher.listFetcher;

import java.util.HashMap;
import java.util.Map;

import com.ecust.spider.Constants;
import com.ecust.spider.api.ListFetcher;

public class JDListFetcher extends ListFetcher {


	@Override
	public void ExcuteList(String oneListUrl) {
		String[] Listclass = {"div#plist&div.p-name","div.goods-list-v1&div.p-name","div.plist&div.p-name" };
		Map<String, String> pageClass = new HashMap<String, String>();
		pageClass.put("p-num", "page=");
		pageClass.put("pagin", "p=");
		excuteGeneralList(oneListUrl, Listclass, pageClass, Constants.JD, 0);
	}

	@Override
	public String GetNextPage(String nextPageUrl, String page, int currentI) {
		// 拼接地址
		String[] splitString = nextPageUrl.split("&");
		nextPageUrl = "";
		for (int i = 0; i < splitString.length - 1; i++) {
			if (splitString[i].contains(page)
					&& splitString[i + 1].contains("JL")) {
				page += currentI;
				nextPageUrl += page + "&";
			} else {
				nextPageUrl += splitString[i] + "&";
			}

		}
		nextPageUrl += splitString[splitString.length - 1];
		return nextPageUrl;
	}

}
