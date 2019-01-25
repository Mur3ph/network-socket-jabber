package main.ie.murph.network.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Login {
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());
	private String username;
	private char[] password;
	private Map<String, Character[]> databaseOfUsers;
	private Map<String, String> databaseOfUsersMessages;

	public Login() {
		System.getProperty("user.dir");
		databaseOfUsers = new HashMap<String, Character[]>();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void resetPassword(char[] password) {
		LOGGER.info("++resetPassword()");
		this.password = password;
	}

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
		return passwordValueStr.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}

	public char[] convertStringToChar(String password) {
		return password.toCharArray();
	}

}
