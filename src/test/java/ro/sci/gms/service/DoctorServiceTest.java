/*package ro.sci.gms.service;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class DoctorServiceTest {

	@Autowired
	DoctorService doctorService;

	private Doctor doctor = new Doctor();

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
		doctor.setUsername("lidia.buble");
		doctor.setPassword("cantFrumos2016");
		doctor.setFirstName("Lidia");
		doctor.setLastName("Buble");
		doctor.setAddress("Toronto");
		doctor.setPhone("+40 744 555 777");
		doctor.setEmail("lidia.buble@fantasyWorld.org");
		doctor.setRole(Role.user);

		doctor.setTitle("");
		doctor.setSpecialty("");
		doctor.setYearsOfExperience(10);
		doctor.setPatient(new Patient());
		
		
		doctor.setDateOfBirth(new Date());
		doctor.setGender(Gender.FEMALE);
		doctor.setMedicalBackground("I www to sing sing. Uuu...");
		doctor.setBloodType(Blood.A);
		doctor.setDoctor(new Doctor());
	}

	
	 * @Test(expected = ValidationException.class) public void
	 * checkSaveUser_double_save() throws ValidationException {
	 * userService.save(user); Li.st(user.getId()); userService.save(user);
	 * Li.st(user.getId()); }
	 

	@Test
	public void checkSaveDoctor() throws ValidationException, SQLException {
		doctorService.save(doctor);

		assertTrue(doctor.getId() > 0);

		System.out.println(doctor.getId());
	}

	@Test
	public void checkGetPatient_valid() throws SQLException {
		Patient saved = null;
		try {
			patientService.save(doctor);
			saved = doctor;
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
			patientService.save(doctor);
			saved = doctor;
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