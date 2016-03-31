package com.ecust.spider.fetcher.itemFetcher;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecust.commen.util.HttpUtil;
import com.ecust.spider.api.ItemFetcher;
import com.ecust.spider.dao.entity.Item;

public class YHDItemFetcher extends ItemFetcher {
	@Override
	public Item getItemInfo(String url) {
		return getItemInfo(url, MAX_TRY);
	}

	@Override
	public Item getItemInfo(String url, int tryTime) {
		return super.getGeneralItemInfo("www.yhd.com", url, tryTime);
	}

	@Override
	public String getItemID(Element doc) {
		Elements idInfo = doc.select("input#productMercantId").select(
				"input[value]");
		String id = null;
		if (idInfo.size() != 0) {
			id = idInfo.attr("value");
			return id;
		}
		return id;
	}

	@Override
	public String getItemName(Element doc) {
		Elements nameInfo = doc.select("#productMainName");
		String name = null;

		if (nameInfo.size() != 0) {
			name = nameInfo.text().replace('\'', '_');
			return name;
		}
		return name;
	}

	@Override
	public String getItemPrice(String itemID) {
		if (itemID == null) {
			return null;
		}
		String price = null;
		if (!itemID.isEmpty()) {
			price = new HttpUtil()
					.getUrl("http://gps.yhd.com/restful/detail?mcsite=1&provinceId=1&pmId="
							+ itemID);
		}

		if (price.length() > 1) {
			JSONObject jsonObject = JSONObject.fromObject(price);
			return jsonObject.get("yhdPrice").toString();
		}
		return null;
	}

	@Override
	public String getItemImageUrl(Element doc) {
		Elements imgUrlInfo = doc.select("div.detail_wrap").select(
				"img#J_prodImg");
		String imgUrl = null;
		if (imgUrlInfo.size() != 0) {
			imgUrl = imgUrlInfo.attr("src");
			return imgUrl;
		}
		return imgUrl;
	}

	@Override
	public String getItemDetail(Element doc) {
		ArrayList<String> detail = new ArrayList<String>();
		Elements detailInfo = doc.select("div.detail_wrap")
				.select("td[style=vertical-align:top]").select("li");

		if (detailInfo.size() != 0) {
			for (Element info : detailInfo) {
				detail.add(info.text().replace('\'', '_'));
			}
			return detail.toString();
		}
		detailInfo = doc.select("div.des").select("div.descon")
				.select("div.desitem").select("dd[title]");

		if (detailInfo.size() != 0) {
			for (Element info : detailInfo) {
				detail.add(info.text().replace('\'', '_'));
			}
			return detail.toString();
		}
		return "暂无介绍";
	}

	@Override
	public ArrayList<String> getItemCategory(Element doc) {
		Elements categoryInfo = doc.select("div.detail_wrap")
				.select("div[data-tpa=DETAIL_CRUMBBOX]").select("a[onclick]");
		ArrayList<String> categoryList = new ArrayList<String>();
		if (categoryInfo.size() != 0) {
			for (Element info : categoryInfo) {
				categoryList.add(info.textNodes().get(0).text());
			}
			return categoryList;
		}
		return categoryList;
	}

}
