package test.unit.ie.murph.network.domain;

import java.util.Arrays;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.domain.Login;

public class LoginTest {
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());
	private static final Login login = new Login();
	private static final String testUsernameKey = "Minnie";
	private static final String testPassword = "password";

	public static void main(String[] args) {
		registerUserToDatabaseTest();
		registerUserWithMessagingTest();
	}

	private static void registerUserToDatabaseTest() {
		login.registerNewUserToDtabase(testUsernameKey, testPassword);
		String convertCharToString = convertCharToString();
		LOGGER.info("Password String: " + convertCharToString);
		LOGGER.info("Password Character Array 1: " + convertStringToCharArrayJava8());
		LOGGER.info("Password Character Array 2: " + Arrays.toString(convertStringToCharArrayJava8()));
		LOGGER.info("Password char Array 1: " + convertStringToChar());
		LOGGER.info("Correct Password: Should pass test: " + login.validateCredentialsUserFromDtabase(testUsernameKey, testPassword));
		LOGGER.info("Incorrect Password: Should fail test: " + login.validateCredentialsUserFromDtabase(testUsernameKey, "wrong_password"));
	}

	private static String convertCharToString() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("++convertCharToString()");
		}
		return Arrays.toString(login.retrieveUserFromDtabase(testUsernameKey));// Convert char to string..;
	}

	private static Character[] convertStringToCharArrayJava8() {
		return testPassword.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
	}

	private static char[] convertStringToChar() {
		return testPassword.toCharArray();
	}
	
	private static void registerUserWithMessagingTest() {
		LOGGER.debug("++tesNewUserWithMessaging()");
		login.registerNewUserToDtabase(testUsernameKey, testPassword);
		
		populateUserMessages();
		
		printMappedUserWithMessages();
		printListOfUserMesages();
	}

	private static void populateUserMessages() {
		for(int x = 0; x < 5; x++) {
			login.sendUsersMessages(testUsernameKey, testPassword);
		}
	}

	private static void printMappedUserWithMessages() {
		LOGGER.debug("++tesNewUserWithMessaging() - MAP: ");
		login.printMapOfUsersMessages(testUsernameKey);
	}
	
	private static void printListOfUserMesages() {
		LOGGER.debug("++tesNewUserWithMessaging() - LIST: ");
		login.printListOfUsersMessages(testUsernameKey);
	}
}
