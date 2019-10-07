package bean;

import java.util.Date;

public class ClientArchive {

	/**
	 * 咨询记录Id
	 */
	private Integer archivesId;
	
	/**
	 * 本次咨询的，来访者的id
	 */
	private Integer clientId;
	
	/**
	 * 本次咨询的，咨询师的id
	 */
	private Integer doctorId;
	
	/**
	 * 申请人(client)做的问卷 内容
	 */
	private String questionContext;
	
	/**
	 * 评测结果
	 * 0 轻微，1 严重，2 非常严重
	 */
	private Integer level;
	
	/**
	 * 申请时间
	 */
	private Date applyTime;
	
	/**
	 * 申请人期望的咨询地点，最终只是一个参考
	 */
	private String expectPlace;
	
	/**
	 * 申请人期望的咨询时间，最终只是一个参考
	 */
	private String expectTime;
	
	/**
	 * 咨询开始时间，几月几日几时
	 */
	private Date startDatetime;
	
	/**
	 * 咨询结束时间，几月几日几时
	 */
	private Date endDatetime;
	
	
	/**
	 * -1 失败，0 未完成，1 完成
	 */
	private Integer status;
	
	/**
	 * 一次咨询的记录文档，命名：日期_来访者id_咨询师id.xxx
	 */
	private String doc_path;
	
	
	/**
	 * 回访记录内容
	 */
	private String secondQuestionContext;
	
	/**
	 * 是否回访，0否，1是
	 */
	private Integer isSendDo;

	public Integer getArchivesId() {
		return archivesId;
	}

	public void setArchivesId(Integer archivesId) {
		this.archivesId = archivesId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getQuestionnaireContext() {
		return questionContext;
	}

	public void setQuestionnaireContext(String questionContext) {
		this.questionContext = questionContext;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getExpectPlace() {
		return expectPlace;
	}

	public void setExpectPlace(String expectPlace) {
		this.expectPlace = expectPlace;
	}

	public String getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(String expectTime) {
		this.expectTime = expectTime;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDoc_path() {
		return doc_path;
	}

	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}

	public String getSecondQuestionContext() {
		return secondQuestionContext;
	}

	public void setSecondQuestionContext(String secondQuestionContext) {
		this.secondQuestionContext = secondQuestionContext;
	}

	public Integer getIsSendDo() {
		return isSendDo;
	}

	public void setIsSendDo(Integer isSendDo) {
		this.isSendDo = isSendDo;
	}
	
	
	
}
