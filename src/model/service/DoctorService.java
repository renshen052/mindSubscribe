package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Doctor;
import model.dao.DoctorDao;
import utils.ResultDate;
import utils.Util;

public class DoctorService {

	DoctorDao doctorDao = new DoctorDao();

	/**
	 * 修改doctor表中is_active的值，是否为激活状态
	 * 
	 * @param doctorId
	 * @param action
	 * @return
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
	 * @param search
	 * @return
	 */
	public List<Doctor> listSearch(Map<String, String> search) {

		return doctorDao.listSearch(search);
	}

	/**
	 * 根据doctorId 删除 doctor
	 * 
	 * @param doctorId
	 * @param response
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
	 * 
	 * @param response
	 * @param parseInt
	 * @return
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
	 * @param doctor
	 */
	public int updateDoctor(Doctor doctor) {

		return doctorDao.updateDoctor(doctor);
	}

	/**
	 * 增加一个Doctor
	 * 
	 * @param doctor
	 * @return
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
	 * @param checkeds
	 * @param response
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
	 * @return
	 */
	public int getDoctorNum() {
		
		return doctorDao.getDoctorNum();
	}

	/**
	 * 根据doctorId查询doctor
	 * @param parseInt
	 * @return
	 */
	public Doctor getDoctorById(int doctorId) {
		return doctorDao.getDoctorByDoctorId(doctorId);
	}

}
