package ro.sci.gms.domain;

import java.util.Date;

public class Appointment extends AbstractModel {
	Patient patient;
	Doctor doctor;
	String patientName;
	String doctorName;
	Date date;
	Date time;
	String details;

	public void createAppointment(Patient patient, Doctor doctor) {
		this.patient = patient;
		this.doctor = doctor;
		patientName = patient.getFullName(); // TEMP
		doctorName = doctor.getFullName(); // TEMP
		details = "default details"; // TEMP
	}

	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getTime() {
		return time;
	}


	public String getPatientName() {
		return patientName;
	}



	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}



	public String getDoctorName() {
		return doctorName;
	}



	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}



	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String list() {
		return "Appointment [patient=" + patient + ", doctor=" + doctor + ", patientName=" + patientName
				+ ", doctorName=" + doctorName + ", time=" + time + ", details=" + details + "]";
	}

//	@Override
//	public String toString() {
//		StringBuilder appointment = new StringBuilder();
//
////		 appointment.append("Appointment ID : " + this.getId() + "\n");
////		 appointment.append("Patient : " + patient.getFullName() + "\n");
////		 appointment.append("Doctor : " + doctor.getFullName() + "\n");
////		 appointment.append("Time : " + this.getTime() + "\n");
//
//		return appointment.toString();
//	}

}
