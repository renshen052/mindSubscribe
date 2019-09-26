package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Announcement;
import bean.Doctor;
import model.dao.AnnouncmentDao;
import utils.ResultDate;
import utils.Util;

public class AnnouncmentService {

	AnnouncmentDao announcmentDao = new AnnouncmentDao();

	
	/**
	 * 查询符合条件的公告
	 * @param search
	 * @return
	 */
	public List<Announcement> listSearch(Map<String, String> search) {

		return announcmentDao.listSearch(search);
	}


	/*
	 * 切换公告的显示和隐藏，0隐藏，1显示
	 */
	public void toggleDoctorActive(String announcmentId, String action, HttpServletResponse response) {


		int i = announcmentDao.toggleClientActive(announcmentId, action);

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
	 * 根据公告的id查询，公告，并且给出响应
	 * @param parseInt
	 * @param response
	 */
	public void getAnnouncementToResponse(int announcementId, HttpServletResponse response) {
		Announcement announcement = announcmentDao.getAnnouncement(announcementId);

		ResultDate rd = new ResultDate();
		if (announcement != null) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("查询成功");
			rd.getDataList().add(announcement);

		} else {

			// 查询失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);
		
	}


	/**
	 * 增加一条公告
	 * @param announcement
	 */
	public void addAnnouncement(Announcement announcement, HttpServletResponse response) {

		int i = announcmentDao.addAnnouncement(announcement);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 创建公告成功
			rd.setIsSuccess(true);
			rd.setMsg("创建公告成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);
		
	}


	/**
	 * 查询所有显示的公告（最新十条）
	 * @param num
	 * @return
	 */
	public ArrayList<Announcement> getAnnouncmentNum(int num) {
		
		return announcmentDao.getAnnouncmentNum(num);
	}
	
}
