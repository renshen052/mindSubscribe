package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Client;
import utils.Util;
import utils.jdbc.JdbcUtil;

/**
 * @author h w j
 * @instruction
 * client 对应的dao
 */
public class ClientDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询符合条件的用户
	 * @param search 查询条件
	 * @return 来访者对象集合
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
	 * @param clientId 来访者id
	 * @param action 要设置的状态值
	 */
	public int toggleClientActive(Integer clientId, Integer action) {

		String sql = "UPDATE client SET is_active=?  WHERE client_id=?";

		return jdbcUtil.executeUpdate(sql, action, clientId);

	}


	/**
	 * 查询 共有多少注册的来访者
	 * @return 来访者数量
	 */
	public int getClientNum() {

		String sql = "SELECT COUNT(1) FROM client;";
		
		ResultSet rs = jdbcUtil.executeQuery(sql);
		
		int num = 0;
		
		try {
			if(rs.next()) {
				
				num = rs.getInt("count(1)");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			jdbcUtil.close();
		}
		
		return num;
	}


	/**
	 * 根据账号查询 来访者（支持电话）
	 * @param clientName 账号
	 * @return 来访者对象
	 */
	public Client getClient(String clientName) {
		
		Client client = null;
		
		String sql = "SELECT * FROM client WHERE client_name=? OR phone=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, clientName,clientName);

		try {
			while (rs.next()) {

				client = new Client();
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

		return client;
	}


	/**
	 * 修改来访者密码
	 * @param clientId 来访者id
	 * @param newPwd 新密码
	 * @return 受影响行数
	 */
	public int updateClientPwd(Integer clientId, String newPwd) {

		String sql = "UPDATE client SET client_pwd=? WHERE client_id=?";
		
		return jdbcUtil.executeUpdate(sql, newPwd,clientId);
		
	}


	/**
	 * 修改client表
	 * @param client 来访者对象
	 * @param clientId 来访者id
	 * @return 受影响行数
	 */
	public int updateClientBase(Client client, Integer clientId) {
		
		String sql = "UPDATE client SET name=?,sex=?,age=?,phone=?,email=? WHERE client_id=?";
		
		return jdbcUtil.executeUpdate(sql, client.getName(),client.getSex(),client.getAge(),client.getPhone(),client.getEmail(),clientId);
		
	}


	/**
	 * 得到来访者
	 * @param clientId 来访者id
	 * @return 来访者对象
	 */
	public Client getClientByClientId(int clientId) {
		
		Client client = null;
		
		String sql = "SELECT * FROM client WHERE client_id=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, clientId);

		try {
			while (rs.next()) {

				client = new Client();
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

		return client;
	}


	/**
	 * 添加一来访者 
	 * @param client 来访者对象
	 * @return 受影响行数
	 */
	public int addClient(Client client) {
		
		String sql = "INSERT INTO client ( `client_name`, `client_pwd`, `name`, `sex`, ";
		
		sql += "`age`, `phone`, `email`, `is_active`, `region_time`)";
		
		sql += "values (?,?,?,?,?,?,?,?,?)";
		
		return jdbcUtil.executeUpdate(sql, client.getClientName(),client.getClientPwd(),client.getName(),
				client.getSex(),client.getAge(),client.getPhone(),client.getEmail(),client.getIsActive(),
				client.getRegionTime());
		
	}

}
