package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bean.Admin;
import bean.Announcement;
import bean.Doctor;
import utils.Util;
import utils.jdbc.JdbcUtil;

public class AnnouncmentDao {

	JdbcUtil jdbcUtil = new JdbcUtil();
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	/**
	 * 查询符合条件的公告
	 * 
	 * @param search
	 * @return
	 */
	public List<Announcement> listSearch(Map<String, String> search) {

		ArrayList<Announcement> list = new ArrayList<>();

		List<Object> searchList = new ArrayList<Object>();

		String sql = "SELECT * FROM announcement an LEFT JOIN admin ad ON an.creater_id=ad.admin_id WHERE 1=1 ";

		// 创建者
		if (Util.isNotEmpty(search.get("creater"))) {

			sql += " AND ad.name like concat('%',?,'%')";
			searchList.add(search.get("creater"));
		}

		// 标题
		if (Util.isNotEmpty(search.get("title"))) {

			sql += " AND an.title like concat('%',?,'%')";
			searchList.add(search.get("title"));
		}

		// 内容
		if (Util.isNotEmpty(search.get("context"))) {

			sql += " AND an.context like concat('%',?,'%')";
			searchList.add(search.get("context"));
		}

		// 创建日期,从xx起
		if (Util.isNotEmpty(search.get("startTime"))) {
			sql += " AND an.create_time >= ? ";
			searchList.add(search.get("startTime"));
		}
		// 创建日期，止到xx
		if (Util.isNotEmpty(search.get("endTime"))) {
			sql += " AND an.create_time <= ? ";
			searchList.add(search.get("endTime"));
		}

		ResultSet rs = jdbcUtil.executeQuery(sql, searchList.toArray());

		try {
			while (rs.next()) {
				
				

				Announcement announcement = new Announcement();
				announcement.setAnnouncementId(rs.getInt("announcement_id"));
				announcement.setTitle(rs.getString("title"));
				announcement.setContext(rs.getString("context"));
				
				
				//announcement.setCreateTime(rs.getDate("create_time"));,这样只能拿到年月日，时分秒丢失
				
				//使用getTimestamp
				announcement.setCreateTime(rs.getTimestamp("create_time"));
				
				announcement.setCreaterId(rs.getInt("creater_id"));
				announcement.setIsActive(rs.getInt("an.is_active"));
				
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				
				announcement.setAdmin(admin); 
				
				list.add(announcement);

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

	/*
	 * 切换公告的显示和隐藏，0隐藏，1显示
	 */
	public int toggleClientActive(String announcementId, String action) {

		String sql = "UPDATE announcement SET is_active=?  WHERE announcement_id=?";

		return jdbcUtil.executeUpdate(sql, action, announcementId);

	}

	/**
	 * 增加一条公告
	 * @param announcement
	 * @return
	 */
	public int addAnnouncement(Announcement announcement) {

		String sql = "INSERT INTO announcement ";
		
		sql += " (`title`, `context`, `creater_id`, `create_time`, `is_active`) ";
		
		sql +=" VALUES (?,?,?,?,?)";
		
		return jdbcUtil.executeUpdate(sql,announcement.getTitle(),announcement.getContext(),announcement.getCreaterId()
				,sdf.format( announcement.getCreateTime() ),announcement.getIsActive());
	
	}

	/**
	 * 根据announcementId，查询一条公告
	 * @param announcementId
	 * @return
	 */
	public Announcement getAnnouncement(int announcementId) {

		String sql = "SELECT * FROM announcement an LEFT JOIN admin ad ON an.creater_id=ad.admin_id WHERE an.announcement_id=? ";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, announcementId);
		
		Announcement announcement = null;
		
		try {
			if(rs.next()) {
				
				announcement = new Announcement();
				
				announcement.setAnnouncementId(rs.getInt("announcement_id"));
				announcement.setTitle(rs.getString("title"));
				announcement.setContext(rs.getString("context"));
				announcement.setCreateTime(rs.getTimestamp("create_time"));
				announcement.setCreaterId(rs.getInt("creater_id"));
				announcement.setIsActive(rs.getInt("an.is_active"));
				
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				
				announcement.setAdmin(admin);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			jdbcUtil.close();
		}
		
		return announcement;
	}

	
	/**
	 * 查询所有显示的公告（最新十条）
	 * @param num
	 * @return
	 */
	public ArrayList<Announcement> getAnnouncmentNum(int num) {

		ArrayList<Announcement> list = new ArrayList<>();


		String sql = "SELECT * FROM announcement an LEFT JOIN admin ad ON an.creater_id=ad.admin_id WHERE an.is_active=1 ";
		
		sql += " ORDER BY an.create_time DESC LIMIT ?";


		ResultSet rs = jdbcUtil.executeQuery(sql, num);

		try {
			while (rs.next()) {

				Announcement announcement = new Announcement();
				announcement.setAnnouncementId(rs.getInt("announcement_id"));
				announcement.setTitle(rs.getString("title"));
				announcement.setContext(rs.getString("context"));
				
				//使用getTimestamp
				announcement.setCreateTime(rs.getTimestamp("create_time"));
				
				announcement.setCreaterId(rs.getInt("creater_id"));
				announcement.setIsActive(rs.getInt("an.is_active"));
				
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("admin_id"));
				admin.setName(rs.getString("name"));
				admin.setEmail(rs.getString("email"));
				admin.setPhone(rs.getString("phone"));
				
				announcement.setAdmin(admin); 
				
				list.add(announcement);

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

}
