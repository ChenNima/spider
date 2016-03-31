package com.ecust.spider.fetcher.listFetcher;

import java.util.HashMap;
import java.util.Map;

import com.ecust.spider.Constants;
import com.ecust.spider.api.ListFetcher;

public class YHDListFetcher extends ListFetcher {

	@Override
	public void ExcuteList(String oneListUrl) {

		String[] Listclass = { "div.mod_search_list&p.title ","div#itemSearchList&p.title","div#itemSearchList&a.search_prod_img","div#itemSearchList&p.proName" };
		Map<String, String> pageClass = new HashMap<String, String>();
		pageClass.put("turn_page", "p");
		int length = "http://item.yhd.com/item/".length();
		excuteGeneralList(oneListUrl, Listclass, pageClass, Constants.YHD,
				length);
	}
	

	@Override
	public String GetNextPage(String nextPageUrl, String page, int currentI) {
		// 拼接地址
		String[] splitString = nextPageUrl.split("-");
		nextPageUrl = "";
		for (int i = 0; i < splitString.length - 1; i++) {
			if (splitString[i].contains(page)
					&& splitString[i + 1].equals("price")) {
				page += currentI;
				nextPageUrl += page + "-";
			} else {
				nextPageUrl += splitString[i] + "-";
			}

		}
		nextPageUrl += splitString[splitString.length - 1];
		return nextPageUrl;
	}

}
