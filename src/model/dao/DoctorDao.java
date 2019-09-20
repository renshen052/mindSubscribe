package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import baen.Doctor;
import utils.Util;
import utils.jdbc.JdbcUtil;

public class DoctorDao {

	JdbcUtil jdbcUtil = new JdbcUtil();

	/**
	 * 查询doctor表中所有的记录
	 * 
	 * @return
	 */
	public List<Doctor> listAll() {

		ArrayList<Doctor> list = new ArrayList<>();

		String sql = "SELECT * FROM doctor ";

		ResultSet rs = jdbcUtil.executeQuery(sql);

		try {
			while (rs.next()) {

				Doctor doctor = new Doctor();
				doctor.setDoctorId(rs.getInt("doctor_id"));
				doctor.setDoctorName(rs.getString("doctor_name"));
				doctor.setDoctorPwd(rs.getString("doctor_pwd"));
				doctor.setName(rs.getString("name"));
				doctor.setSex(rs.getInt("sex"));
				doctor.setAge(rs.getInt("age"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setEmail(rs.getString("email"));
				doctor.setIsActive(rs.getInt("is_active"));
				doctor.setLevel(rs.getString("level"));
				doctor.setSkill(rs.getString("skill"));
				doctor.setImg(rs.getString("img"));
				doctor.setPlace(rs.getString("place"));

				list.add(doctor);

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
	 * 修改doctor表中is_active的值，是否为激活状态
	 * 
	 * @param doctorId
	 * @param action
	 * @return
	 */
	public int toggleDoctorActive(String doctorId, String action) {

		String sql = "UPDATE doctor SET is_active=?  WHERE doctor_id=?";

		return jdbcUtil.executeUpdate(sql, action, doctorId);

	}

	/**
	 * 根据条件查询咨询师
	 * 
	 * @param search
	 * @return
	 */
	public List<Doctor> listSearch(Map<String, String> search) {

		ArrayList<Doctor> list = new ArrayList<>();

		List<Object> searchList = new ArrayList<Object>();

		String sql = "SELECT * FROM doctor WHERE 1=1 ";

		// 姓名
		if (Util.isNotEmpty(search.get("name"))) {

			sql += " AND name like concat('%',?,'%')";
			searchList.add(search.get("name"));
		}

		// 性别
		if (Util.isNotEmpty(search.get("sex"))) {

			sql += " AND sex=?";
			searchList.add(search.get("sex"));
		}

		// 年龄,从xx起
		if (Util.isNotEmpty(search.get("startAge"))) {
			sql += " AND age >= ? ";
			searchList.add(search.get("startAge"));
		}
		// 年龄，止到xx
		if (Util.isNotEmpty(search.get("endAge"))) {
			sql += " AND age <= ? ";
			searchList.add(search.get("endAge"));
		}

		// 电话
		if (Util.isNotEmpty(search.get("phone"))) {

			sql += " AND phone like concat('%',?,'%')";
			searchList.add(search.get("phone"));
		}

		// 电子邮件
		if (Util.isNotEmpty(search.get("email"))) {

			sql += " AND email like concat('%',?,'%')";
			searchList.add(search.get("email"));
		}

		ResultSet rs = jdbcUtil.executeQuery(sql,searchList.toArray());

		try {
			while (rs.next()) {

				Doctor doctor = new Doctor();
				doctor.setDoctorId(rs.getInt("doctor_id"));
				doctor.setDoctorName(rs.getString("doctor_name"));
				doctor.setDoctorPwd(rs.getString("doctor_pwd"));
				doctor.setName(rs.getString("name"));
				doctor.setSex(rs.getInt("sex"));
				doctor.setAge(rs.getInt("age"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setEmail(rs.getString("email"));
				doctor.setIsActive(rs.getInt("is_active"));
				doctor.setLevel(rs.getString("level"));
				doctor.setSkill(rs.getString("skill"));
				doctor.setImg(rs.getString("img"));
				doctor.setPlace(rs.getString("place"));

				list.add(doctor);

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
	 * 根据doctorId 删除 doctor
	 * @param doctorId
	 * @param response
	 */
	public int deleteDoctor(Integer doctorId) {
		
		String sql = "DELETE FROM doctor WHERE doctor_id=?";
		
		return jdbcUtil.executeUpdate(sql, doctorId);
	}

	
	/**
	 * 通过doctor的doctorId 查询doctor
	 * @param doctorId
	 * @return
	 */
	public Doctor getDoctorByDoctorId(int doctorId) {

		String sql = "SELECT * FROM doctor WHERE doctor_id=?";
		
		ResultSet rs = jdbcUtil.executeQuery(sql, doctorId);
		
		Doctor doctor = null;
		
		try {
			if(rs.next()) {
				
				doctor = new Doctor();
				doctor.setDoctorId(rs.getInt("doctor_id"));
				doctor.setDoctorName(rs.getString("doctor_name"));
				doctor.setDoctorPwd(rs.getString("doctor_pwd"));
				doctor.setName(rs.getString("name"));
				doctor.setSex(rs.getInt("sex"));
				doctor.setAge(rs.getInt("age"));
				doctor.setPhone(rs.getString("phone"));
				doctor.setEmail(rs.getString("email"));
				doctor.setIsActive(rs.getInt("is_active"));
				doctor.setLevel(rs.getString("level"));
				doctor.setSkill(rs.getString("skill"));
				doctor.setImg(rs.getString("img"));
				doctor.setPlace(rs.getString("place"));
				
				
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
		
		return doctor;
	}

	/**
	 * 修改Doctor
	 * @param doctor
	 */
	public int updateDoctor(Doctor doctor) {

		String sql = "UPDATE doctor SET ";
		
		sql += "name=?, sex=?, age=?, phone=?, email=?, is_active=?, level=?, skill=?, img=?, place=?";
		
		sql += "WHERE doctor_id = ?";
		
		return jdbcUtil.executeUpdate(sql, doctor.getName(),doctor.getSex(),
				doctor.getAge(),doctor.getPhone(),doctor.getEmail(),doctor.getIsActive(),doctor.getLevel(),
				doctor.getSkill(),doctor.getImg(),doctor.getPlace(),doctor.getDoctorId());
	}

	/**
	 * 增加一个Doctor
	 * @param doctor
	 * @return
	 */
	public int addDoctor(Doctor doctor) {

		String sql = "INSERT INTO doctor ";
		
		sql += " (doctor_name, doctor_pwd, name, sex, age, phone, email, is_active, level, skill, img, place) ";
		
		sql +="VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		return jdbcUtil.executeUpdate(sql, doctor.getDoctorName(),doctor.getDoctorPwd(),doctor.getName(),doctor.getSex(),
				doctor.getAge(),doctor.getPhone(),doctor.getEmail(),doctor.getIsActive(),doctor.getLevel(),
				doctor.getSkill(),doctor.getImg(),doctor.getPlace());
	}

}
