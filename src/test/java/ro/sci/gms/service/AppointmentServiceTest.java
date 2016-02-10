/*package ro.sci.gms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class AppointmentServiceTest {

	@Autowired
	AppointmentService aptService;

	private Patient patient1 = new Patient();
	private Patient patient2 = new Patient();
	private Doctor doctor1 = new Doctor();
	private Doctor doctor2 = new Doctor();
	private Appointment appointment1 = new Appointment();
	private Appointment appointment2 = new Appointment();
	private Appointment appointment3 = new Appointment();
	private Appointment appointment4 = new Appointment();

	@After
	public void tearDown() {
		Collection<Appointment> appointments = new LinkedList<>(aptService.getAll());

		for (Appointment apt : appointments) {
			aptService.delete(apt.getId());
		}
	}

	@Before
	public void setup() {
		patient1.setLastName("Lopez");
		patient1.setFirstName("Jennifer");
		patient2.setLastName("Salma");
		patient2.setFirstName("Hayek");
		doctor1.setLastName("Sigmund");
		doctor1.setFirstName("Freud");
		doctor2.setLastName("Albert");
		doctor2.setFirstName("Adler");
		Date date = new Date();
		date.setDate(15);
		Date time1 = new Date();
		Date time2 = new Date();
		date.setHours(0);
		appointment1.setDate(date);
		appointment2.setDate(date);
		appointment3.setDate(date);
		appointment4.setDate(date);
		time1.setHours(10);
		appointment1.setTime(time1);
		appointment2.setTime(time1);
		time2.setHours(14);
		appointment3.setTime(time2);
		appointment4.setTime(time2);
		appointment1.createAppointment(patient1, doctor1);
		appointment2.createAppointment(patient1, doctor2);
		appointment3.createAppointment(patient2, doctor1);
		appointment4.createAppointment(patient2, doctor2);
	}

	@Test
	public void checkSaveAppointment_valid() {
		try {
			aptService.save(appointment1);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(appointment1.getId() > 0);

		System.out.println(appointment1.getId());
	}

	@Test(expected = ValidationException.class)
	public void checkSaveAppointment_double_save() throws ValidationException {
		aptService.save(appointment1);
		aptService.save(appointment1);
	}

	@Test
	public void checkGetAppointment_valid() {
		Appointment saved = null;
		try {
			aptService.save(appointment1);
			saved = appointment1;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		Appointment retrieved = aptService.get(id);

		assertEquals(appointment1, retrieved);

		System.out.println(retrieved.getDetails());
	}

	@Test
	public void checkGetAppointment_noAppointments() {

		Collection<Appointment> all = aptService.getAll(patient1);

		assertEquals(0, all.size());

		System.out.println(all);
	}

	@Test
	public void checkGetAllAppointments_patient_valid() {

		try {
			aptService.save(appointment1);
			aptService.save(appointment2);
			aptService.save(appointment3);
			aptService.save(appointment4);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<Appointment> all = aptService.getAll(patient1);

		for (Appointment ap : all) {
			System.out.println(ap);
		}

		System.out.println(all);

		assertEquals(2, all.size());

	}

	@Test
	public void checkGetAllAppointments_doctor_valid() {

		try {
			aptService.save(appointment1);
			aptService.save(appointment2);
			aptService.save(appointment3);
			aptService.save(appointment4);

		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<Appointment> all = aptService.getAll(doctor1);

		for (Appointment ap : all) {
			System.out.println(ap.list());
		}

		System.out.println(all);

		assertEquals(2, all.size());

	}

	@Test
	public void checkDeleteAppointment_valid() {
		Appointment saved = null;
		try {
			aptService.save(appointment1);
			saved = appointment1;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		aptService.delete(id);

		assertNull(aptService.get(id));

		if (null == aptService.get(id)) {
			System.out.println("Appointment was deleted.");
		}
	}

	@Test
	public void checkSaveAppointment_saveTwoApts() throws ValidationException {

		aptService.save(appointment1);
		aptService.save(appointment3);

		assertTrue(appointment1.getId() > 0);
		assertTrue(appointment3.getId() > 0);

	}

}
*/