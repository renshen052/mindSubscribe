package model.service;

import java.util.List;

import baen.Doctor;
import model.dao.DoctorDao;

public class DoctorService {

	DoctorDao doctorDao = new DoctorDao();
	
	public List<Doctor> listAll() {
		return doctorDao.listAll();
	}

}
