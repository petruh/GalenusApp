package ro.sci.gms.service;

import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.db.JDBCPatientDAO;
import ro.sci.gms.domain.Patient;

//@Service
public class PatientService extends UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

//	@Resource(name="patientDAO")
	private JDBCPatientDAO patientDAO;

	public void save(Patient patient) throws ValidationException, SQLException {
		LOGGER.debug("Saving: " + patient);
		validate(patient);
		patientDAO.savePatient(patient);
	}
	
	public Patient getPatient(Long id) {
		LOGGER.debug("Getting patient for id: " + id);
		return patientDAO.findById(id);
	}

	public Collection<Patient> getAllPatients() {
		LOGGER.debug("Getting all patients.");
		Collection<Patient> patientsList = patientDAO.getAllPatients();
		return patientsList;
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting patient for id: " + id);
		Patient patient = patientDAO.findById(id);
		
		if (patient != null) {
			patientDAO.delete(patient);
			return true;
		}

		return false;
	}

	private void validate(Patient patient) throws ValidationException {
		if (patient == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.]");
		} else {
			System.out.println("Valid data.");
		}
	}

	
}
