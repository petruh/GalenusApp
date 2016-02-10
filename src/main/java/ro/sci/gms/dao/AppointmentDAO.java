package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.User;

public interface AppointmentDAO extends BaseDAO<Appointment>{

	Collection<Appointment> searchById(String query);

	Collection<Appointment> getAll(User user);

	Collection<Appointment> search(String query);
}
