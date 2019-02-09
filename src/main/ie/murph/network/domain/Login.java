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
	private Map<String, Character[]> MAP_DATABASE_OF_USERS;
	private Map<String, List<String>> MAP_DATABASE_OF_USERS_MESSAGES;
	private List<String> LIST_OF_MESSAGES;

	public Login() {
		System.getProperty("user.dir");
		MAP_DATABASE_OF_USERS = new HashMap<String, Character[]>();
		MAP_DATABASE_OF_USERS_MESSAGES = new HashMap<String, List<String>>();
	}

	public void addNewUserToDtabase(String usernameKey, String passwordValue) {
		LOGGER.info("++addUserToDtabase()");
		MAP_DATABASE_OF_USERS.put(usernameKey, this.convertStringToCharArrayJava8(passwordValue));
	}

	public Character[] retrieveUserFromDtabase(String usernameKey) {
		LOGGER.info("++retrieveUserFromDtabase()");
		return MAP_DATABASE_OF_USERS.get(usernameKey);
	}

	public Character[] validateCredentialsUserFromDtabase(String usernameKey, String usersPassword) {
		LOGGER.info("++validateCredentialsUserFromDtabase()");
		Character[] passwordToBeValidated =  this.convertStringToCharArrayJava8(usersPassword);
		Character[] storedPassword = MAP_DATABASE_OF_USERS.get(usernameKey);
		boolean isEqual = Arrays.equals(passwordToBeValidated, storedPassword);
		if(isEqual)
		{
			LOGGER.info("++validateCredentialsUserFromDtabase(): Login Successful ");
		}
		else {
			LOGGER.info("++validateCredentialsUserFromDtabase(): Login Failure ");
		}
		return MAP_DATABASE_OF_USERS.get(usernameKey);
	}

	public boolean isUserExist(String usernameKey) {
		LOGGER.info("++isUserExist()");
		return MAP_DATABASE_OF_USERS.containsKey(usernameKey);
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
		return MAP_DATABASE_OF_USERS_MESSAGES;
	}

	public void printListOfUsersMessages(String username) {
		LOGGER.debug("++printListOfUsersMessages()");
		if (keyExists(username)) {
			LIST_OF_MESSAGES.forEach((v) -> System.out.println("Username: " + username + " Message: " + v));
		}
	}
	
	public void printMapOfUsersMessages(String username) {
		LOGGER.debug("++printMaoOfUsersMessages()");
		if (keyExists(username)) {
			MAP_DATABASE_OF_USERS_MESSAGES.forEach((k, v) -> System.out.println("Username: " + k + " Message: " + v));
		}
	}
	
	public void sendUsersMessages(String username, String message) {
		LOGGER.debug("++sendUsersMessages()");
		if (keyExists(username)) {
			LOGGER.debug("++sendUsersMessages()");
			LIST_OF_MESSAGES = grabListOfMessagesBelongingTo(username);
			addMessageToUsersList(LIST_OF_MESSAGES, message);
		} else {
			LOGGER.debug("++sendUsersMessages()");
			LIST_OF_MESSAGES = createNewList();
			addMessageToUsersList(LIST_OF_MESSAGES, message);
			createNewUserMessages(username, LIST_OF_MESSAGES);
		}
		LOGGER.debug("--sendUsersMessages()");
	}

	private boolean keyExists(String username) {
		LOGGER.debug("++keyExists()");
		return MAP_DATABASE_OF_USERS_MESSAGES.containsKey(username);
	}

	private List<String> grabListOfMessagesBelongingTo(String username) {
		LOGGER.debug("++grabListOfMessagesBelongingTo()");
		return MAP_DATABASE_OF_USERS_MESSAGES.get(username);
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
		MAP_DATABASE_OF_USERS_MESSAGES.put(username, list);
	}
}
