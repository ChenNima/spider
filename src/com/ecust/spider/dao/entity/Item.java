package com.ecust.spider.dao.entity;

import java.util.ArrayList;

public class Item {
	private String name; 			//商品名称
	private String host;			//商品来源
	private String id;					//商品ID
	private String price;			//商品价格
	private ArrayList<String> catgory;	//商品完整分类结构（从大到小）
	private String url;				//链接地址
	private String imageUrl;		//图片链接
	private String description;		//商品描述
	private String catFirst;		//商品第一级分类（大）
	private String catSecond;		//商品第二季分类（中）
	private String catThird;		//商品第二季分类（小）
	private String brand = "";			//品牌
	
	public Item(){					//空构造器
		
	}
	
	public Item(String name,String host,String id,String price,	//完整构造器
			ArrayList<String> catgory,String url,String imageUrl,String description){
		setName(removeQuo(name));
		setHost(host);
		setId(id);
		setPrice(price);
		setUrl(url);
		setImageUrl(imageUrl);
		setDescription(removeQuo(description));
		for(int i = 0;i<catgory.size();i++){
			catgory.set(i, removeQuo(catgory.get(i)));
		}
		adaptCatgory(catgory);
	}
	
	@Override
	public String toString(){		//获取商品信息
		return new String("该商品："+"名称="+name+" "+
				"ID="+id+" "+
				"来源="+host+" "+
				"价格="+price+" "+
				"分类="+catgory+" "+
				"三级分类="+catFirst+"|"+catSecond+"|"+catThird+" "+
				"链接="+url+" "+
				"图片链接="+imageUrl+" "+
				"描述="+description);
	}
	
	public void adaptCatgory(ArrayList<String> Catgory){
		setCatgory(Catgory);
		switch(Catgory.size()){
		case 0:
			System.out.println("分类信息为空!");
			break;
		case 1:
			setCatFirst(Catgory.get(0));
			break;
		case 2:
			setCatFirst(Catgory.get(0));
			setCatSecond(Catgory.get(1));
			break;
		case 3:
			setCatFirst(Catgory.get(0));
			setCatSecond(Catgory.get(1));
			setCatThird(Catgory.get(2));
			break;
		default:
			setCatFirst(Catgory.get(0));
			setCatSecond(Catgory.get(1));
			setCatThird(Catgory.get(2));
			setBrand(Catgory.get(Catgory.size()-1));
			break;
		}
	}

	private String removeQuo(String str){
		String rep = new String (str);
		rep = rep.replace("'", "''");
		return rep;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCatFirst() {
		return catFirst;
	}
	
	public void setCatFirst(String catFirst) {
		this.catFirst = catFirst;
	}

	public String getCatSecond() {
		return catSecond;
	}

	public void setCatSecond(String catSecond) {
		this.catSecond = catSecond;
	}

	public ArrayList<String> getCatgory() {
		return catgory;
	}

	public void setCatgory(ArrayList<String> catgory) {
		this.catgory = catgory;
	}

	public String getCatThird() {
		return catThird;
	}

	public void setCatThird(String catThird) {
		this.catThird = catThird;
	}

	public String getBrand() {
		if(brand.equals("")){
			return null;
		}
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
