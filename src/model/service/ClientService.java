package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Client;
import model.dao.ClientDao;
import utils.ResultDate;
import utils.Util;

public class ClientService {
	
	ClientDao clientDao = new ClientDao();

	
	/**
	 * 查询符合条件的用户
	 * @param search
	 * @return
	 */
	public List<Client> listSearch(Map<String, String> search) {

		return clientDao.listSearch(search);
	}
	
	
	/**
	 * 修改client表中is_active的值，是否为激活状态
	 * 
	 * @param clientId
	 * @param action
	 * @return
	 */
	public void toggleClientActive(Integer clientId, Integer action, HttpServletResponse response) {

		int i = clientDao.toggleClientActive(clientId, action);

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
	 * 通过doctor的doctorId 查询doctor,并且做响应
	 * 
	 * @param response
	 * @param parseInt
	 * @return
	 */
	public void getClientByClientIdToResponse(int clientId, HttpServletResponse response) {

		
		
		
	}
	
	
	
	
	
	
	

}
