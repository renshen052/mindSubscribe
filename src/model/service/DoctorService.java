package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Doctor;
import model.dao.DoctorDao;
import utils.ResultDate;
import utils.Util;

/**
 * @author h w j
 * @instruction
 * 咨询师Service
 */
public class DoctorService {

	DoctorDao doctorDao = new DoctorDao();

	/**
	 * 修改doctor表中is_active的值，是否为激活状态
	 * 
	 * @param doctorId 咨询师id
	 * @param action 要更新的值
	 */
	public void toggleDoctorActive(String doctorId, String action, HttpServletResponse response) {

		int i = doctorDao.toggleDoctorActive(doctorId, action);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 修改成功
			rd.setIsSuccess(true);
			rd.setMsg("修改成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 查询符合条件的咨询师
	 * @param search 查询条件
	 * @return 咨询师对象集合
	 */
	public List<Doctor> listSearch(Map<String, String> search) {

		return doctorDao.listSearch(search);
	}

	/**
	 * 根据doctorId 删除 doctor
	 * 
	 * @param doctorId 咨询师id
	 * @param response 响应对象
	 */
	public void deleteDoctor(Integer doctorId, HttpServletResponse response) {

		int i = doctorDao.deleteDoctor(doctorId);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 修改成功
			rd.setIsSuccess(true);
			rd.setMsg("删除成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 通过doctor的doctorId 查询doctor,并且做响应
	 * @param doctorId 咨询师id
	 * @param response 响应对象
	 */
	public void getDoctorByDoctorIdToResponse(int doctorId, HttpServletResponse response) {

		Doctor doctor = doctorDao.getDoctorByDoctorId(doctorId);

		ResultDate rd = new ResultDate();
		if (doctor != null) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("查询成功");
			rd.getDataList().add(doctor);

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 修改Doctor
	 * 
	 * @param doctor 要更新的咨询师对象
	 * @return 受影响行数
	 */
	public int updateDoctor(Doctor doctor) {

		return doctorDao.updateDoctor(doctor);
	}

	/**
	 * 增加一个Doctor
	 * 
	 * @param doctor 要添加的咨询师对象
	 * @return 受影响行数
	 */
	public int addDoctor(Doctor doctor) {

		// 增加的时候分配 账号和密码

		// 随机账号
		doctor.setDoctorName(Util.generateRandomNum(9));

		// 默认密码123456
		doctor.setDoctorPwd("123456");

		return doctorDao.addDoctor(doctor);
	}

	/**
	 * 删除多个Doctor
	 * 
	 * @param checkeds 要删除的doctorId(1,2,3,4)使用","分隔
	 * @param response 响应对象
	 */
	public void deleteDoctorCheckeds(String checkeds, HttpServletResponse response) {

		String[] checkedStrs = checkeds.split(",");

		int i = 0;

		for (String checked : checkedStrs) {

			if (doctorDao.deleteDoctor(Integer.parseInt(checked)) == 1) {
				i++;
			}

		}

		ResultDate rd = new ResultDate();
		if (i == checkedStrs.length) {
			rd.setIsSuccess(true);
			rd.setMsg("删除成功");
		} else {

			rd.setIsSuccess(false);
			rd.setMsg("删除失败");
		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 查询咨询师总数
	 * @return 咨询师数量
	 */
	public int getDoctorNum() {
		
		return doctorDao.getDoctorNum();
	}

	/**
	 * 根据doctorId查询doctor
	 * @param doctorId 咨询师id
	 * @return 咨询师对象
	 */
	public Doctor getDoctorById(int doctorId) {
		return doctorDao.getDoctorByDoctorId(doctorId);
	}

	/**
	 * 通过doctorName获得咨询师(支持电话登录)
	 * @param doctorName 咨询师账号
	 * @return 咨询师对象
	 */
	public Doctor getDoctor(String doctorName) {
		return doctorDao.getDoctor(doctorName);
	}

	/**
	 * 修改咨询师密码
	 * @param doctorId 咨询师id
	 * @param newPwd 新密码
	 * @return 受影响行数
	 */
	public int updateDoctorPwd(Integer doctorId, String newPwd) {
		return doctorDao.updateDoctorPwd(doctorId,newPwd);
	}

}
