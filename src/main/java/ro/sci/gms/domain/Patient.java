package ro.sci.gms.domain;

import java.util.Date;

import ro.sci.gms.temp.Li;

public class Patient extends User {
	private Date dateOfBirth;
	private Gender gender;
	private String medicalBackground;
	private Blood bloodType;
	private Doctor doctor;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getMedicalBackground() {
		return medicalBackground;
	}

	public void setMedicalBackground(String medicalBackground) {
		this.medicalBackground = medicalBackground;
	}

	public Blood getBloodType() {
		return bloodType;
	}

	public void setBloodType(Blood bloodType) {
		this.bloodType = bloodType;
	}

	public String see() {
		String output = "Patient [dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", medicalBackground="
				+ medicalBackground + ", bloodType=" + bloodType + ", doctor=" + doctor + ", getPassword()="
				+ getPassword() + ", getUserName()=" + getUsername() + ", getFullName()=" + getFullName()
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + ", getAdress()="
				+ getAddress() + ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getRole()=" + getRole()
				+ ", hashCode()=" + hashCode() + ", getId()=" + getId() + ", getClass()=" + getClass() + ", toString()="
				+ super.toString() + "]";

		Li.st(output);
		return output;
	}

}
