package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Client;
import bean.ClientArchive;
import bean.Doctor;
import utils.jdbc.JdbcUtil;

/**
 * @author h w j
 * @instruction
 * 操作表clientArchive，的DAO
 */
public class ClientArchiveDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询共有多少条咨询记录
	 * 
	 * @return 咨询记录数
	 */
	public int getClientArchiveNum() {

		String sql = "SELECT COUNT(1) FROM client_archive";

		ResultSet rs = jdbcUtil.executeQuery(sql);

		int num = 0;

		try {
			if (rs.next()) {

				num = rs.getInt("count(1)");

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
	 * 查询来访者咨询记录
	 * 
	 * @param clientId 来访者id
	 * @return 咨询记录列表
	 */
	public List<ClientArchive> listClientArchive(Integer clientId, int statusStart,int statusEnd ) {
		
		List<ClientArchive> list = new ArrayList<>();

		String sql = "SELECT *";

		sql += " FROM `client_archive` ca LEFT JOIN doctor d ON ca.doctor_id=d.doctor_id";
		
		sql += "  WHERE client_id=? AND status >= ? AND status <= ?";
		
		sql += " ORDER BY apply_time DESC";

		ResultSet rs = jdbcUtil.executeQuery(sql, clientId, statusStart,statusEnd);

		try {
			while (rs.next()) {

				ClientArchive clientArchive = getCAList(rs);
				Doctor doctor = new Doctor();
				doctor.setDoctorId(clientArchive.getDoctorId());
				doctor.setName(rs.getString("d.name"));
				clientArchive.setDoctor(doctor);
				
				
				list.add(clientArchive);
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
	 * 通过rs封装到ClientArchive对象中
	 * 
	 * @param rs 查询的结果集
	 * @return 从rs中取出封装好的ClientArchive对象
	 * @throws SQLException 
	 */
	private ClientArchive getCAList(ResultSet rs) throws SQLException {

		ClientArchive clientArchive = new ClientArchive();
		clientArchive.setArchivesId(rs.getInt("archives_id"));
		clientArchive.setClientId(rs.getInt("client_id"));
		clientArchive.setDoctorId(rs.getInt("doctor_id"));
		clientArchive.setQuestionContext(rs.getString("question_context"));
		clientArchive.setClientDescription(rs.getString("client_description"));
		clientArchive.setLevel(rs.getInt("level"));
		clientArchive.setApplyTime(rs.getTimestamp("apply_time"));
		clientArchive.setExpectPlace(rs.getString("expect_place"));
		clientArchive.setExpectTime(rs.getString("expect_time"));
		clientArchive.setStartDatetime(rs.getTimestamp("start_datetime"));
		clientArchive.setEndDatetime(rs.getTimestamp("end_datetime"));
		clientArchive.setSubPlace(rs.getString("sub_place"));
		clientArchive.setStatus(rs.getInt("status"));
		clientArchive.setDocPath(rs.getString("doc_path"));
		clientArchive.setSecondQuestionContext(rs.getString("second_question_context"));
		clientArchive.setIsSecondDo(rs.getInt("is_second_do"));

		return clientArchive;
	}

	
	/**
	 * 添加一次申请,并且响应客户端
	 * 
	 * @param clientArchive 咨询记录对象
	 * @return 受影响行数
	 */
	public int addClientArchive(ClientArchive clientArchive) {

		String sql = "INSERT INTO `client_archive` ";
		
		sql += "( `client_id`, `doctor_id`, `question_context`,`client_description`, `level`, `apply_time`, `expect_place`, ";
		
		sql += " `expect_time`, `start_datetime`, `end_datetime`, `sub_place`,`status`, `doc_path`, `second_question_context`, `is_second_do` )";
		
		sql += "VALUES(?,?,?,?,?,?,?,?,  ?,?,?,?,?,?,?)";
	
		
		return jdbcUtil.executeUpdate(sql, clientArchive.getClientId(),clientArchive.getDoctorId(),clientArchive.getQuestionContext()
				,clientArchive.getClientDescription()
				,clientArchive.getLevel(),clientArchive.getApplyTime(),clientArchive.getExpectPlace(),clientArchive.getExpectTime()
				,clientArchive.getStartDatetime(),clientArchive.getEndDatetime(),clientArchive.getSubPlace()
				,clientArchive.getStatus(),clientArchive.getDocPath()
				,clientArchive.getSecondQuestionContext(),clientArchive.getIsSecondDo());
	
	}


	/**
	 * 查询咨询师咨询记录
	 * @param doctorId 咨询记录中对应的咨询师id
	 * @param statusStart 最小状态
	 * @param statusEnd 最大状态
	 * @return
	 */
	public List<ClientArchive> listDoctorArchive(Integer doctorId, int statusStart,int statusEnd) {
		
		List<ClientArchive> list = new ArrayList<>();

		String sql = "SELECT *";

		sql += " FROM `client_archive` ca LEFT JOIN client c ON ca.client_id=c.client_id";
		
		sql += "  WHERE doctor_id=? AND status >= ? AND status <= ?";
		
		sql += " ORDER BY start_datetime DESC";

		ResultSet rs = jdbcUtil.executeQuery(sql, doctorId, statusStart,statusEnd);

		try {
			while (rs.next()) {

				ClientArchive clientArchive = getCAList(rs);
				Client client = new Client();
				client.setClientId(clientArchive.getClientId());
				client.setName(rs.getString("c.name"));
				clientArchive.setClient(client);
				
				list.add(clientArchive);
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
	 * 通过archivesId得到ClientArchive对象
	 * 
	 * @param archivesId 咨询记录id
	 * @return 咨询记录对象
	 */
	public ClientArchive getClientArchiveById(int archivesId) {
		
		ClientArchive clientArchive = null;
		
		String sql = "SELECT *";

		sql += " FROM `client_archive`";
		
		sql += "  WHERE archives_id=?";

		ResultSet rs = jdbcUtil.executeQuery(sql, archivesId);

		try {
			if (rs.next()) {

				clientArchive = getCAList(rs);
				
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

		return clientArchive;
	}

	/**
	 * 切换status
	 * @param archivesId 咨询记录id
	 * @param status 要更新到的状态
	 * @return 受影响行数
	 */
	public int updateClientArchiveStuatus(int archivesId, int status) {

		String sql = "UPDATE `client_archive` SET `status` =? ";
		
		sql += "WHERE `archives_id` =? ";
		
		return jdbcUtil.executeUpdate(sql,status,archivesId);
	
	}

	/**
	 * 安排咨询
	  * 
	 * @param clientArchive 咨询记录
	 * @return 受影响行数
	 */
	public int planSub(ClientArchive clientArchive) {

		String sql = "UPDATE `client_archive` SET `start_datetime` = ? , `end_datetime` = ? , `sub_place` = ? , `status` = 1   ";
		
		sql += "WHERE `archives_id` =? ";
		
		return jdbcUtil.executeUpdate(sql,clientArchive.getStartDatetime(),clientArchive.getEndDatetime()
				,clientArchive.getSubPlace(),clientArchive.getArchivesId());
	
	}

	/**
	 * 修改咨询记录的回访内容
	 * @param archivesId 咨询记录id
	 * @param context 咨询者的评价内容
	 */
	public int evaluateSub(String archivesId, String context) {

		String sql = "UPDATE `client_archive` SET `second_question_context` = ? , `is_second_do` = 1   ";
		
		sql += "WHERE `archives_id` =? ";
		
		return jdbcUtil.executeUpdate(sql,context,archivesId);
	
	}

	
	/**
	 * 更新咨询文档的存放路径
	 * @param archivesId 咨询记录id
	 * @param subDocPath 文档的路径
	 * @return
	 */
	public int uploadSubDoc(String archivesId, String subDocPath) {

		String sql = "UPDATE `client_archive` SET `doc_path` = ? , `status` = 2   ";
		
		sql += "WHERE `archives_id` =? ";
		
		return jdbcUtil.executeUpdate(sql,subDocPath,archivesId);
	
	}
}
