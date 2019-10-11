package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	
	/**
	 * 通过questionIds来返回一个json字符串（包括用户回答内容，题目）
	 * @param questionIds
	 * @param request
	 * @return
	 * //题目内容context，是的分值answer_yes_score，否的分值answer_no_score，
			//clientSelected用户选项的分值
	 */
	public HashMap<String,String> getJSON(String questionIds, HttpServletRequest request) {
		
		HashMap<String,String> mapJsonLevel = new HashMap<String,String>();
		
		String[] questions = questionIds.split(",");
		
		//计算分值
		Integer level = 0;
		
		//构造JSON
		StringBuilder sb = new StringBuilder();
		
		sb.append("{");
		
		for(String id : questions) {
			
			String questionId = id;
			
			String context = request.getParameter("context" + id);
			
			String answer_yes_score = request.getParameter("answer_yes_score" + id);
			
			String answer_no_score = request.getParameter("answer_no_score" + id);
			
			String clientSelected = request.getParameter(id);
			
			level += Integer.parseInt(clientSelected);
			
			
			sb.append("\"" + questionId + "\":{");
			sb.append("\"context\":\"" + context + "\",");
			sb.append("\"answer_yes_score\":\"" + answer_yes_score + "\",");
			sb.append("\"answer_no_score\":\"" + answer_no_score + "\",");
			sb.append("\"clientSelected\":\"" + clientSelected + "\"");
			sb.append("},");
			
		}
		
		//删掉多余的","号
		sb.deleteCharAt(sb.length()-1);
		
		sb.append("}");
		
		mapJsonLevel.put("JSON", sb.toString());
		mapJsonLevel.put("level", level+"");
		
		return mapJsonLevel;
	}
	
	
	
}
