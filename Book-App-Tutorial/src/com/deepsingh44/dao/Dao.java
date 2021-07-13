package com.deepsingh44.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {
	private static Connection connection;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "root");
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;
	}

}
