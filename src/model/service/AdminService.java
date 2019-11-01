package model.service;

import bean.Admin;
import model.dao.AdminDao;

/**
 * @author h w j
 * @instruction
 * 管理员的Service
 */
public class AdminService {

	AdminDao adminDao = new AdminDao();

	/**
	 * 根据账号查询管理员(或者电话号码)
	 * @param adminName 管理员账号
	 * @return adminName 查询到的管理员
	 */
	public Admin getAdmin(String adminName) {
		return adminDao.getAdmin(adminName);
	}

	/**
	 * 修改管理员密码
	 * @param adminId 管理员id
	 * @param newPwd  新密码
	 * @return 执行update后受影响行数
	 */
	public int updateAdminPwd(Integer adminId, String newPwd) {
		return adminDao.updateAdminPwd(adminId, newPwd);
	}

	/**
	 * 修改管理员信息，根据管理员id
	 * @param admin 管理员的新信息
	 * @param adminId 管理员id
	 * @return 执行update后受影响行数
	 */
	public int updateAdminBase(Admin admin, Integer adminId) {
		return adminDao.updateAdminBase(admin, adminId);
	}

	/**
	 * 根据Id查询管理员个人信息
	 * 
	 * @param adminId 管理员id
	 * @return 查询到的管理员对象
	 */
	public Admin getAdminById(Integer adminId) {
		return adminDao.getAdminById(adminId);
	}
}
