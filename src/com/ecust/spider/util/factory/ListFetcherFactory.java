package com.ecust.spider.util.factory;

import com.ecust.spider.api.ListFetcher;
import com.ecust.spider.fetcher.listFetcher.JDListFetcher;
import com.ecust.spider.fetcher.listFetcher.SNListFetcher;
import com.ecust.spider.fetcher.listFetcher.YHDListFetcher;

public class ListFetcherFactory {

	public static final String JD = "jd";
	public static final String YHD = "yhd";
	public static final String SN = "suning";

	public ListFetcher getListFetcher(String oneListUrl) {
		if (oneListUrl.contains(JD)) {
			return new JDListFetcher();
		} else if (oneListUrl.contains(YHD)) {
			return new YHDListFetcher();
		} else if (oneListUrl.contains(SN)) {
			return new SNListFetcher();
		} else {
			System.out.println("暂时还无法处理的网站");
		}
		return null;
	}

}
