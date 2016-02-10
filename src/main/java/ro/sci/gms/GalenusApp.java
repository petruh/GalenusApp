package ro.sci.gms;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.sci.gms.dao.AppointmentDAO;
import ro.sci.gms.dao.inmemory.IMAppointmentDAO;
import ro.sci.gms.dao.inmemory.IMUserDAO;
import ro.sci.gms.domain.Agenda;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.User;
import ro.sci.gms.service.DoctorService;
import ro.sci.gms.service.PatientService;
import ro.sci.gms.service.UserService;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class GalenusApp {
	public static void main(String[] args) {
		SpringApplication.run(GalenusApp.class, args);
		
	}
	
//	@Bean 
//	public User loadMockUser() {
//		User user = new User();
//		user.setUsername("mock");
//		user.setPassword("mock");
//	}
	
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public PatientService patientService() {
		return new PatientService();
	}
	
	@Bean
	public DoctorService doctorService() {
		return new DoctorService();
	}

	@Bean
	public AppointmentDAO aptDAO() {
		return new IMAppointmentDAO();
		// JDBCAppointmentDAO("localhost", "5432", "test", "test", "test");
	}

	@Bean
	public IMUserDAO userDAO() {
		return new  IMUserDAO();
		//JDBCUserDAO("localhost", "5432", "galenus", "postgres", "postgres");
	}
	
	@Bean
	public IMUserDAO patientDAO() {
		return new  IMUserDAO();
//		JDBCPatientDAO("localhost", "5432", "galenus", "postgres", "postgres");
	}
	
	@Bean
	public IMUserDAO doctorDAO() {
		return new  IMUserDAO();
//		JDBCDoctorDAO("localhost", "5432", "galenus", "postgres", "postgres");
	}
	
	@Bean
	public Patient loggedPatient() {
		Patient patient = new Patient();
		Doctor doctor = new Doctor();
		Agenda agenda = new Agenda();
		doctor.setFirstName("Arnold");
		doctor.setLastName("Schwarzenegger");
		doctor.setAgenda(agenda);
		patient.setFirstName("Angela");
		patient.setLastName("Merkel");
		patient.setDoctor(doctor);
		patient.setUsername("angela.merkel");
		patient.setEmail("angela.merkel@bundesregierung.de");
		patient.setPhone("+49 89 636 48018");
		patient.setMedicalBackground("Loves to cuddle with muslim alien citizens.");
		patient.setDateOfBirth(new Date());
		patient.setPassword("1234");
		
		return patient;
	}

}
