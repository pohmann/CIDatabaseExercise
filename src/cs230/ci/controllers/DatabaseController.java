package cs230.ci.controllers;

import java.util.ArrayList;
import java.util.List;

import cs230.ci.entities.User;
import dblibrary.project.csci230.*;

/**
 * The DatabaseController class is the primary interaction class with the
 * database library.  It currently just calls the lower-level methods and
 * forwards the result, converted to sensible objects.
 * 
 * @author Peter Ohmann
 */
public class DatabaseController {
	private UniversityDBLibrary database;

	/**
	 * The default constructor that connects to the underlying
	 * UniversityDBLibrary object using your team's info.
	 */
	public DatabaseController() {
		// TODO: fill in your team's database info!
		this.database = new UniversityDBLibrary("csci230", "Csci230$");
	}

	// add a user to the db
	public boolean addUser(User theUser) {
		int result = this.database.user_addUser(theUser.getFirstName(), theUser.getLastName(),
				theUser.getUsername(), theUser.getPassword(), 'u');

		return result != -1;
	}

	// remove a user from the db
	public boolean removeUser(String username) {
		int result = this.database.user_deleteUser(username);

		return result != -1;
	}

	// get a user; null if not in DB
	public User getUser(String username) {
		String[][] databaseUserStrings = this.database.user_getUsers();

		for (String[] singleUser : databaseUserStrings) {
			String thisUsername = singleUser[2];
			if (thisUsername.equals(username)) {
				return new User(singleUser[2], singleUser[3], singleUser[0], singleUser[1]);
			}
		}

		return null;
	}

	// get the list of all the users in the DB
	public List<User> getAllUsers() {
		String[][] dbUserList = this.database.user_getUsers();

		ArrayList<User> result = new ArrayList<User>();
		for (String[] user : dbUserList) {
			result.add(new User(user[2], user[3], user[0], user[1]));
		}

		return result;
	}

}