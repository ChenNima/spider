package com.ecust.spider.fetcher.itemFetcher;

import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ecust.commen.util.HttpUtil;
import com.ecust.spider.api.ItemFetcher;
import com.ecust.spider.dao.entity.Item;

public class JDItemFetcher extends ItemFetcher {

	@Override
	// 获得京东商品信息
	public Item getItemInfo(String url) {
		return getItemInfo(url, MAX_TRY);
	}

	@Override
	public Item getItemInfo(String url, int tryTime) {
		return getGeneralItemInfo("www.jd.com", url, tryTime);
	}

	@Override
	protected Item getGeneralItemInfo(String host, String url, int tryTime) {
		return super.getGeneralItemInfo(host, url, tryTime);
	}

	// 获得商品名
	@Override
	public String getItemName(Element doc) {
		Elements ItemInfo = doc.select("div.m-item-inner")
				.select("div#itemInfo").select("div#name").select("h1");
		if (ItemInfo.size() != 0) {
			return ItemInfo.text().replace('\'', '_');
		}

		ItemInfo = doc.select("div#product-intro").select("div#name")
				.select("h1");
		if (ItemInfo.size() != 0) {
			return ItemInfo.text().replace('\'', '_');
		}

		return null;
	}

	@Override
	// 获得图片
	public String getItemImageUrl(Element doc) {
		Elements ImgInfo = doc.select("div#preview").select("div.jqzoom")
				.select("img[src]");
		if (ImgInfo.size() != 0) {
			return ImgInfo.first().attr("src");
		}
		ImgInfo = doc.select("div#preview").select("div#spec-n1")
				.select("img[src]");
		if (ImgInfo.size() != 0) {
			return ImgInfo.first().attr("src");
		}
		return null;

	}

	@Override
	// 商品ID
	public String getItemID(Element doc) {
		Elements idElement = doc.select("div.m-item-inner")
				.select("div#summary-price").select("div.dd")
				.select("a[data-sku]");
		String id = null;
		if (idElement.size() == 0) {
			idElement = doc.select("div.clearfix").select("div.dd")
					.select("a[data-sku]");
		}
		if (idElement.size() != 0) {
			id = idElement.attr("data-sku");
		}

		if (id != "") {
			return id;
		} else {
			return null;
		}

	}

	@Override
	// 商品价格
	public String getItemPrice(String itemID) {
		if (itemID == null) {
			return null;
		}
		String price = null;
		if (!itemID.isEmpty()) {
			price = new HttpUtil().getUrl("http://p.3.cn/prices/get?skuid=J_"
					+ itemID + "&tpye=1");
		}

		if (price.length() > 1) {
			price = price.substring(1, price.length() - 2);
			JSONObject jsonObject = JSONObject.fromObject(price);
			return jsonObject.get("p").toString();
		}
		return null;

	}

	@Override
	// 商品类别
	public ArrayList<String> getItemCategory(Element doc) {
		ArrayList<String> itemCategory = new ArrayList<String>();
		Elements categoryInfo = doc.select("div.breadcrumb")
				.select("a[clstag]");
		if (categoryInfo.size() == 0) {
			categoryInfo = doc.select("div.breadcrumb").select("a[href]");
		}
		for (Element category : categoryInfo) {
			String itemCategoryInfo = category.text();
			if (itemCategoryInfo != null) {
				itemCategoryInfo = itemCategoryInfo.replace('\'', '_');
				itemCategory.add(itemCategoryInfo);
			}

		}
		return itemCategory;
	}

	@Override
	// 详细信息
	public String getItemDetail(Element doc) {
		ArrayList<String> detail = new ArrayList<String>();
		Elements detailList = doc.select("ul.p-parameter-list");
		if (detailList.size() == 0) {
			detailList = doc.select("div#product-detail-1")
					.select("div.p-parameter").select("ui.p-parameter-list");
		}
		for (Element detailInfo : detailList) {
			String Detail = detailInfo.text();
			Detail = Detail.replace('\'', '_');
			detail.add(Detail);
		}
		return detail.toString();
	}

}
