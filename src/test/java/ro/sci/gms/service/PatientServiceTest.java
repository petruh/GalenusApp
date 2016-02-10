/*package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Blood;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Gender;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class PatientServiceTest {

	@Autowired
	PatientService patientService;

	private Patient patient = new Patient();

	@After
	public void tearDown() {
		// Collection<Appointment> appointments = new
		// LinkedList<>(aptService.getAll());
		//
		// for (Appointment apt : appointments) {
		// aptService.delete(apt.getId());
		// }
	}

	@Before
	public void setup() {
		patient.setUsername("lidia.buble");
		patient.setPassword("cantFrumos2016");
		patient.setFirstName("Lidia");
		patient.setLastName("Buble");
		patient.setAddress("Toronto");
		patient.setPhone("+40 744 555 777");
		patient.setEmail("lidia.buble@fantasyWorld.org");
		patient.setRole(Role.user);

		patient.setDateOfBirth(new Date());
		patient.setGender(Gender.FEMALE);
		patient.setMedicalBackground("I www to sing sing. Uuu...");
		patient.setBloodType(Blood.A);
		patient.setDoctor(new Doctor());
	}

	
	 * @Test(expected = ValidationException.class) public void
	 * checkSaveUser_double_save() throws ValidationException {
	 * userService.save(user); Li.st(user.getId()); userService.save(user);
	 * Li.st(user.getId()); }
	 

	@Test
	public void checkSavePatient_valid() throws ValidationException, SQLException {
		patientService.save(patient);

		assertTrue(patient.getId() > 0);

		System.out.println(patient.getId());
	}

	@Test
	public void checkGetPatient_valid() throws SQLException {
		Patient saved = null;
		try {
			patientService.save(patient);
			saved = patient;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		Patient retrieved = patientService.getPatient(id);

		assertEquals(saved.getId(), retrieved.getId());

		System.out.println(retrieved.getFullName());
	}

	@Test
	public void checkDeletePatient_valid() throws SQLException {
		Patient saved = null;
		try {
			patientService.save(patient);
			saved = patient;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		patientService.delete(id);

		assertNull(patientService.get(id));

		if (null == patientService.get(id)) {
			System.out.println("Patient was deleted.");
		}
	}

}*/