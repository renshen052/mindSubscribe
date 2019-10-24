package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Admin;
import utils.jdbc.JdbcUtil;

/**
 * @author h w j
 * @instruction
 * 操作管理员表admin，的DAO
 */
public class AdminDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	
	
	/**
	 * 根据账号查询管理员(或者电话号码)
	 * @param adminName 管理员账号
	 * @return adminName 查询到的管理员
	 */
	public Admin getAdmin(String adminName) {
		
		Admin admin = null;
		
		String sql = "SELECT * FROM admin WHERE admin_name=? OR phone=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, adminName,adminName);
		
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
	 * 修改管理员密码
	 * @param adminId 管理员id
	 * @param newPwd  新密码
	 * @return 执行update后受影响行数
	 */
	public int updateAdminPwd(Integer adminId,String newPwd) {

		String sql = "UPDATE admin SET admin_pwd=? WHERE admin_id=?";
		
		return jdbcUtil.executeUpdate(sql, newPwd,adminId);
		
	}


	/**
	 * 修改管理员信息，根据管理员id
	 * @param admin 管理员的新信息
	 * @param adminId 管理员id
	 * @return 执行update后受影响行数
	 */
	public int updateAdminBase(Admin admin, Integer adminId) {
		
		String sql = "UPDATE admin SET NAME=?,sex=?,age=?,phone=?,email=? WHERE admin_id=?";
		
		return jdbcUtil.executeUpdate(sql, admin.getName(),admin.getSex(),admin.getAge(),admin.getPhone(),admin.getEmail(),adminId);
		
	}

	/**
	 * 根据Id查询管理员个人信息
	 * 
	 * @param adminId 管理员id
	 * @return 查询到的管理员对象
	 */
	public Admin getAdminById(Integer adminId) {
		
		Admin admin = null;
		
		String sql = "SELECT * FROM admin WHERE admin_id=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, adminId);
		
		try {
			if(rs.next()) {
				
				admin = new Admin();
				
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setAdminName(rs.getString("admin_name"));
				//admin.setAdminPwd(rs.getString("admin_pwd"));
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
	
	
}
