package ro.sci.gms.domain;

import java.math.BigInteger;
import java.security.SecureRandom;

public class VerificationToken {

	private String token;
	private User user;

	public VerificationToken() {
		super();
		token = generateToken();
	}
	
	public VerificationToken(User user) {
		this();
		this.user = user;
	}

	public VerificationToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	private String generateToken() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}