/*package ro.sci;

import java.util.Collection;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.AppointmentService;
import ro.sci.gms.service.UserService;
import ro.sci.gms.service.ValidationException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationTests.class)
@WebAppConfiguration
public class AppTest {

	@Autowired
	AppointmentService aptService;
	@Autowired
	UserService userService;

	@Test
	public void seeHours() {
		// Doctor doctor = new Doctor();
		// Date day = new Date();
		// Collection<Integer> availableHours =
		// aptService.getAvailableHours(doctor, day);
		// System.out.println("Existent appointment(s) at :");
		//
		// for (Integer hour : availableHours) {
		// System.out.println(hour);
		// }
		//
		// System.out.println();
	}

	@Test
	public void runBooking() throws ValidationException {
		try {
			aptService.generateSome();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aptService.getAll();

		Doctor doctor = new Doctor();
		Agenda agenda1 = new Agenda();
		Agenda agenda2 = new Agenda();

		doctor.setAgenda(agenda1);

		agenda2 = doctor.getAgenda();
		Date today = new Date();
		today.setDate(15);

		agenda2.book(today, 9);
		agenda2.book(today, 11);
		agenda2.book(today, 16);
		System.out.println("Booked hours: " + agenda2.getBookedHours(today));
		agenda2.cancelBooking(today, 9);

		System.out.println("Booked hours: " + agenda2.getBookedHours(today));

		System.out.println("Available hours: " + agenda2.getAvailableHours(today));

		// agenda2.book(today, 12);
		//
		// System.out.println("Available hours: " +
		// agenda2.getAvailableHours(today));

		System.out.println("Bookings" + aptService.getAll());

	}

	@Test
	public void checkStuff() {
		try {
			aptService.generateSome();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Collection<Appointment> allAppointments = aptService.getAll();

		Long aptID = null;

		for (Appointment appointment : allAppointments) {
			aptID = appointment.getId();
		}
		Appointment myAppointment = aptService.get(aptID);

		Doctor doctor = myAppointment.getDoctor();

		Date day = myAppointment.getDate();

		System.out.println(myAppointment.list());
		System.out.println(aptService.getAvailableHours(doctor, day));

		aptService.delete(myAppointment.getId());
		System.out.println(aptService.getAvailableHours(doctor, day));
	}

	*//**
	 * Creates some mock patients, doctors and sets an appointment.
	 * 
	 * @Worked for IM context and Agenda object based on HashMap IM
	 *         implementation.
	 *//*
	@Test
	public void runAppTest() {

		StringBuilder output = new StringBuilder();

		Patient aPatient = new Patient();
		Doctor aDoctor = new Doctor();
		Appointment aAppointment = new Appointment();

		aPatient.setFirstName("Mihai");
		aPatient.setLastName("Savin");

		aPatient.setDoctor(aDoctor);

		aDoctor.setFirstName("Celia");
		aDoctor.setLastName("Cruz");

		output.append(aPatient.getDoctor().getFullName());

		try {
			userService.save(aPatient);
			userService.save(aDoctor);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collection<User> all = userService.getAll();

		output.append(all);
		// Assert.assertEquals(2, all.size());

		System.out.println(output);

		System.out.println("-------------------------------------------");

		Long patientID = null;

		patientID = aPatient.getId();

		// Simultating new appointment request context

		Patient thePatient = new Patient();

		thePatient = (Patient) userService.get(patientID);
		Doctor theDoctor = thePatient.getDoctor();

		aAppointment.createAppointment(thePatient, theDoctor);

		Date selectedDate = new Date();
		selectedDate.setDate(15);
		Date selectedTime = new Date();
		selectedDate.setHours(0);

		aAppointment.setDate(selectedDate);

		theDoctor.setAgenda(new Agenda());
		Collection<Integer> availableHours = aptService.getAvailableHours(theDoctor, selectedDate);
		System.out.println("Available doctor's hours: " + availableHours);

		Integer selectedHour = null;

		for (Integer hour : availableHours) {
			selectedHour = hour;
		}

		selectedTime.setHours(selectedHour);

		aAppointment.setTime(selectedTime);

		try {
			aptService.save(aAppointment);
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		availableHours = aptService.getAvailableHours(theDoctor, selectedDate);
		System.out.println("Available doctor's hours: " + availableHours);

		System.out.println(thePatient.getDoctor());
		System.out.println(theDoctor.getAgenda().getBookedHours(selectedDate));

	}

}
*/