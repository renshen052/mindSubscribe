package model.service;

import baen.Admin;
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
}
