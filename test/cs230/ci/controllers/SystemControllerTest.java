package cs230.ci.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs230.ci.entities.User;
import cs230.ci.mocks.MockDatabaseController;

public class SystemControllerTest {
	private static User testUser = new User("peter12344321", "secure", "Peter", "Ohmann");
	private static User testUser2 = new User("anotheruser555", "morepass", "Another", "Person");

	// the class under test (SystemController) and a DatabaseController for setup and tearDown
	private SystemController theController = new SystemController();
	private DatabaseController dbController = new DatabaseController();

	private void switchToMock() {
		MockDatabaseController mockedDBC = new MockDatabaseController();
		theController.injectMock(mockedDBC);
		// TODO: How can we also make setUp and tearDown use the mock?
		// Hint: This just requires a single (simple!) line!
	}

	@Before
	public void setUp() throws Exception {
		//switchToMock();
		this.dbController.addUser(testUser);
	}

	@After
	public void tearDown() throws Exception {
		this.dbController.removeUser("peter123444321");
		this.dbController.removeUser("anotheruser555");
	}

	@Test
	public void testLogin() {
		// test bad username (EC 1)
		User result = this.theController.login("peter98766789", "secure");
		Assert.assertNull(result);

		// test good username, bad password (EC 2)
		result = this.theController.login("peter12344321", "wrongpass");
		Assert.assertNull(result);

		// test good username and password (EC 3)
		// NOTE: assertion here is arbitrary, ensuring that we get back the right data
		result = this.theController.login("peter12344321", "secure");
		Assert.assertEquals(testUser.getFirstName(), result.getFirstName());
	}

	@Test
	public void testGetAllUsers() {
		// get original # of users in the DB
		int precount = this.theController.getAllUsers().size();

		// add another one
		this.dbController.addUser(testUser2);

		// make sure the # of users went up by 1!
		int postcount = this.theController.getAllUsers().size();
		Assert.assertEquals(precount+1, postcount);
	}

}
