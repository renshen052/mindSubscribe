package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Client;
import model.dao.ClientDao;
import utils.ResultDate;
import utils.Util;

/**
 * @author h w j
 * @instruction
 * 来访者的Service
 */
public class ClientService {
	
	ClientDao clientDao = new ClientDao();

	
	/**
	 * 查询符合条件的用户
	 * @param search 查询条件
	 * @return 来访者对象集合
	 */
	public List<Client> listSearch(Map<String, String> search) {

		return clientDao.listSearch(search);
	}
	
	
	/**
	 * 修改client表中is_active的值，是否为激活状态
	 * 
	 * @param clientId 来访者id
	 * @param action 要设置的状态值
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
	 * 查询 共有多少来访者
	 * @return 来访者数量
	 */
	public int getClientNum() {
		return clientDao.getClientNum();
	}


	/**
	 * 根据账号查询 来访者（支持电话）
	 * @param clientName 账号
	 * @return 来访者对象
	 */
	public Client getClient(String clientName) {
		
		return clientDao.getClient(clientName);
	
	}


	/**
	 * 修改来访者密码
	 * @param clientId 来访者id
	 * @param newPwd 新密码
	 * @return 受影响行数
	 */
	public int updateClientPwd(Integer clientId, String newPwd) {

		return clientDao.updateClientPwd(clientId,newPwd);
	}


	/**
	 * 修改来访者的个人信息
	 * @param client 来访者对象
	 * @param clientId 来访者id
	 * @return 受影响行数
	 */
	public int updateClientBase(Client client, Integer clientId) {
		return clientDao.updateClientBase(client,clientId);
	}


	/**
	 * 得到来访者
	 * @param clientId 来访者id
	 * @return 来访者对象
	 */
	public Client getClientByClientId(int clientId) {
		return clientDao.getClientByClientId(clientId);
	}


	/**
	 * 判断电话是否可用（是否被注册过）
	 * @param phone 电话
	 * @param response 响应对象
	 */
	public void checkPhoneResponse(String phone, HttpServletResponse response) {

		Client client = clientDao.getClient(phone);

		ResultDate rd = new ResultDate();
		if (client == null) {
			// 可用
			rd.setIsSuccess(true);
			rd.setMsg("电话号码可用");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("已经被注册过");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}


	/**
	 * 添加一来访者
	 * @param client 来访者对象
	 */
	public int addClient(Client client) {

		return clientDao.addClient(client);
		
	}

}
