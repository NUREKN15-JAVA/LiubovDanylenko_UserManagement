package ua.nure.kn156.danylenko;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class TestUser extends TestCase {

	private static final int AGE = 19;
	private User user;
	private Date date;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		Calendar calendar = Calendar.getInstance();
		calendar.set(1997, Calendar.OCTOBER, 16);
		date = calendar.getTime();
	}

	public void testGetFullName() {
		user.setFirstName("Liubov");
		user.setLastName("Danylenko");
		assertEquals("Danylenko, Liubov", user.getFullName());
	}

	public void testGetFirstNameThrowsException() {
		user.setFirstName("Liubov");
		try {
			user.getFullName();
			fail("Error");
		} catch (IllegalArgumentException e) {

		}
	}

	public void testGetLastNameThrowsException() {
		user.setLastName("Danylenko");
		try {
			user.getFullName();
			fail("Error");
		} catch (IllegalArgumentException e) {

		}
	}

	public void testGetAge() {
		user.setDate(date);
		assertEquals(AGE, user.getAge());
	}

}
