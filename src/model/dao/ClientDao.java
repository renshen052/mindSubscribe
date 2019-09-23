package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import baen.Client;
import utils.Util;
import utils.jdbc.JdbcUtil;

public class ClientDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询符合条件的用户
	 * 
	 * @param search
	 * @return
	 */
	public List<Client> listSearch(Map<String, String> search) {

		ArrayList<Client> list = new ArrayList<>();

		List<Object> searchList = new ArrayList<Object>();

		String sql = "SELECT * FROM client WHERE 1=1 ";

		// 姓名
		if (Util.isNotEmpty(search.get("name"))) {

			sql += " AND name like concat('%',?,'%')";
			searchList.add(search.get("name"));
		}

		// 性别
		if (Util.isNotEmpty(search.get("sex"))) {

			sql += " AND sex=?";
			searchList.add(search.get("sex"));
		}

		// 年龄,从xx起
		if (Util.isNotEmpty(search.get("startAge"))) {
			sql += " AND age >= ? ";
			searchList.add(search.get("startAge"));
		}
		// 年龄，止到xx
		if (Util.isNotEmpty(search.get("endAge"))) {
			sql += " AND age <= ? ";
			searchList.add(search.get("endAge"));
		}

		// 电话
		if (Util.isNotEmpty(search.get("phone"))) {

			sql += " AND phone like concat('%',?,'%')";
			searchList.add(search.get("phone"));
		}

		// 电子邮件
		if (Util.isNotEmpty(search.get("email"))) {

			sql += " AND email like concat('%',?,'%')";
			searchList.add(search.get("email"));
		}

		// 注册时间，从xx起
		if (Util.isNotEmpty(search.get("startRegionTime"))) {
			sql += " AND region_time >= ? ";
			searchList.add(search.get("startRegionTime"));
		}
		// 注册时间，止到xx
		if (Util.isNotEmpty(search.get("endRegionTime"))) {
			sql += " AND region_time <= ? ";
			searchList.add(search.get("endRegionTime"));
		}

		ResultSet rs = jdbcUtil.executeQuery(sql, searchList.toArray());

		try {
			while (rs.next()) {

				Client client = new Client();
				client.setClientId(rs.getInt("client_id"));
				client.setClientName(rs.getString("client_name"));
				client.setClientPwd(rs.getString("client_pwd"));
				client.setName(rs.getString("name"));
				client.setSex(rs.getInt("sex"));
				client.setAge(rs.getInt("age"));
				client.setPhone(rs.getString("phone"));
				client.setEmail(rs.getString("email"));
				client.setIsActive(rs.getInt("is_active"));
				client.setRegionTime( rs.getDate("region_time") );

				list.add(client);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			jdbcUtil.close();

		}

		return list;
	}
	
	
	/**
	 * 修改client表中is_active的值，是否为激活状态
	 * 
	 * @param clientId
	 * @param action
	 * @return
	 */
	public int toggleClientActive(Integer clientId, Integer action) {

		String sql = "UPDATE client SET is_active=?  WHERE client_id=?";

		return jdbcUtil.executeUpdate(sql, action, clientId);

	}
	
	
	

}
