package com.ecust.spider.dao.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ecust.spider.Value;
import com.ecust.spider.dao.entity.Item;

public class SqlUtil {

	private String dbName;
	private String userName;
	private String password;
	protected Statement stmt;

	public SqlUtil(String dbName, String userName, String password) {
		setDbName(dbName);
		setUserName(userName);
		setPassword(password);
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/" + dbName + "?user="
					+ userName + "&password=" + password
					+ "&useUnicode=true&characterEncoding=utf-8";
			Connection conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (Exception e) {
			System.out.println("链接数据库出错！");
			e.printStackTrace();
		}
	}

	public void addItem(Item item, String table) {
		String sql = "INSERT INTO "
				+ table
				+ "(Iname,Iid,Ibrand,Ihost,Iprice,Ifirst_cat,Isecond_cat,Ithird_cat,Iurl,Iimg_url,Idescription)"
				+ " VALUES('" + item.getName() + "','" + item.getId() + "','"
				+ item.getBrand() + "','" + item.getHost() + "','"
				+ item.getPrice() + "','" + item.getCatFirst() + "','"
				+ item.getCatSecond() + "','" + item.getCatThird() + "','"
				+ item.getUrl() + "','" + item.getImageUrl() + "','"
				+ item.getDescription() + "')";
		try {
			stmt.execute(sql);
			System.out.println(++Value.count +" "+item.getCatgory()+"\t"+item.getName() + "已写入数据库"+"\t"+Value.doneNum+"/"+Value.totleNum+"\t"+Value.errNum);
			if (Value.getDbState()) {
				Value.setDbState(false);
			}
		} catch (Exception e) {
			System.out.println("写入数据库出错！");
			e.printStackTrace();
		}
	}

	public void getAll(String table) {
		String sql = new String("select * from " + table);
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getInt("P_Id") + "\t"
						+ rs.getString("Iname") + "\t" + rs.getString("Iprice")
						+ "\t" + rs.getString("Ithird_cat"));
			}
		} catch (Exception e) {
			System.out.println("数据库查询出错！");
			e.printStackTrace();
		}
	}

	public void deleteAll(String table) {
		String sql = new String("DELETE FROM " + table);
		String sql2 = new String("ALTER TABLE " + table + " AUTO_INCREMENT=1");
		try {
			stmt.execute(sql);
			stmt.execute(sql2);
			System.out.println("数据库已清空！");
			Value.setDbState(true);
		} catch (Exception e) {
			System.out.println("删除失败！");
			e.printStackTrace();
		}
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
