package ua.nure.kn156.danylenko.db;

import java.util.Collection;

import ua.nure.kn156.danylenko.User;
/**
 * Interface for Use class which implement DAO pattern with all CRUD opps
 * @author Lyubov
 *
 */

public interface UserDAO {
	/**
	 * Add user into table and get new user's id from db
	 * @param user all field of user must be not-null except of id field
	 * @return copy of user from db with id auto-created
	 * @throws DatabaseException in case of any error with db
	 */
	User create(User user) throws DatabaseException;
	/**
	 * Find user in table by user's id from db
	 * @param id is used for choosing users from db
	 * @return all information about user from db 
	 * @throws DatabaseException in case of any error with db
	 */
	User find(Long id) throws DatabaseException;
	/**
	 * Update information about the user by user's id from db
	 * @param user all field of user must be not-null except of id field
	 * @throws DatabaseException in case of any error with db
	 */
	void update(User user) throws DatabaseException;
	/**
	 * Delete information about user by user's id from db
	 * @param user user all field of user must be not-null except of id field
	 * @throws DatabaseException in case of any error with db
	 */
	void delete(User user) throws DatabaseException;
	/**
	 * Select all users from db
	 * @return list of all users from db
	 * @throws DatabaseException in case of any error with db
	 */
	Collection<User> findAll() throws DatabaseException;
	/**
	 * Sets the connection factory
	 * @param connectionFactory the connection factory to use
	 */
	void setConnectionFactory(ConnectionFactory connectionFactory);
	
	Collection find(String firstName, String lastName) throws DatabaseException;
	Collection findAll(String firstName, String lastName) throws DatabaseException;

}
