package main.ie.murph.network.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Login {
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());
//	private String username;
//	private char[] password;
	private Map<String, Character[]> databaseOfUsers;
	private Map<String, List<String>> databaseOfUsersMessages;
	private List<String> list;

	public Login() {
		System.getProperty("user.dir");
		databaseOfUsers = new HashMap<String, Character[]>();
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public char[] getPassword() {
//		return password;
//	}
//
//	public void setPassword(char[] password) {
//		this.password = password;
//	}
//
//	public void resetPassword(char[] password) {
//		LOGGER.info("++resetPassword()");
//		this.password = password;
//	}

	public void addUserToDtabase(String usernameKey, Character[] passwordValue) {
		LOGGER.info("++addUserToDtabase()");
		databaseOfUsers.put(usernameKey, passwordValue);
	}

	public Character[] retrieveUserFromDtabase(String usernameKey) {
		LOGGER.info("++retrieveUserFromDtabase()");
		return databaseOfUsers.get(usernameKey);
	}

	public boolean isUserExist(String usernameKey) {
		LOGGER.info("++isUserExist()");
		return databaseOfUsers.containsKey(usernameKey);
	}

	public String convertCharToString(Login login, String usernameKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("++convertCharToString()");
		}
		return Arrays.toString(login.retrieveUserFromDtabase(usernameKey));// Convert char to string..;
	}

	public Character[] convertStringToCharArrayJava8(String passwordValueStr) {
		LOGGER.debug("++convertStringToCharArrayJava8()");
		return passwordValueStr.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}

	public char[] convertStringToChar(String password) {
		LOGGER.debug("++convertStringToChar()");
		return password.toCharArray();
	}

	public Map<String, List<String>> getUsersMessages() {
		LOGGER.debug("++getUsersMessages()");
		return databaseOfUsersMessages;
	}

	public void printUsersMessages(String username) {
		LOGGER.debug("++printUsersMessages()");
		if (keyExists(username)) {
			list.forEach((v) -> System.out.println("Username: " + username + " Message: " + v));
		}
	}

	public void sendUsersMessages(String username, String message) {
		LOGGER.debug("++sendUsersMessages()");
		if (keyExists(username)) {
			LOGGER.debug("++sendUsersMessages()");
			list = grabListOfMessagesBelongingTo(username);
			addMessageToUsersList(list, message);
		} else {
			LOGGER.debug("++sendUsersMessages()");
			list = createNewList();
			addMessageToUsersList(list, message);
			createNewUserMessages(username, list);
		}
		LOGGER.debug("--sendUsersMessages()");
	}

	private boolean keyExists(String username) {
		LOGGER.debug("++keyExists()");
		return databaseOfUsersMessages.containsKey(username);
	}

	private List<String> grabListOfMessagesBelongingTo(String username) {
		LOGGER.debug("++grabListOfMessagesBelongingTo()");
		return databaseOfUsersMessages.get(username);
	}

	private void addMessageToUsersList(List<String> list, String message) {
		LOGGER.debug("++addMessageToUsersList()");
		list.add(message);
	}

	private List<String> createNewList() {
		LOGGER.debug("++createNewList()");
		return new ArrayList<String>();
	}

	private void createNewUserMessages(String username, List<String> list) {
		LOGGER.debug("++createNewUserMessages()");
		databaseOfUsersMessages.put(username, list);
	}
}
