package ro.sci.gms.configuration;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.sci.gms.dao.db.JDBCDoctorDAO;

public class DBConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCDoctorDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	/*
	 * This seems to be required. Doesn't work without it.
	 */
	public DBConnection() {
		this("ec2-79-125-117-94.eu-west-1.compute-1.amazonaws.com",
				"5432", "d99d8uvcdiqh5q",
				"gsmxwxyrbiqutc", 
				"ifm7QuPfDxj7VYVqReCWKKQp9Z");
	}

	public DBConnection(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;
	}
}
