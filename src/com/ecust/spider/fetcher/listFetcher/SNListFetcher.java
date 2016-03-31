package com.ecust.spider.fetcher.listFetcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ecust.spider.Constants;
import com.ecust.spider.api.ListFetcher;

public class SNListFetcher extends ListFetcher {

	@Override
	public void ExcuteList(String oneListUrl) {
		// TODO Auto-generated method stub
		String[] Listclass ={"div#proShow&a.search-bl","div#productTab&a.sell"};
		Map<String, String> pageClass = new HashMap<String, String>();
		pageClass.put("snPages", "2");
		excuteGeneralList(oneListUrl, Listclass, pageClass, Constants.SN, 0);

	}

	@Override
	public String GetNextPage(String nextPageUrl, String page, int currentI) {
		try {
			String[] splitString = nextPageUrl.split("-");
			
			currentI=currentI-1;
			int num=Integer.parseInt(page);
			if(nextPageUrl.contains("cityId")){
				nextPageUrl = "";
				String[] jsonStrings=splitString[splitString.length-1].split("&");
				ArrayList<String> jsonNumber=new ArrayList<String>() ;
				for (String string : jsonStrings) {
					if(string.contains("=")){
					 String[] array=string.split("=");
					// System.out.println(array[1]);
					 jsonNumber.add(array[1]);
					}
				}
				for (int i = 0; i < num; i++) {
					nextPageUrl += splitString[i] + "-";
				}
				// TODO Auto-generated method stub
				nextPageUrl +=currentI + "-";
				nextPageUrl +=jsonNumber.get(2) + "-";
				nextPageUrl +=jsonNumber.get(1) + "-";
				nextPageUrl +=jsonNumber.get(0) ;
				nextPageUrl += ".html";
			}
			else {
				nextPageUrl = "";
				for (int i = 0; i < splitString.length-1; i++) {
					if(i==num){
						nextPageUrl += currentI + "-";
					}else {
						nextPageUrl += splitString[i] + "-";
					}
					
				}
				nextPageUrl +=splitString[splitString.length-1];
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("获取下一页发生错误");
			e.printStackTrace();
		}
		
		return nextPageUrl;
	}

}
