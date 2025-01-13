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
	private DatabaseController myDBController;
	
	/**
	 * Construct a SystemController using the basic (no parameter)
	 * DatabaseController as the underlying database access.
	 */
	public SystemController() {
		this.myDBController = new DatabaseController();
	}
	
	/**
	 * Inject a compatible DatabaseController object (generally a sub-class
	 * designed to be used as a mock) in place of the underlying
	 * DatabaseController object.  This method should only be used for testing
	 * in environments where access to the actual database is impossible
	 * (like a remote integration testing server).
	 * 
	 * @param newDBC the mocked DatabaseController object
	 */
	public void injectMock(DatabaseController newDBC) {
		// TODO: How could we make this particular SystemController use newDBC
		//       (presumably a mock) rather than the actual database?
		// HINT: You only need one (very simple!) line here!
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
	public User login(String username, String password) {
		User theUser = this.myDBController.getUser(username);
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
	public List<User> getAllUsers() {
		List<User> usersList = this.myDBController.getAllUsers();
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
	public boolean addUser(String username, String password,
			String firstName, String lastName) {
		return this.myDBController.addUser(new User(username, password, firstName, lastName));
	}
	
	/**
	 * Attempt to remove the specified user from the database.
	 * 
	 * @param username the username to remove (if it exists!)
	 * @return true if the user is successfully removed, or false on database error
	 * or if the username does not exist
	 */
	public boolean removeUser(String username) {
		return this.myDBController.removeUser(username);
	}

}
