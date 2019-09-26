package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import utils.jdbc.JdbcUtil;

public class ClientArchiveDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询共有多少条咨询记录
	 * 
	 * @return
	 */
	public int getClientArchiveNum() {
		
		String sql = "SELECT COUNT(1) FROM client_archive";
		
		ResultSet rs = jdbcUtil.executeQuery(sql);

		int num = 0;

		try {
			if (rs.next()) {

				num = rs.getInt("count(1)");

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

		return num;
	}
}
