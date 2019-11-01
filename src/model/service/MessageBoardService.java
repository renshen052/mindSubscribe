package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import bean.MessageBoard;
import model.dao.MessageBoardDao;
import utils.ResultDate;
import utils.Util;

/**
 * @author h w j
 * @instruction
 * 留言板Service
 */
public class MessageBoardService {

	MessageBoardDao messageBoardDao = new MessageBoardDao();

	/**
	 * 查询符合条件的留言
	 * 
	 * @param search 查询条件
	 * @return 留言对象集合
	 */
	public List<MessageBoard> listSearch(Map<String, String> search) {

		return messageBoardDao.listSearch(search);
	}

	/**
	 * 切换留言的显示和隐藏，0隐藏，1显示
	 * @param messageBoardId 留言id
	 * @param action 要更新为的值
	 * @param response 响应对象
	 */
	public void toggleMessageBoardActive(String messageBoardId, String action, HttpServletResponse response) {

		int i = messageBoardDao.toggleMessageBoardActive(messageBoardId, action);

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
	 * 根据留言的id查询，并且给出响应
	 * 
	 * @param messageBoardId 留言id
	 * @param response响应对象
	 */
	public void getMessageBoardToResponse(int messageBoardId, HttpServletResponse response) {
		MessageBoard messageBoard = messageBoardDao.getMessageBoard(messageBoardId);

		ResultDate rd = new ResultDate();
		if (messageBoard != null) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("查询成功");
			rd.getDataList().add(messageBoard);

		} else {

			// 查询失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 增加一条留言
	 * @param messageBoard 封装好的留言对象
	 * @param response 响应对象
	 */
	public void addMessageBoard(MessageBoard messageBoard, HttpServletResponse response) {

		int i = messageBoardDao.addMessageBoard(messageBoard);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 留言成功
			rd.setIsSuccess(true);
			rd.setMsg("留言成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 得到最新的num条留言
	 * @param num 留言数量
	 * @return 最新的num条留言对象集合
	 */
	public ArrayList<MessageBoard> getMessageBoardNum(int num) {
		
		return messageBoardDao.getMessageBoardNum(num);
	}

}
