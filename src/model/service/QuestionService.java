package model.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import bean.Doctor;
import bean.Question;
import model.dao.QuestionDao;
import utils.ResultDate;
import utils.Util;

public class QuestionService {

	QuestionDao questionDao = new QuestionDao();
	
	/**
	 * 添加一个预约问题
	 * @param response 
	 */
	public void addQuestion(Question question, HttpServletResponse response) {

		int i = questionDao.addQuestion(question);		

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 修改成功
			rd.setIsSuccess(true);
			rd.setMsg("添加成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);
	}
	
	/**
	 * 查看所有的问题
	 * @param context 
	 */
	public ArrayList<Question> listQuestion(String context){
		
		return questionDao.listQuestion(context);		
	}

	
	/**
	 * 修改一个预约的问题
	 * @param question
	 * @param response 
	 */
	public void updateQuestion(Question question, HttpServletResponse response) {

		int i = questionDao.updateQuestion(question);

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
	 * 查询一个问题，并且返回响应
	 * @param questionId
	 * @param response
	 */
	public void getQuestionByQuestionIdToResponse(int questionId, HttpServletResponse response) {

		Question question = questionDao.getQuestionByQuestionId(questionId);

		ResultDate rd = new ResultDate();
		if (question != null) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("查询成功");
			rd.getDataList().add(question);

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 *  删除一个问题
	 * @param questionId
	 * @param response
	 */
	public void deleteQuestion(int questionId, HttpServletResponse response) {

		int i = questionDao.deleteQuestion(questionId);

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
	 * 删除所有选中的问题
	 * @param checkeds
	 * @param response
	 */
	public void deleteQuestionCheckeds(String checkeds, HttpServletResponse response) {

		String[] checkedStrs = checkeds.split(",");

		int i = 0;

		for (String checked : checkedStrs) {

			if (questionDao.deleteQuestion(Integer.parseInt(checked)) == 1) {
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
	
	
	
}
