package ro.sci.gms.dao;

import java.util.Collection;

import ro.sci.gms.domain.User;

public interface UserDAO<T extends User> extends BaseDAO<User> {

	Collection<T> searchById(String query);
	T findByUsername(String username);
}
