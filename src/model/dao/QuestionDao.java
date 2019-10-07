package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Doctor;
import bean.Question;
import utils.Util;
import utils.jdbc.JdbcUtil;

public class QuestionDao {
	
	JdbcUtil jdbcUtil = new JdbcUtil();
	
	/**
	 * 添加一个预约问题
	 */
	public int addQuestion(Question question) {
		
		String sql = "INSERT INTO question (question_num, context, answer_yes_score,answer_no_score) ";
		
		sql += "VALUES (?,?,?,?)";

		return jdbcUtil.executeUpdate(sql, question.getQuestionNum(),question.getContext(),
				question.getAnswerYesScore(),question.getAnswerNoScore());
		
	}
	
	/**
	 * 查看所有的问题
	 * @param context 
	 */
	public ArrayList<Question> listQuestion(String context){
		
		ArrayList<Question> list = new ArrayList<Question>();
		
		String sql = "SELECT 	`question_id`, `question_num`, `context`, `answer_yes_score`,`answer_no_score` ";
		
		sql += "FROM `question` WHERE 1=1 ";
		
		ResultSet rs = null;
		
		if(Util.isNotEmpty(context)) {
			
			sql += " AND context like concat('%',?,'%')";
			rs = jdbcUtil.executeQuery(sql,context);
			
		}else {
			rs = jdbcUtil.executeQuery(sql);
		}

		try {
			while (rs.next()) {
				
				Question question = new Question();
				question.setQuestionId(rs.getInt("question_id"));
				question.setQuestionNum(rs.getInt("question_num"));
				question.setContext(rs.getString("context"));
				question.setAnswerYesScore(rs.getInt("answer_yes_score"));
				question.setAnswerNoScore(rs.getInt("answer_no_score"));
				
				
				list.add(question);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			jdbcUtil.close();

		}

		return list;
		
	}

	
	/**
	 * 修改一个预约的问题
	 * @param question
	 * @param response 
	 */
	public int updateQuestion(Question question) {

		String sql = "UPDATE question  ";
		
		sql += " SET question_num=?,context=? ,answer_yes_score=?,answer_no_score=? ";
		
		sql += " WHERE question_id=?";
		
		return jdbcUtil.executeUpdate(sql, question.getQuestionNum(),question.getContext(),question.getAnswerYesScore(),
				question.getAnswerYesScore(),question.getQuestionId());
	}

	
	/**
	 *  删除一个问题
	 * @param questionId
	 * @param response
	 */
	public int deleteQuestion(int questionId) {

		String sql = "DELETE FROM question WHERE question_id=?";
		
		return jdbcUtil.executeUpdate(sql, questionId);
	}

	/**
	 * 查询一个问题
	 * @param questionId
	 * @param response
	 */
	public Question getQuestionByQuestionId(int questionId) {
		
		
		String sql = "SELECT 	`question_id`, `question_num`, `context`, `answer_yes_score`,`answer_no_score` ";
		
		sql += "FROM `question` WHERE question_id=? ";
		
		ResultSet	rs = jdbcUtil.executeQuery(sql,questionId);
		
		Question question = null;

		try {
			while (rs.next()) {
				
				question = new Question();
				question.setQuestionId(rs.getInt("question_id"));
				question.setQuestionNum(rs.getInt("question_num"));
				question.setContext(rs.getString("context"));
				question.setAnswerYesScore(rs.getInt("answer_yes_score"));
				question.setAnswerNoScore(rs.getInt("answer_no_score"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			jdbcUtil.close();

		}

		return question;
		
	}
	
}
