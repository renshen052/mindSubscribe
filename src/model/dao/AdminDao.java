package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Admin;
import utils.jdbc.JdbcUtil;

public class AdminDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	
	
	/**
	 * 根据账号查询管理员
	 * @return
	 */
	public Admin getAdmin(String adminName) {
		
		Admin admin = null;
		
		String sql = "SELECT * FROM admin WHERE admin_name=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, adminName);
		
		try {
			if(rs.next()) {
				
				admin = new Admin();
				
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setAdminName(rs.getString("admin_name"));
				admin.setAdminPwd(rs.getString("admin_pwd"));
				admin.setName(rs.getString("name"));
				admin.setSex(rs.getInt("sex"));
				admin.setAge(rs.getInt("age"));
				admin.setPhone(rs.getString("phone"));
				admin.setEmail(rs.getString("email"));
				admin.setIs_active(rs.getInt("is_active"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			jdbcUtil.close();
			
		}
		
		return admin;
	}


	/**
	 * 修改密码
	 * @param adminId
	 * @return
	 */
	public int updateAdminPwd(Integer adminId,String newPwd) {

		String sql = "UPDATE admin SET admin_pwd=? WHERE admin_id=?";
		
		return jdbcUtil.executeUpdate(sql, newPwd,adminId);
		
	}


	/**
	 * 修改管理员信息，根据管理员id
	 * @param admin
	 * @param adminId
	 * @return
	 */
	public int updateAdminBase(Admin admin, Integer adminId) {
		
		String sql = "UPDATE admin SET NAME=?,sex=?,age=?,phone=?,email=? WHERE admin_id=?";
		
		return jdbcUtil.executeUpdate(sql, admin.getName(),admin.getSex(),admin.getAge(),admin.getPhone(),admin.getEmail(),adminId);
		
	}
	
	
}
