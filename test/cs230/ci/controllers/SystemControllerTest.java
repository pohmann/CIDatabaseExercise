package cs230.ci.controllers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs230.ci.entities.User;

public class SystemControllerTest {
	private static User testUser = new User("peter12344321", "secure", "Peter", "Ohmann");
	private static User testUser2 = new User("anotheruser555", "morepass", "Another", "Person");

	@Before
	public void setUp() throws Exception {
		DatabaseController.addUser(testUser);
	}

	@After
	public void tearDown() throws Exception {
		DatabaseController.removeUser("peter123444321");
		DatabaseController.removeUser("anotheruser555");
	}

	@Test
	public void testLogin() {
		// test bad username (EC 1)
		User result = SystemController.login("peter98766789", "secure");
		Assert.assertNull(result);
		
		// test good username, bad password (EC 2)
		result = SystemController.login("peter12344321", "wrongpass");
		Assert.assertNull(result);
		
		// test good username and password (EC 3)
		// NOTE: assertion here is arbitrary, ensuring that we get back the right data
		result = SystemController.login("peter12344321", "secure");
		Assert.assertEquals(testUser.getFirstName(), result.getFirstName());
	}

	@Test
	public void testGetAllUsers() {
		// get original # of users in the DB
		int precount = DatabaseController.getAllUsers().size();
		
		// add another one
		DatabaseController.addUser(testUser2);
		
		// make sure the # of users went up by 1!
		int postcount = DatabaseController.getAllUsers().size();
		Assert.assertEquals(precount+1, postcount);
	}

}
