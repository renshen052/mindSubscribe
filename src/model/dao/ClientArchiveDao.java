package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClientArchive;
import bean.Doctor;
import utils.jdbc.JdbcUtil;

public class ClientArchiveDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询共有多少条咨询记录
	 * 
	 * @return
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
	 * @param clientId
	 * @return
	 */
	public List<ClientArchive> listClientArchive(Integer clientId, int statusStart,int statusEnd ) {
		
		List<ClientArchive> list = new ArrayList<>();

		String sql = "SELECT *";

		sql += " FROM `client_archive` ca LEFT JOIN doctor d ON ca.doctor_id=d.doctor_id";
		
		sql += "  WHERE client_id=? AND status >= ? AND status <= ?";

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
	 * @param rs
	 * @return
	 * @throws SQLException 
	 */
	private ClientArchive getCAList(ResultSet rs) throws SQLException {

		ClientArchive clientArchive = new ClientArchive();
		clientArchive.setArchivesId(rs.getInt("archives_id"));
		clientArchive.setClientId(rs.getInt("client_id"));
		clientArchive.setDoctorId(rs.getInt("doctor_id"));
		clientArchive.setQuestionContext(rs.getString("question_context"));
		clientArchive.setLevel(rs.getInt("level"));
		clientArchive.setApplyTime(rs.getTimestamp("apply_time"));
		clientArchive.setExpectPlace(rs.getString("expect_place"));
		clientArchive.setExpectTime(rs.getString("expect_time"));
		clientArchive.setStartDatetime(rs.getTimestamp("start_datetime"));
		clientArchive.setEndDatetime(rs.getTimestamp("end_datetime"));
		clientArchive.setStatus(rs.getInt("status"));
		clientArchive.setDocPath(rs.getString("doc_path"));
		clientArchive.setSecondQuestionContext(rs.getString("second_question_context"));
		clientArchive.setIsSecondDo(rs.getInt("is_second_do"));

		return clientArchive;
	}
}
