package model.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.Message;
import model.dao.MessageDao;
import utils.ResultDate;
import utils.Util;

public class MessageService {

	
	MessageDao messageDao = new MessageDao();

	
	/**
	 * 查询 用户 已经发送的消息（即用户发送的）
	 * @param search
	 * @param reqeustUser 
	 * @param reqeustUserId 
	 * @return
	 */
	public List<Message> listSendMessage(Map<String, String> search, String reqeustUser, Integer reqeustUserId) {
		
		return messageDao.listSendMessage(search,reqeustUser,reqeustUserId);
	}


	/**
	 * 查询 别人给 用户 发送的消息（即用户接受到的）
	 * @param search
	 * @return
	 */
	public List<Message> listReceivMessage(Map<String, String> search) {

		return messageDao.listReceivMessage(search);
	}


	/**
	 * 发送一条消息
	 * @param message
	 * @param response
	 */
	public void sendMessage(Message message, HttpServletResponse response) {


		int i = messageDao.sendMessage(message);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("发送成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	
	}
}
