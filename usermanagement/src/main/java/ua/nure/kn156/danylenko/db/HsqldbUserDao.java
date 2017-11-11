package ua.nure.kn156.danylenko.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn156.danylenko.User;

class HsqldbUserDao implements UserDAO {
	/**
	 * UPDATE_QUERY, INSERT_QUERY, DELETE_QUERY, SELECT_BY_ID, SELECT_ALL_QUERY constants for updating, inserting,
	 * deleting or selection data from db table
	 */
	private static final String UPDATE_QUERY = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE id=?";
	private static final String SELECT_BY_ID = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id=?";
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(){
	}
	
	public HsqldbUserDao(ConnectionFactory connectionFactory){
		this.connectionFactory = connectionFactory;
	}
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Override
	public User create(User user) throws DatabaseException {
		try{
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			int n = statement.executeUpdate();
			if (n!=1) {
				throw new DatabaseException("Number of inserted rows: " + n);
			}
			CallableStatement callableStatement = connection
					.prepareCall("call IDENTITY()");	
			ResultSet keys = callableStatement.executeQuery();
			User insertedUser = new User(user);
			if (keys.next()) {
				insertedUser.setId(keys.getLong(1));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return insertedUser;
		} catch (DatabaseException e) 
		{
			throw e;
		} catch(SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User find(Long id) throws DatabaseException {
		User user = new User();
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(SELECT_BY_ID);
			statement.setLong(1, id.longValue());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
				
		return user;
		}

	@Override
	public void update(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(UPDATE_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			statement.setLong(4, user.getId().longValue());
			
			int n = statement.executeUpdate();
			if (n!=1) {
				throw new DatabaseException("Number of updated rows: " + n);
			}
			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
			
	}

	
	@Override
	public void delete(User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection
					.prepareStatement(DELETE_QUERY);
			statement.setLong(1, user.getId().longValue());
			int n = statement.executeUpdate();
			if (n!=1) {
				throw new DatabaseException("Number of deleted rows: " + n);
			}
			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
				
	}
	

	@Override
	public Collection<User> findAll() throws DatabaseException {
		Collection result = new LinkedList();
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while (resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
				
		return result;
	}

}
