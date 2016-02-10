package ro.sci.gms.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import ro.sci.gms.domain.Doctor;
import ro.sci.gms.temp.Li;

/**
 * Pure JDBC implementation for {@link EmployeeDAO}.
 * 
 * @author sebi
 *
 */
@Repository  @DependsOn("JDBCUserDAO")
@Qualifier("doctorDAO")
public class JDBCDoctorDAO extends JDBCUserDAO {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/*
	 * This seems to be required. Doesn't work without it.
	 */
	public JDBCDoctorDAO() {
	}

	public JDBCDoctorDAO(String host, String port, String dbName, String userName, String pass) {
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

	public Doctor saveDoctor(Doctor doctor) throws SQLException {
		Li.st("Saving Doctor (user data) to DB.");

		Connection connection = newConnection();
		connection.setAutoCommit(false);

		// First write User data from Doctor .to DB
		update(connection, doctor);

		// Then write Doctor specific data .to DB
		Li.st("Saving Doctor (doctor data) to DB.");
		try {
			PreparedStatement ps = null;

			ps = connection.prepareStatement(
					"insert into doctor (user_id, title, specialty, years_of_experience, agenda_id, patient_id) "
							+ "values (?, ?, ?, ?, ?, ?) returning user_id");

			ps.setLong(1, doctor.getId());
			ps.setString(2, doctor.getTitle());
			ps.setString(3, doctor.getSpecialty());
			ps.setLong(4, doctor.getYearsOfExperience());
			ps.setLong(5, doctor.getId());
			ps.setLong(6, doctor.getPatient().getId());

			ps.executeQuery();

		} catch (Exception ex) {
			// throw new RuntimeException("Error building prepared statement.
			// (91)", ex);
			ex.printStackTrace();
		}
		// Finished writing specific Doctor data to DB. Ready to commit
		// transaction.
		finally {
			try {
				connection.commit();
				connection.close();
			} catch (Exception ex) {
				throw new RuntimeException("Error commiting or closing while writing user to DB. (91)", ex);
			}
		}

		return doctor;

	}

	@Override
	public Doctor findById(Long id) {

		List<Doctor> result = new LinkedList<>();

		try (Connection connection = newConnection();
				ResultSet rs = connection.createStatement()
						.executeQuery("select * from users, doctor where id=user_id and id= " + id)) {

			while (rs.next()) {
				result.add(extractDoctor(rs));
			}
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("(091) Error getting Doctor from DB.", ex);
		}

		if (result.size() > 1) {
			throw new IllegalStateException("(091) Multiple Doctors/Users for id: " + id);
		}
		return result.isEmpty() ? null : result.get(0);
	}

	// ? is it ok to pass the user as param or would it be better to only pass
	// the id
	public boolean delete(Doctor doctor) {
		boolean result = false;
		try (Connection connection = newConnection()) {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from users where id = " + doctor.getId());
			result = statement.execute("delete from doctor where user_id = " + doctor.getId());
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("(091) Error deleting user or doctor data.", ex);
		}
		return result;
	}

	// Still figuring out if needed or not
	public Collection<Doctor> getAllDoctors() {
		return null;
	}

	private Doctor extractDoctor(ResultSet rs) throws SQLException {
		Doctor doctor = new Doctor();

		/*doctor.setUserName(rs.getString("user_name"));
		doctor.setFirstName(rs.getString("first_name"));
		doctor.setLastName(rs.getString("last_name"));
		doctor.setPassword(rs.getString("password"));
		doctor.setId(rs.getLong("id"));
		doctor.setAddress(rs.getString("address"));
		doctor.setPhone(rs.getString("phone"));
		doctor.setEmail(rs.getString("email"));
		doctor.setRole(Role.valueOf(rs.getString("role")));
		doctor.setDateOfBirth(new Date(rs.getTimestamp("date_of_birth").getTime()));
		doctor.setGender(Gender.valueOf(rs.getString("gender")));
		doctor.setMedicalBackground(rs.getString("medical_background"));
		doctor.setBloodType(Blood.valueOf(rs.getString("blood_type")));

		// After JDBCDoctorDAO is operational, this line needs to be updated.
		Long retrievedDoctorId = rs.getLong("id");
		Doctor retrievedDoctor = new Doctor(); // will be replaced by
												// doctorDAO.getDoctor(retrievedDoctorId);
		retrievedDoctor.setId(retrievedDoctorId);

		doctor.setDoctor(retrievedDoctor);
*/
		return doctor;
	}

}
