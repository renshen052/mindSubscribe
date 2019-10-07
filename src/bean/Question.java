package bean;

public class Question {

	/**
	 * 一个问卷题目的id
	 */
	private Integer questionId;
	
	/**
	 * 题目显示顺序
	 */
	private Integer questionNum;
	
	/**
	 * 题目内容
	 */
	private String context;
	
	/**
	 * 题目回答是的分值
	 */
	private Integer answerYesScore;
	
	/**
	 * 题目回答否的分值
	 */
	private Integer answerNoScore;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getAnswerYesScore() {
		return answerYesScore;
	}

	public void setAnswerYesScore(Integer answerYesScore) {
		this.answerYesScore = answerYesScore;
	}

	public Integer getAnswerNoScore() {
		return answerNoScore;
	}

	public void setAnswerNoScore(Integer answerNoScore) {
		this.answerNoScore = answerNoScore;
	}
	
}
