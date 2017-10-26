package ua.nure.kn156.danylenko.db;

import java.sql.SQLException;

public class DatabaseException extends Exception {

	public DatabaseException(SQLException e) {
		super(e);
	}

	public DatabaseException(String string) {
		super(string);
	}

}
