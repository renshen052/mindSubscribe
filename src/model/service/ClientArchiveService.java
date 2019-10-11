package model.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import bean.ClientArchive;
import model.dao.ClientArchiveDao;
import utils.ResultDate;
import utils.Util;

public class ClientArchiveService {

	ClientArchiveDao clientArchiveDao = new ClientArchiveDao();

	/**
	 * 查询共有多少条咨询记录
	 * @return
	 */
	public int getClientArchiveNum() {
		return clientArchiveDao.getClientArchiveNum();
	}

	/**
	 * 查询来访者正在预约的申请
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> onSubList(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId,0,1);
	}

	
	/**
	 * 查询来访者已经完成的预约
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> clientConsult(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId,2,3);
	}

	/**
	 * 添加一次申请,并且返回响应
	 * @param clientArchive
	 * @param response 
	 */
	public void addClientArchive(ClientArchive clientArchive, HttpServletResponse response) {
		

		int i = clientArchiveDao.addClientArchive(clientArchive);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("提交成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);
		
		
		
		
	}

	/**
	 * 查询 id为doctorId 的咨询师，接受到的所有申请(未通过的)
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> getAllSubFromClient(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId,0,0);
	}

	
	/**
	 * 显示咨询师所有正在咨询中的记录
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> subOnList(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId,1,2);
	}

	
	/**
	 * 显示咨询师已经完成的咨询
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> getSubOk(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId,3,3);
	}
}
