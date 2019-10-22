package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Message;
import utils.Util;
import utils.jdbc.JdbcUtil;

public class MessageDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询 用户 已经发送的消息（即用户发送的）
	 * 
	 * @param search
	 * @param reqeustUser
	 * @param reqeustUserId 
	 * @return
	 */
	public List<Message> listSendMessage(Map<String, String> search, String reqeustUser, Integer reqeustUserId) {

		List<Message> list = new ArrayList<Message>();

		List<Object> searchList = new ArrayList<Object>();

		String sql = "SELECT * FROM message m ";

		sql += "WHERE sender_id=? ";
		
		sql += " AND sender=? ORDER BY send_time DESC;";
		
		searchList.add(reqeustUserId);
		searchList.add(reqeustUser);

		// 接收人身份
		if (Util.isNotEmpty(search.get("receiver"))) {

			sql += " AND receiver=?";
			searchList.add(search.get("receiver"));
		}

		// 接收人名字
		if (Util.isNotEmpty(search.get("receiverName"))) {

			sql += " AND receiver_name like concat('%',?,'%')";
			searchList.add(search.get("receiverName"));
		}

		// 发送时间，从xx起
		if (Util.isNotEmpty(search.get("startSendTime"))) {
			sql += " AND send_time >= ? ";
			searchList.add(search.get("startSendTime"));
		}
		// 注册时间，止到xx
		if (Util.isNotEmpty(search.get("endSendTime"))) {
			sql += " AND send_time <= ? ";
			searchList.add(search.get("endSendTime"));
		}

		// 是否已读
		if (Util.isNotEmpty(search.get("isRead"))) {

			sql += " AND is_read=?";
			searchList.add(search.get("isRead"));
		}

		// 消息内容
		if (Util.isNotEmpty(search.get("context"))) {

			sql += " AND context like concat('%',?,'%')";
			searchList.add(search.get("context"));
		}

		
		ResultSet rs = jdbcUtil.executeQuery(sql, searchList.toArray());
		

		try {
			while (rs.next()) {

				Message message = new Message();
				message.setMessageId(rs.getInt("message_id"));
				message.setSender(rs.getString("sender"));
				message.setSenderId(rs.getInt("sender_id"));
				message.setReceiver(rs.getString("receiver"));
				message.setReceiverId(rs.getInt("receiver_id"));
				message.setSendTime(rs.getTimestamp("send_time"));
				message.setIsRead(rs.getInt("is_read"));
				message.setContext(rs.getString("context"));
				
				message.setReceiverName(rs.getString("receiver_name"));
				
				list.add(message);

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
	 * 查询 别人给 用户 发送的消息（即用户接受到的）
	 * 
	 * @param search
	 * @return
	 */
	public List<Message> listReceivMessage(Map<String, String> search, String reqeustUser, Integer reqeustUserId) {

		List<Message> list = new ArrayList<Message>();

		List<Object> searchList = new ArrayList<Object>();

		String sql = "SELECT * FROM message m ";

		sql += "WHERE receiver_id=? ";
		
		sql += " AND receiver=?  ORDER BY send_time DESC";
		
		searchList.add(reqeustUserId);
		searchList.add(reqeustUser);
		

		// 发送人身份
		if (Util.isNotEmpty(search.get("sender"))) {

			sql += " AND sender=?";
			searchList.add(search.get("sender"));
		}

		// 发送人名字
		if (Util.isNotEmpty(search.get("senderName"))) {

			sql += " AND sender_name like concat('%',?,'%')";
			searchList.add(search.get("senderName"));
		}

		// 发送时间，从xx起
		if (Util.isNotEmpty(search.get("startSendTime"))) {
			sql += " AND send_time >= ? ";
			searchList.add(search.get("startSendTime"));
		}
		// 注册时间，止到xx
		if (Util.isNotEmpty(search.get("endSendTime"))) {
			sql += " AND send_time <= ? ";
			searchList.add(search.get("endSendTime"));
		}

		// 是否已读
		if (Util.isNotEmpty(search.get("isRead"))) {

			sql += " AND is_read=?";
			searchList.add(search.get("isRead"));
		}

		// 消息内容
		if (Util.isNotEmpty(search.get("context"))) {

			sql += " AND context like concat('%',?,'%')";
			searchList.add(search.get("context"));
		}

		
		ResultSet rs = jdbcUtil.executeQuery(sql, searchList.toArray());
		

		try {
			while (rs.next()) {

				Message message = new Message();
				message.setMessageId(rs.getInt("message_id"));
				message.setSender(rs.getString("sender"));
				message.setSenderId(rs.getInt("sender_id"));
				message.setSenderName(rs.getString("sender_name"));
				message.setReceiver(rs.getString("receiver"));
				message.setReceiverId(rs.getInt("receiver_id"));
				message.setSendTime(rs.getTimestamp("send_time"));
				message.setIsRead(rs.getInt("is_read"));
				message.setContext(rs.getString("context"));
				
				message.setReceiverName(rs.getString("receiver_name"));
				
				list.add(message);

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
	 * 发送一条消息
	 * @param message
	 * @param response
	 */
	public int sendMessage(Message message) {

		String sql = "INSERT INTO message ";
		
		sql += " ( `sender`, `sender_id`, `sender_name`, `receiver`, `receiver_id`, `receiver_name`, `send_time`, `is_read`, `context`) ";
		
		sql +=" VALUES (?,?,?,?,?,?,?,?,?)";
		
		return jdbcUtil.executeUpdate(sql,message.getSender(),message.getSenderId(),message.getSenderName(),
				message.getReceiver(),message.getReceiverId(),message.getReceiverName(),message.getSendTime(),
				message.getIsRead(),message.getContext());
	
	}

	/**
	 * 切换消息为已读状态
	 * @param string
	 */
	public int toggleIsRead(int messageId) {
		
		String sql = "UPDATE `message` SET `is_read` = 1 WHERE `message_id` =?";
		
		return jdbcUtil.executeUpdate(sql, messageId);

	}

	
	/**
	 * 查询所有未读的消息（最新的num条）
	 * @param i
	 * @param adminId
	 * @return
	 */
	public ArrayList<Message> getMessageNum(int num, Integer reqeustUserId,String requestUser) {

		ArrayList<Message> list = new ArrayList<Message>();

		String sql = "SELECT * FROM message m ";

		sql += "WHERE receiver_id=? ";
		
		sql += " AND receiver=? ";
		
		sql += " AND is_read=0 ";
		
		sql += " ORDER BY send_time DESC ";
		
		sql += " LIMIT ?";

		
		ResultSet rs = jdbcUtil.executeQuery(sql, reqeustUserId,requestUser,num);
		

		try {
			while (rs.next()) {

				Message message = new Message();
				message.setMessageId(rs.getInt("message_id"));
				message.setSender(rs.getString("sender"));
				message.setSenderId(rs.getInt("sender_id"));
				message.setSenderName(rs.getString("sender_name"));
				message.setReceiver(rs.getString("receiver"));
				message.setReceiverId(rs.getInt("receiver_id"));
				message.setSendTime(rs.getTimestamp("send_time"));
				message.setIsRead(rs.getInt("is_read"));
				message.setContext(rs.getString("context"));
				
				message.setReceiverName(rs.getString("receiver_name"));
				
				list.add(message);

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
	 * 查询所有未读消息数量
	 * @param object
	 * @param object2
	 * @return
	 */
	public int newMessageNum(String user, Integer userId) {

		int num = -1;

		String sql = "SELECT COUNT(1) FROM  message  WHERE receiver=? AND receiver_id=? AND is_read=0";

		
		ResultSet rs = jdbcUtil.executeQuery(sql, user,userId);

		try {
			if (rs.next()) {

				num = rs.getInt(1);

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

		return num;

	}

	
	/**
	 * 查询接受者的Email
	 * @param receiver
	 * @param receiverId
	 * @return 邮箱
	 */
	public String getReceiverEmail(String receiver, Integer receiverId) {

		String email = null;
		
		String sql = null;
		
		if("admin".equals(receiver)) {
			
			sql = "SELECT email FROM  admin  WHERE admin_id=?";
			
		}else if("client".equals(receiver)) {
			
			sql = "SELECT email FROM  client  WHERE client_id=?";
			
		}else if("doctor".equals(receiver)) {
			
			sql = "SELECT email FROM  doctor  WHERE doctor_id=?";
			
		}else {
			return email;
		}
		
		System.out.println("得到接受者的Email：" + sql);
		
		ResultSet rs = jdbcUtil.executeQuery(sql, receiverId);

		try {
			if (rs.next()) {

				email = rs.getString(1);

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

		return email;
	}
}
