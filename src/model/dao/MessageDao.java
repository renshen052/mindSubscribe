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
		
		sql += " AND sender=? ";
		
		searchList.add(reqeustUserId);
		searchList.add(reqeustUser);
		
		System.out.println(search.toString());

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
	public List<Message> listReceivMessage(Map<String, String> search) {

		return null;
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
}
