package ro.sci.gms.dao.inmemory;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.User;

@Repository
public class IMUserDAO<T extends User> extends IMBaseDAO implements UserDAO {

	public Collection<T> searchById(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<T> all = new LinkedList<>(getAll());
		for (Iterator<T> it = all.iterator(); it.hasNext();) {
			User user = it.next();
		}
		return all;
	}

	public Collection<T> getAll(T user) {

		Collection<T> all = new LinkedList<>(getAll());
		Collection<T> usersAppointments = new LinkedList<>();

		// for (User user : all) {
		// if ((appointment.getDoctor().equals(user)) ||
		// (appointment.getPatient().equals(user))) {
		// usersAppointments.add(appointment);
		// }
		// }

		return usersAppointments;
	}

	public Collection<User> search(String query) {
		if (StringUtils.isEmpty(query)) {
			return getAll();
		}

		Collection<User> all = new LinkedList<>(getAll());
		// for (Iterator<User> it = all.iterator(); it.hasNext();) {
		// User apt = it.next();
		// String bulkData = apt.getDoctorName() + " " + apt.getPatientName() +
		// //
		// " " + apt.getDate() + " " + apt.getTime() + " " + apt.getDetails();
		// if (!bulkData.toLowerCase().contains(query.toLowerCase())) {
		// it.remove();
		// }
		// }
		return all;
	}

	@Override
	public User findByUsername(String username) {
		Collection<User> users = new LinkedList<User>(getAll());
		for (User user : users) {
			if (username.equals(user.getUsername())) {
				return user;
			}
		}
		return null;
	}

}
