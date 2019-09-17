package test;

import java.sql.SQLException;

import model.dao.AdminDao;

public class TestJDBCPool {
	public static void main(String[] args) {
		
		
		try {
			utils.jdbc.Pool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接成功");
		
		System.out.println("执行：" + new AdminDao().getAdmin("root").toString());
		
	}
}
