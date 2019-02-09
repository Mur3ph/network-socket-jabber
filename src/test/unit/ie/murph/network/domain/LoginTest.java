package test.unit.ie.murph.network.domain;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.domain.Login;

public class LoginTest {
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());

	public static void main(String[] args) {
		Login login = new Login();
		String usernameKey = "Minnie";
		String testPassword = "passwd";

		login.addNewUserToDtabase(usernameKey, testPassword);
		String convertCharToString = convertCharToString(login, usernameKey);
		LOGGER.info("Password String: " + convertCharToString);
		LOGGER.info("Password Character Array 1: " + convertStringToCharArrayJava8(testPassword));
		LOGGER.info("Password Character Array 2: " + Arrays.toString(convertStringToCharArrayJava8(testPassword)));
		LOGGER.info("Password char Array 1: " + convertStringToChar(testPassword));
		LOGGER.info("Correct Password: Should pass test: " + login.validateCredentialsUserFromDtabase(usernameKey, testPassword));
		LOGGER.info("Incorrect Password: Should fail test: " + login.validateCredentialsUserFromDtabase(usernameKey, "wrong_password"));
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
