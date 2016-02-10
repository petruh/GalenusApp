/*package ro.sci.gms.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.ApplicationTests;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class UserServiceTest {

//	@Autowired
	@Resource(name="userService")
	UserService userService;

	private User user = new User();
	
	@After
	public void tearDown() {
//		Collection<Appointment> appointments = new LinkedList<>(aptService.getAll());
//
//		for (Appointment apt : appointments) {
//			aptService.delete(apt.getId());
//		}
	}

	@Before
	public void setup() {
		user.setUsername("roxana.erdei");
		user.setPassword("cantFrumos2016");
		user.setFirstName("Lidia");
		user.setLastName("Buble");
		user.setAddress("Stockholm");
		user.setPhone("+40 744 555 777");
		user.setEmail("lidia.buble@fantasyWorld.org");
		user.setRole(Role.user);
	}

	@Test
	public void checkSaveUser_valid() throws ValidationException {
		userService.save(user);

		assertTrue(user.getId() > 0);

		System.out.println(user.getId());
	}

	 
	@Test(expected = ValidationException.class)
	public void checkSaveUser_double_save() throws ValidationException {
		userService.save(user);
		Li.st(user.getId());
		userService.save(user);
		Li.st(user.getId());
	}
	
	
	@Test
	public void checkGetUser_valid() {
		User saved = null;
		try {
			userService.save(user);
			saved = user;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		User retrieved = userService.get(id);

		assertEquals(saved.getId(), retrieved.getId());

		System.out.println(retrieved.getFullName());
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
	public void checkDeleteUser_valid() {
		User saved = null;
		try {
			userService.save(user);
			saved = user;
		} catch (ValidationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Long id = saved.getId();

		userService.delete(id);

		assertNull(userService.get(id));

		if (null == userService.get(id)) {
			System.out.println("User was deleted.");
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