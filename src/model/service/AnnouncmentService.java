package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Doctor;
import model.dao.AnnouncmentDao;

public class AnnouncmentService {

	AnnouncmentDao announcmentDao = new AnnouncmentDao();

	
	/**
	 * 查询符合条件的公告
	 * @param search
	 * @return
	 */
	public List<Doctor> listSearch(Map<String, String> search) {
		// TODO Auto-generated method stub
		return null;
	}


	/*
	 * 切换公告的显示和隐藏，0隐藏，1显示
	 */
	public void toggleDoctorActive(String doctorId, String action, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}


	/**
	 * 根据公告的id查询，公告，并且给出响应
	 * @param parseInt
	 * @param response
	 */
	public void getAnnouncmentToResponse(int parseInt, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
