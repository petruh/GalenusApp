package ro.sci.gms.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import ro.sci.gms.domain.Blood;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.Gender;
import ro.sci.gms.domain.Patient;
import ro.sci.gms.domain.Role;
import ro.sci.gms.temp.Li;

/**
 * Pure JDBC implementation for {@link EmployeeDAO}.
 * 
 * @author sebi
 *
 */
@Repository  @DependsOn("JDBCUserDAO")
@Qualifier("patientDAO")
public class JDBCPatientDAO extends JDBCUserDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCPatientDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/*
	 * This seems to be required. Doesn't work without it.
	 */
	public JDBCPatientDAO() {
	}

	public JDBCPatientDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}

	protected Connection newConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
	
			String url = new StringBuilder()//
					.append("jdbc:")//
					.append("postgresql")//
					.append("://")//
					.append(host)//
					.append(":")//
					.append(port)//
					.append("/")//
					.append(dbName)//
					.append("?user=")//
					.append(userName)//
					.append("&password=")//
					.append(pass).toString();
			Connection result = DriverManager.getConnection(url);
			result.setAutoCommit(false);
	
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error getting DB connection. (91)", ex);
		}
	
	}

	public Patient savePatient(Patient patient) throws SQLException {
		Li.st("Saving Patient (user data) to DB.");

		Connection connection = newConnection();
		connection.setAutoCommit(false);

		// First write User data from Patient .to DB
		update(connection, patient);

		// Then write Patient specific data .to DB
		Li.st("Saving Patient (patient data) to DB.");
		try {
			PreparedStatement ps = null;

			ps = connection.prepareStatement(
					"insert into patient (user_id, date_of_birth, gender, medical_background, blood_type, doctor_id) "
							+ "values (?, ?, ?, ?, ?, ?) returning user_id");

			ps.setLong(1, patient.getId());
			ps.setTimestamp(2, new Timestamp(patient.getDateOfBirth().getTime()));
			ps.setString(3, patient.getGender().name());
			ps.setString(4, patient.getMedicalBackground());
			ps.setString(5, patient.getBloodType().name());
			ps.setLong(6, patient.getDoctor().getId());

			ps.executeQuery();

			// View data on console
			patient.see();
		} catch (Exception ex) {
			// throw new RuntimeException("Error building prepared statement.
			// (91)", ex);
			ex.printStackTrace();
		}
		// Finished writing specific Patient data to DB. Ready to commit
		// transaction.
		finally {
			try {
				connection.commit();
				connection.close();
			} catch (Exception ex) {
				throw new RuntimeException("Error commiting or closing while writing user to DB. (91)", ex);
			}
		}

		return patient;

	}

	@Override
	public Patient findById(Long id) {

		List<Patient> result = new LinkedList<>();

		try (Connection connection = newConnection();
				ResultSet rs = connection.createStatement()
						.executeQuery("select * from users, patient where id=user_id and id= " + id)) {

			while (rs.next()) {
				result.add(extractPatient(rs));
			}
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("(091) Error getting Patient from DB.", ex);
		}

		if (result.size() > 1) {
			throw new IllegalStateException("(091) Multiple Patients/Users for id: " + id);
		}
		return result.isEmpty() ? null : result.get(0);
	}

	// ? is it ok to pass the user as param or would it be better to only pass
	// the id
	public boolean delete(Patient patient) {
		boolean result = false;
		try (Connection connection = newConnection()) {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from users where id = " + patient.getId());
			result = statement.execute("delete from patient where user_id = " + patient.getId());
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("(091) Error deleting user or patient data.", ex);
		}
		return result;
	}

	// Still figuring out if needed or not
	public Collection<Patient> getAllPatients() {
		return null;
	}

	private Patient extractPatient(ResultSet rs) throws SQLException {
		Patient patient = new Patient();

		patient.setUsername(rs.getString("user_name"));
		patient.setFirstName(rs.getString("first_name"));
		patient.setLastName(rs.getString("last_name"));
		patient.setPassword(rs.getString("password"));
		patient.setId(rs.getLong("id"));
		patient.setAddress(rs.getString("address"));
		patient.setPhone(rs.getString("phone"));
		patient.setEmail(rs.getString("email"));
		patient.setRole(Role.valueOf(rs.getString("role")));
		patient.setDateOfBirth(new Date(rs.getTimestamp("date_of_birth").getTime()));
		patient.setGender(Gender.valueOf(rs.getString("gender")));
		patient.setMedicalBackground(rs.getString("medical_background"));
		patient.setBloodType(Blood.valueOf(rs.getString("blood_type")));

		// After JDBCDoctorDAO is operational, this line needs to be updated.
		Long retrievedDoctorId = rs.getLong("id");
		Doctor retrievedDoctor = new Doctor(); // will be replaced by
												// doctorDAO.getDoctor(retrievedDoctorId);
		retrievedDoctor.setId(retrievedDoctorId);

		patient.setDoctor(retrievedDoctor);

		return patient;
	}

}
