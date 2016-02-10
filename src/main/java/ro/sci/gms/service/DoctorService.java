package ro.sci.gms.service;

import java.sql.SQLException;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.db.JDBCDoctorDAO;
import ro.sci.gms.domain.Doctor;

@Service
public class DoctorService extends UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);

//	@Resource(name="doctorDAO")
	@Autowired
	private JDBCDoctorDAO doctorDAO;

	public void save(Doctor doctor) throws ValidationException, SQLException {
		LOGGER.debug("Saving: " + doctor);
		validate(doctor);
		doctorDAO.saveDoctor(doctor);
	}
	
	public Doctor getDoctor(Long id) {
		LOGGER.debug("Getting patient for id: " + id);
		return doctorDAO.findById(id);
	}

	public Collection<Doctor> getAllDoctors() {
		LOGGER.debug("Getting all doctors.");
		Collection<Doctor> doctorsList = doctorDAO.getAllDoctors();
		return doctorsList;
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting doctor for id: " + id);
		Doctor doctor = doctorDAO.findById(id);
		
		if (doctor != null) {
			doctorDAO.delete(doctor);
			return true;
		}

		return false;
	}

	private void validate(Doctor doctor) throws ValidationException {
		if (doctor == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.]");
		} else {
			System.out.println("Valid data.");
		}
	}

	
}
