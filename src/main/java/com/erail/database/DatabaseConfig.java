package com.erail.database;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConfig {

	Properties property = new Properties();
	private Connection con;

	private static DatabaseConfig instance;

	private DatabaseConfig() {
		try {
			loadDbConfig();
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(property.getProperty("spring.datasource.url"),
					property.getProperty("spring.datasource.username"),
					property.getProperty("spring.datasource.password"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static DatabaseConfig getInstance() {
		if (instance == null) {
			instance = new DatabaseConfig();
		}
		return instance;
	}

	public void loadDbConfig() {
		try {
			FileReader fr = new FileReader("src/main/resources/application.properties");
			property.load(fr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			return con;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
