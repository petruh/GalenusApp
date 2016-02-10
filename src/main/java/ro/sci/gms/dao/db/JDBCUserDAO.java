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
import org.springframework.stereotype.Repository;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.Role;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;

/**
 * Pure JDBC implementation for {@link UserDAO}.
 * 
 * @author sebi
 *
 */
@Repository
@Qualifier("userDAO")
public class JDBCUserDAO implements UserDAO<User> {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/*
	 * This seems to be required. Doesn't work without it.
	 */
	public JDBCUserDAO(){
		this("ec2-107-20-136-89.compute-1.amazonaws.com",
				"5432", "d7knfdj6mtmiln",
				"byqbrtdwaibjxz", 
				"UAl53FuuLtSkCF3oFM3itsxvaE");
/*		this("ec2-54-83-12-22.compute-1.amazonaws.com",
				"5432", "d78nunqpo44clm",
				"zjxfqqjwejqiid", 
				"UaeRrlUbjmnxBOxp9FOWEKNG7y");*/
	}

	public JDBCUserDAO(String host, String port, String dbName, String userName, String pass) {
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

	public User update(User user) {
		Li.st("Saving User to DB started.");

		Connection connection = newConnection();

		update(connection, user);

		try {
			connection.commit();
			connection.close();
		} catch (Exception ex) {
			throw new RuntimeException("Error commiting or closing while writing user to DB. (91)", ex);
		}
		return user;

	}

	/**
	 * This method is needed in order to save children of User - Patient and
	 * Doctor that is - into the DB in the same transaction.
	 */
	protected User update(Connection connection, User user) {
		try {
			PreparedStatement ps = null;
			if (user.getId() > 0) {
				ps = connection.prepareStatement(
						"update users set first_name=?, last_name=?, user_name=?, password=?, address=?, phone= ?, email= ?, role=? "
								+ "where id = ? returning id");
			} else {

				ps = connection.prepareStatement(
						"insert into users (first_name, last_name, user_name, password, address, phone, email, role) "
								+ "values (?, ?, ?, ?, ?, ?, ?, ?) returning id");
			}

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getEmail());
			ps.setString(8, user.getRole().name());

			if (user.getId() > 0) {
				ps.setLong(9, user.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getLong(1));
			}
			rs.close();

		} catch (SQLException ex) {
			throw new RuntimeException("Error saving user to DB. (91)", ex);
		}

		return user;

	}

	// Still figuring out if needed or not
	@Override
	public Collection<User> getAll() {
		Connection connection = newConnection();

		Collection<User> result = new LinkedList<>();

		// try (ResultSet rs = connection.createStatement().executeQuery("select
		// * from employee")) {
		//
		// while (rs.next()) {
		// result.add(extractAppointment(rs));
		// }
		// connection.commit();
		// } catch (SQLException ex) {
		//
		// throw new RuntimeException("Error getting employees.", ex);
		// } finally {
		// try {
		// connection.close();
		// } catch (Exception ex) {
		//
		// }
		// }

		return result;
	}

	@Override
	public User findById(Long id) {

		List<User> result = new LinkedList<>();

		try (Connection connection = newConnection();
				ResultSet rs = connection.createStatement().executeQuery("select * from users where id = " + id)) {

			while (rs.next()) {
				result.add(extractUser(rs));
			}
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("Error getting User from DB. (091)", ex);
		}

		if (result.size() > 1) {
			throw new IllegalStateException("(091) Multiple Users for id: " + id);
		}
		return result.isEmpty() ? null : result.get(0);
	}

	// ? is it ok to pass the user as param or would it be better to only pass
	// the id
	public boolean delete(User user) {
		boolean result = false;
		try (Connection connection = newConnection()) {
			Statement statement = connection.createStatement();
			result = statement.execute("delete from users where id = " + user.getId());
			connection.commit();
		} catch (SQLException ex) {
			throw new RuntimeException("(091) Error deleting User.", ex);
		}
		return result;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setUsername(rs.getString("user_name"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setPassword(rs.getString("password"));
		user.setId(rs.getLong("id"));
		user.setAddress(rs.getString("address"));
		user.setPhone(rs.getString("phone"));
		user.setEmail(rs.getString("email"));
		user.setRole(Role.valueOf(rs.getString("role")));
		
		return user;
	}

	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * Don't know is this is really needed. It's a little messed up right now.
	 * Will do some further cleaning up.
	 * 
	 * @see ro.sci.gms.dao.UserDAO#searchById(java.lang.String)
	 */
	@Override
	public Collection<User> searchById(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
