package ro.sci.gms.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;

@Service("userService")
public class UserService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	/*
	 * No instantiation trough new needed, Spring takes care of this.
	 * 
	 * @Autowired was firstly used. After producing more specific classes, this
	 * annotation was not working anymore. Functional@7.2.16:18.
	 */
//	@Resource(name = "userDAO")
	@Autowired
	@Qualifier("userDAO")
	private UserDAO<User> userDAO;

	public void save(User user) throws ValidationException {
		LOGGER.debug("Saving: " + user);

		validate(user);
		userDAO.update(user);
		Li.st(user.getId() + "$" + user.getUsername());
	}

	public User get(Long id) {
		LOGGER.debug("Getting user for id: " + id);
		return userDAO.findById(id);
	}

	public Collection<User> getAll() {
		Collection<User> usersList = userDAO.getAll();

		return usersList;
	}

	public boolean delete(Long id) {
		LOGGER.debug("Deleting user for id: " + id);

		User user = userDAO.findById(id);
		if (user != null) {
			userDAO.delete(user);
			return true;
		}

		return false;
	}

	private void validate(User user) throws ValidationException {
		// Minimal validation. Needs extension.
		if (user == null) {
			throw new ValidationException("Invalid data. [BETA version err: Not enough data.](091)");
		} else {
			System.out.println("Valid data.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findByUsername(username);
		if (user != null) {
			System.out.println("Fetching login details for " + user.toString());
			String role = ""+user.getRole();
//			List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
//			gas.add(new GrantedAuthorityImpl(role));
//			user.setAuthorities(gas);
//			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true,
//					true, true, AuthorityUtils.createAuthorityList(role));
			return user;
		}
		else {
			throw new UsernameNotFoundException("The username was not found");
		}
	}
}
