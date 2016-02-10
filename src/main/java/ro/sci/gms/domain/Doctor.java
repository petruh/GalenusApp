package ro.sci.gms.domain;

public class Doctor extends User {
	private String title;
	private String specialty;
	private int yearsOfExperience;
	private int numberOfPatients;
	private String picture;
	private Agenda agenda;
	private Patient patient;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@Override
	public String toString() {
		return "Doctor [title=" + title + ", specialty=" + specialty + ", yearsOfExperience=" + yearsOfExperience
				+ ", numberOfPatients=" + numberOfPatients + ", picture=" + picture + ", agenda=" + agenda + "]";
	}
	public Doctor() {
		agenda = new Agenda();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public int getNumberOfPatients() {
		return numberOfPatients;
	}
	public void setNumberOfPatients(int numberOfPatients) {
		this.numberOfPatients = numberOfPatients;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	
	

}
