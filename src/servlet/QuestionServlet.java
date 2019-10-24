package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Question;
import model.service.QuestionService;
import utils.Util;

/**
 * @author h w j
 * @instruction
 * 问题模块的控制器
 */
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	QuestionService questionService = new QuestionService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String m = request.getParameter("m");
		
		if ("listQuestion".equals(m)) {

			//得到查询条件
			String context = request.getParameter("context");
			
			
			// 查询符合条件的题目
			List<Question> list = questionService.listQuestion(context);

			
			request.setAttribute("questionList", list);

			request.setAttribute("listSize", list.size());

			request.getRequestDispatcher("/admin/question.jsp").forward(request, response);

		}else if("updateQuestion".equals(m)) {
			
			//增加或者修改
		
			//id有值就是修改，没值就是添加
			String questionId = request.getParameter("id");
			
			
			//取得表单里的值
			String  questionNum = request.getParameter("questionNum");
			String  answerYesScore = request.getParameter("answerYesScore");
			String  answerNoScore = request.getParameter("answerNoScore");
			String  context = request.getParameter("context");

			
			//将表单对象封装为Question对象
			Question question = new Question();
			question.setQuestionNum(Integer.parseInt(questionNum));
			question.setAnswerYesScore(Integer.parseInt(answerYesScore));
			question.setAnswerNoScore(Integer.parseInt(answerNoScore));
			question.setContext(context);
			
			
			if(Util.isNotEmpty(questionId)) {
				//id不为空，是修改
				question.setQuestionId(Integer.parseInt(questionId));
				
				//修改
				questionService.updateQuestion(question,response);
				
			}else {
				//增加
				questionService.addQuestion(question,response);
			}
			
		}else if("deletQuestion".equals(m)) {
			
			//删除
			
			//获取要删除的doctor
			String questionId = request.getParameter("id");
			
			//批量删除的Id
			String checkeds = request.getParameter("checkeds");
			
			//删除单个的
			if(Util.isNotEmpty(questionId)) {
				questionService.deleteQuestion(Integer.parseInt(questionId),response);
			}
			
			//批量删除
			if(Util.isNotEmpty(checkeds)) {
				questionService.deleteQuestionCheckeds(checkeds,response);
			}
			
			
		}else if("selecteQuestion".equals(m)) {//ajax
			
			//要查看的问题
			String questionId = request.getParameter("id");
			
			//查询，并且将数据返回（JSON格式）
			questionService.getQuestionByQuestionIdToResponse(Integer.parseInt(questionId ),response);
			
			
			
		}
	
	}

}
