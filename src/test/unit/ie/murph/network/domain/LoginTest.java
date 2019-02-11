package test.unit.ie.murph.network.domain;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.domain.Login;

public class LoginTest {
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());
	private static final Login LOGIN = new Login();
	private static final String TEST_USERNAME_MAP_KEY = "Minnie";
	private static final String TEST_PASSWORD_MAP_VALUE = "password";

	public static void main(String[] args) {
		registerUserToDatabaseTest();
		registerUserWithMessagingTest();
	}

	private static void registerUserToDatabaseTest() {
		LOGIN.registerNewUserToDtabase(TEST_USERNAME_MAP_KEY, TEST_PASSWORD_MAP_VALUE);
		String convertCharToString = convertCharToString();
		LOGGER.info("Password String: " + convertCharToString);
		LOGGER.info("Password Character Array 1: " + convertStringToCharArrayJava8());
		LOGGER.info("Password Character Array 2: " + Arrays.toString(convertStringToCharArrayJava8()));
		LOGGER.info("Password char Array 1: " + convertStringToChar());
		LOGGER.info("Correct Password: Should pass test: " + LOGIN.validateCredentialsUserFromDtabase(TEST_USERNAME_MAP_KEY, TEST_PASSWORD_MAP_VALUE));
		LOGGER.info("Incorrect Password: Should fail test: " + LOGIN.validateCredentialsUserFromDtabase(TEST_USERNAME_MAP_KEY, "wrong_password"));
	}

	private static String convertCharToString() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("++convertCharToString()");
		}
		return Arrays.toString(LOGIN.retrieveUserFromDtabase(TEST_USERNAME_MAP_KEY));// Convert char to string..;
	}

	private static Character[] convertStringToCharArrayJava8() {
		return TEST_PASSWORD_MAP_VALUE.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}

	private static char[] convertStringToChar() {
		return TEST_PASSWORD_MAP_VALUE.toCharArray();
	}
	
	private static void registerUserWithMessagingTest() {
		LOGGER.debug("++tesNewUserWithMessaging()");
		LOGIN.registerNewUserToDtabase(TEST_USERNAME_MAP_KEY, TEST_PASSWORD_MAP_VALUE);
		
		populateUserMessages();
		
		printMappedUserWithMessages();
		printListOfUserMesages();
	}

	private static void populateUserMessages() {
		for(int x = 0; x < 5; x++) {
			LOGIN.sendUsersMessages(TEST_USERNAME_MAP_KEY, TEST_PASSWORD_MAP_VALUE);
		}
	}

	private static void printMappedUserWithMessages() {
		LOGGER.debug("++tesNewUserWithMessaging() - MAP: ");
		LOGIN.printMapOfUsersMessages(TEST_USERNAME_MAP_KEY);
	}
	
	private static void printListOfUserMesages() {
		LOGGER.debug("++tesNewUserWithMessaging() - LIST: ");
		LOGIN.printListOfUsersMessages(TEST_USERNAME_MAP_KEY);
	}
}
