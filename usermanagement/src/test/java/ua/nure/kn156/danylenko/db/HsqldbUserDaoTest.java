package ua.nure.kn156.danylenko.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import ua.nure.kn156.danylenko.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	/**
	 * FIND_ID_USER, UPDATE_ID_USER, DELETE_ID_USER constants for testing methods from the class "HsqldbUserDao"
	 */
	private static final Long FIND_ID_USER = 1001L;
	private static final Long UPDATE_ID_USER = 1001L;
	private static final Long DELETE_ID_USER = 1000L;
	private	HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory); 
		}

	public void testCreate() {
		User user= new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setDateOfBirth(new Date());
		assertNull(user.getId());
		User createdUser;
		try {
		createdUser = dao.create(user);
		assertNotNull(createdUser);
		assertNotNull(createdUser.getId());
		assertEquals(user.getFullName(), createdUser.getFullName());
		assertEquals(user.getAge(), createdUser.getAge());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
	}
	
	public void testFindAll() {
		try {
			Collection collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindById() {
		User user = new User();
		try {
			User findedUser = dao.find(FIND_ID_USER);
			assertNotNull("User is null", findedUser);
			assertEquals("Discrepancy between user's ID", FIND_ID_USER, findedUser.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testUpdateUser() {
		
		try {
			User user = dao.find(UPDATE_ID_USER);
			user.setFirstName("John");
			dao.update(user);
			User updatedUser = dao.find(UPDATE_ID_USER);
			assertNotNull("User doesn't update", updatedUser);
			assertEquals("Discrepancy between user's information", user.getFirstName(), updatedUser.getFirstName());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
public void testDeleteUser() {
		User user = null;
		try {
			user = dao.find(DELETE_ID_USER);
			dao.delete(user);
			User deletedUser = dao.find(DELETE_ID_USER);
			assertNotNull("User doesn't delete", deletedUser);
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl(
				"org.hsqldb.jdbcDriver",
				"jdbc:hsqldb:file:db/usermanagement",
				"sa",
				"");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass()
				.getClassLoader()
				.getResourceAsStream("usersDataSet.xml")); 
		return dataSet;
	}

}
