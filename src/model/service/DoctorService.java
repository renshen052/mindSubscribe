package model.service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;

import baen.Doctor;
import model.dao.DoctorDao;
import utils.ResultDate;

public class DoctorService {

	DoctorDao doctorDao = new DoctorDao();
	
	public List<Doctor> listAll() {
		return doctorDao.listAll();
	}

	
	/**
	 * 修改doctor表中is_active的值，是否为激活状态
	 * 
	 * @param doctorId
	 * @param action
	 * @return
	 */
	public void toggleDoctorActive(String doctorId, String action, HttpServletResponse response) {
		
		int i = doctorDao.toggleDoctorActive(doctorId,action);
		
		ResultDate rd = new ResultDate();
		if(i == 1) {
			//修改成功
			rd.setIsSuccess(true);
			rd.setMsg("修改成功");
			
		}else {
			
			//修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");
			
		}
		
		//设置响应格式
		response.setContentType("application/jsonj; charset=utf-8");
		
		
		try(
				Writer witer = response.getWriter();
				){
			
			
			JSONUtil.serialize(witer, rd);
			
			
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<Doctor> listSearch(Map<String, String> search) {
		
		return doctorDao.listSearch(search);
	}

	
	/**
	 * 根据doctorId 删除 doctor
	 * @param doctorId
	 * @param response
	 */
	public void deleteDoctor(String doctorId, HttpServletResponse response) {

		
		int i = doctorDao.deleteDoctor(doctorId);
		
		ResultDate rd = new ResultDate();
		if(i == 1) {
			//修改成功
			rd.setIsSuccess(true);
			rd.setMsg("删除成功");
			
		}else {
			
			//修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");
			
		}
		
		//设置响应格式
		response.setContentType("application/jsonj; charset=utf-8");
		
		
		try(
				Writer witer = response.getWriter();
				){
			
			
			JSONUtil.serialize(witer, rd);
			
			
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
