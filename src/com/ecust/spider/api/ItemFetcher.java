package com.ecust.spider.api;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ecust.spider.Value;
import com.ecust.spider.dao.entity.Item;

public abstract class ItemFetcher {
	protected final static int MAX_TRY = 3;

	public abstract Item getItemInfo(String url);

	public abstract Item getItemInfo(String url, int tryTime);
	
	protected Item getGeneralItemInfo(String host,String url, int tryTime){
		tryTime--;
		Document doc;
		Item currentItem=new Item();
		String itemID;
		try 
		{		
            doc = Jsoup.connect(url).get();
            itemID=getItemID(doc);
            currentItem = new Item(getItemName(doc),host,itemID,getItemPrice(itemID),
            		getItemCategory(doc),url,getItemImageUrl(doc),getItemDetail(doc)
    				);
            
		}
		catch (Exception e) 
		{
			if(tryTime>=0)
			{
				System.out.println("重新获取item信息,url为"+url+"item"+currentItem.toString()+" 剩余次数："+(tryTime+1));
				return getItemInfo(url,tryTime);
			}
			else
			{
				System.out.println("获取Item失败，url为"+url+"item"+currentItem.toString());
				e.printStackTrace();
				Value.errNum++;
				return null;
			}
		}
		return currentItem;
	}

	public abstract String getItemName(Element doc);

	public abstract String getItemImageUrl(Element doc);
	
	public abstract String getItemID(Element doc);
	
	public	abstract String getItemPrice(String itemID);
	
	public	abstract ArrayList<String> getItemCategory(Element doc);
	
	public	abstract String getItemDetail(Element doc);
}
