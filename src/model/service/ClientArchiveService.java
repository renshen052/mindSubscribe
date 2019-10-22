package model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import bean.ClientArchive;
import bean.Doctor;
import bean.Message;
import model.dao.ClientArchiveDao;
import model.dao.MessageDao;
import utils.ResultDate;
import utils.UploadResult;
import utils.Util;
import utils.mail.Mail;
import utils.mail.MailSend;

public class ClientArchiveService {

	ClientArchiveDao clientArchiveDao = new ClientArchiveDao();

	MessageDao messageDao = new MessageDao();

	/**
	 * 查询共有多少条咨询记录
	 * 
	 * @return
	 */
	public int getClientArchiveNum() {
		return clientArchiveDao.getClientArchiveNum();
	}

	/**
	 * 查询来访者正在预约的申请
	 * 
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> onSubList(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId, -1, 1);
	}

	/**
	 * 查询来访者已经完成的预约
	 * 
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> clientConsult(Integer clientId) {
		return clientArchiveDao.listClientArchive(clientId, 2, 3);
	}

	/**
	 * 添加一次申请,并且返回响应
	 * 
	 * @param clientArchive
	 * @param response
	 */
	public void addClientArchive(ClientArchive clientArchive, HttpServletResponse response) {

		int i = clientArchiveDao.addClientArchive(clientArchive);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("提交成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 查询 id为doctorId 的咨询师，接受到的所有申请(未通过的)
	 * 
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> getAllSubFromClient(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId, 0, 0);
	}

	/**
	 * 显示咨询师所有正在咨询中的记录
	 * 
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> subOnList(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId, 1, 2);
	}

	/**
	 * 显示咨询师已经完成的咨询
	 * 
	 * @param doctorId
	 * @return
	 */
	public List<ClientArchive> getSubOk(Integer doctorId) {
		return clientArchiveDao.listDoctorArchive(doctorId, 3, 3);
	}

	/**
	 * 通过archivesId得到ClientArchive对象
	 * 
	 * @param parseInt
	 * @return
	 */
	public ClientArchive getClientArchiveById(int archivesId) {
		return clientArchiveDao.getClientArchiveById(archivesId);
	}

	/**
	 * 驳回申请
	 * 
	 * @param applyTime
	 * @param parseInt
	 * @param i
	 * @param response
	 * @param doctorNow
	 */
	public void updateStatusFalseResponse(int archivesId, int clientId, String applyTime, HttpServletResponse response,
			Doctor doctorNow) {

		int i = clientArchiveDao.updateClientArchiveStuatus(archivesId, -1);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("成功");

			// 发送消息 邮件 给咨询者

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date at = null;
			try {
				at = sdf.parse(applyTime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Message message = new Message();
			message.setSender("admin");
			message.setSenderId(3);
			message.setSenderName("系统(请勿回复)");
			message.setReceiver("client");
			message.setReceiverId(clientId);
			message.setReceiverName("接受系统消息方");
			message.setContext("咨询师：" + doctorNow.getName() + "，取消了您在(" + sdf.format(at) + "时)的申请,如有疑问请与其联系;" + "(邮箱:"
					+ doctorNow.getEmail() + ",电话：" + doctorNow.getPhone() + ")");
			message.setSendTime(new Date());
			message.setIsRead(0);
			
			//发送站内消息
			messageDao.sendMessage(message);
			//发送邮件
			sendMessageEmail(message);

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	
	/**
	 * 发送邮件
	 * @param message 邮件的消息内容，及接受者
	 */
	private void sendMessageEmail(Message message) {

		String email = messageDao.getReceiverEmail(message.getReceiver(),message.getReceiverId());
		
		if(email != null) {
			
			Mail mail = new Mail();
			mail.setTitle("咨询安排");
			mail.setContent(message.getContext());
			mail.setTo(email);
		    boolean result = MailSend.send(mail);
		    
		    if(!result) {
		    	System.out.println("给：" + message.getReceiver() + " " + message.getReceiverId() + ",email:" + email + ",发送的邮件失败");
		    }
			
		}else {
			System.out.println("邮箱不存在" + message.getReceiver() + " " + message.getReceiverId());
		}
		
	}

	/**
	 * 安排咨询，返回结果
	 * 
	 * @param clientArchive
	 * @param response
	 * @param doctorNow
	 */
	public void planSubResponse(ClientArchive clientArchive, HttpServletResponse response, Doctor doctorNow) {

		int i = -999;

		ResultDate rd = new ResultDate();

		if (clientArchive.getStartDatetime().compareTo(clientArchive.getEndDatetime()) < 0) {
			// 符合，开始日期小于结束日期

			i = clientArchiveDao.planSub(clientArchive);
		}

		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("成功");

			// 发送消息 邮件 给申请者

			Message message = new Message();
			message.setSender("admin");
			message.setSenderId(3);
			message.setSenderName("系统(请勿回复)");
			message.setReceiver("client");
			message.setReceiverId(clientArchive.getClientId());
			message.setReceiverName("接受系统消息方");
			message.setContext("咨询师：" + doctorNow.getName() + "，为您安排了咨询，请在\"我的预约\"中查看详情，如有疑问请与其联系;" + "(邮箱:"
					+ doctorNow.getEmail() + ",电话：" + doctorNow.getPhone() + ")");
			message.setSendTime(new Date());
			message.setIsRead(0);
			
			//发送站内消息
			messageDao.sendMessage(message);
			
			//发送邮件
			sendMessageEmail(message);

		} else if (i == -999) {

			// 条件不符合
			rd.setIsSuccess(false);
			rd.setMsg("失败，开始日期不能大于结束日期！");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 完成咨询
	 * 
	 * @param archivesId
	 * @param clientId
	 * @param response
	 * @param doctorNow
	 */
	public void updateStatusFinishResponse(int archivesId, int clientId, HttpServletResponse response,
			Doctor doctorNow) {

		int i = clientArchiveDao.updateClientArchiveStuatus(archivesId, 3);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("成功");

			// 发送消息 邮件 给申请者
			Message message = new Message();
			message.setSender("admin");
			message.setSenderId(3);
			message.setSenderName("系统(请勿回复)");
			message.setReceiver("client");
			message.setReceiverId(clientId);
			message.setReceiverName("接受系统消息方");
			message.setContext("您与：咨询师" + doctorNow.getName() + "的咨询已经完成,如有疑问请与其联系;" + "(邮箱:" + doctorNow.getEmail()
					+ ",电话：" + doctorNow.getPhone() + ")");
			message.setSendTime(new Date());
			message.setIsRead(0);
			
			//发送站内消息
			messageDao.sendMessage(message);
			
			//发送邮件
			sendMessageEmail(message);

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 评价本次咨询
	 * 
	 * @param archivesId
	 * @param context
	 * @param response
	 */
	public void evaluateSub(String archivesId, String context, HttpServletResponse response) {

		int i = clientArchiveDao.evaluateSub(archivesId, context);

		ResultDate rd = new ResultDate();
		if (i == 1) {
			// 成功
			rd.setIsSuccess(true);
			rd.setMsg("评价成功");

		} else {

			// 修改失败
			rd.setIsSuccess(false);
			rd.setMsg("失败，请刷新页面后重试");

		}

		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}

	/**
	 * 咨询记录里的咨询文档
	 * 
	 * @param archivesId
	 * @param uploadResult
	 * @param response
	 */
	public void uploadSubDoc(String archivesId, UploadResult uploadResult, HttpServletResponse response) {

		ResultDate rd = new ResultDate();

		if (uploadResult.isSuccess()) {

			// 得到文件存放路径
			String subDocPath = uploadResult.getLogicFileName();

			int i = clientArchiveDao.uploadSubDoc(archivesId, subDocPath);

			if (i == 1) {
				// 成功
				rd.setIsSuccess(true);

			}

		} else {

			rd.setIsSuccess(false);
			
		}

		rd.setMsg(uploadResult.getMsg());
		
		// 响应，JSON格式数据
		Util.responseJson(rd, response);

	}
}
