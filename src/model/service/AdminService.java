package model.service;

import bean.Admin;
import model.dao.AdminDao;

public class AdminService {

	AdminDao adminDao = new AdminDao();
	
	/**
	 * 根据账号查询管理员
	 * @return
	 */
	public Admin getAdmin(String adminName) {
		return adminDao.getAdmin(adminName);
	}

	
	/**
	 * 修改密码
	 * @param adminId
	 * @return
	 */
	public int updateAdminPwd(Integer adminId,String newPwd) {
		return adminDao.updateAdminPwd(adminId,newPwd);
	}


	/**
	 * 修改管理员信息，根据管理员id
	 * @param admin
	 * @param adminId
	 * @return
	 */
	public int updateAdminBase(Admin admin, Integer adminId) {
		return adminDao.updateAdminBase( admin, adminId);
	}
}
