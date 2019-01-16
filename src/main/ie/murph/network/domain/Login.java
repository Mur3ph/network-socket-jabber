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

	public static void main(String[] args) {
		Login login = new Login();
		String usernameKey = "Minnie";
		String testPassword = "passwd";

		login.addUserToDtabase(usernameKey, convertStringToCharArrayJava8(testPassword));
		String convertCharToString = convertCharToString(login, usernameKey);
		LOGGER.info("Password String: " + convertCharToString);
		LOGGER.info("Password Character Array 1: " + convertStringToCharArrayJava8(testPassword));
		LOGGER.info("Password Character Array 2: " + Arrays.toString(convertStringToCharArrayJava8(testPassword)));
		LOGGER.info("Password char Array 1: " + convertStringToChar(testPassword));

	}

	private static String convertCharToString(Login login, String usernameKey) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("++convertCharToString()");
		}
		return Arrays.toString(login.retrieveUserFromDtabase(usernameKey));// Convert char to string..;
	}

	private static Character[] convertStringToCharArrayJava8(String passwordValueStr) {
		return passwordValueStr.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}

	private static char[] convertStringToChar(String password) {
		return password.toCharArray();
	}

}
