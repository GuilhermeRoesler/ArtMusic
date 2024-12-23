package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class SQLController {
	protected String URL;
	protected String USER;
	protected String PASSWORD;
	protected Connection db;
	
	public SQLController() {
		Dotenv dotenv = Dotenv.load();
		URL = dotenv.get("DB_URL");
		USER = dotenv.get("DB_USER");
		PASSWORD = dotenv.get("DB_PASSWORD");

		try {
			db = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
