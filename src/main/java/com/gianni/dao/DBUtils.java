package com.gianni.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DBUtils {
	
	Logger log = Logger.getLogger(DBUtils.class.getSimpleName());

	private static final String URL_DB = "jdbc:sqlite:centodiciotto.db";
	private static Connection conn;

	public DBUtils() {
		try {
			log.info("INIZIOOOO");
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(URL_DB);
		} catch (SQLException e) {
			log.severe(e.getMessage());
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.severe(e.getMessage());

			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		return conn;
	}

	

}
