package cs230.ci.controllers;

import java.util.List;

import cs230.ci.entities.User;

/**
 * The SystemController class handles all of the back-end computation work
 * for the system, and serves as a "go-between" for the front-end and the
 * database controller.
 * 
 * @author Peter Ohmann
 */
public class SystemController {
	private static boolean useMock = false;
	
	// other classes should *not* instantiate this class.  It is "pure static".
	private SystemController() throws Exception {
		throw new Exception("Attempt to instantiate a SystemController");
	}
	
	/**
	 * Verify whether the username and password provided match a user in the
	 * database.  Return a Boolean indicating yes or no.
	 * 
	 * @param username the username to check
	 * @param password the password to check for matching the username
	 * @return the matching User object if the username and password match
	 * a database entry, or null otherwise
	 */
	public static User login(String username, String password) {
		User theUser = DatabaseController.getUser(username);
		if (theUser == null)
			return null;
		
		if (!theUser.getPassword().equals(password)) {
			return null;
		}
		else {
			return theUser;
		}
	}

	/**
	 * Get the list of all users in the system (database store).
	 * 
	 * @return the list of users
	 */
	public static List<User> getAllUsers() {
		List<User> usersList = DatabaseController.getAllUsers();
		return usersList;
	}
	
	/**
	 * Attempt to add a new user to the database with the provided details.
	 * 
	 * @param username the new (must be unique!) username
	 * @param password the password to store
	 * @param firstName the user's first name
	 * @param lastName the user's last name (can be blank)
	 * @return true if the user is successfully stored, or false on database error
	 * or if the username is not unique
	 */
	public static boolean addUser(String username, String password,
			String firstName, String lastName) {
		return DatabaseController.addUser(new User(username, password, firstName, lastName));
	}
	
	/**
	 * Attempt to remove the specified user from the database.
	 * 
	 * @param username the username to remove (if it exists!)
	 * @return true if the user is successfully removed, or false on database error
	 * or if the username does not exist
	 */
	public static boolean removeUser(String username) {
		return DatabaseController.removeUser(username);
	}
	
	/**
	 * This method switches the SystemController to use the "mock" database,
	 * rather than the real one.  Of course, it doesn't do anything...yet!
	 */
	public static void mockEnable() {
		// TODO: Switch to using the MockDatabaseController!
		useMock = true;
	}
	
	/**
	 * This method switches the SystemController back to using the "regular"
	 * database!
	 */
	public static void mockDisable() {
		useMock = false;
	}

}
