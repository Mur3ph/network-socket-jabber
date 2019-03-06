package main.ie.murph.network.gui;

import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.constants.text.IGUIRequest;
import main.ie.murph.network.domain.Login;

public class ClientCommunication {

	private static final Logger LOGGER = LogManager.getLogger(ClientCommunication.class.getName());
	private final Scanner SCANNER = new Scanner(System.in);
	private Login login = new Login();
	
	public void userLoginPage(String userRegisteredSuccessMessage) throws ClassNotFoundException
	{
		LOGGER.info("++userLoginPage(): \n" + userRegisteredSuccessMessage + "\n" + IGUIRequest.LOGIN_MENU);
		switch (SCANNER.nextInt())
	    {
	      case 1: this.login();
	      case 2: this.registerNewUser();
	      case 3: // Forgot Password method
	      case 4: // Forgot User name method
	      case 5: // Send Messages..
	               break;
	      default:	System.out.println("What day is it?");;
	    }
	}
	
	private void login() throws ClassNotFoundException
	{
		LOGGER.info("++login(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
		String username = this.SCANNER.next();
		if(login.isUserExist(username))
		{
			validateUserLogin(username);
		}
		else
		{
			LOGGER.info("++login(); Register new user account");
			this.registerNewUser();
		}
	}
	
	private void validateUserLogin(String username) throws ClassNotFoundException {
		LOGGER.info("++validateUserLogin(): " + IGUIRequest.REQUEST_PASSWORD_LOGIN);
		String userPassword = this.SCANNER.next();
		if(loginResultSuccessful(username, userPassword))
		{
			// Redirect to home/Email/Message page
			LOGGER.info("++validateUserLogin(): Login Successful ");
//			run(); 
		}
		else {
			// RETURN_FAILED_LOGIN_MESSAGE
			LOGGER.info("++validateUserLogin(): Login Failure ");
		}
	}

	private boolean loginResultSuccessful(String username, String userPassword) {
		return login.validateCredentialsUserFromDtabase(username, userPassword);
	}

	private void registerNewUser() throws ClassNotFoundException
	{
		LOGGER.info("++registerNewUser(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
		String username = this.SCANNER.next();
		LOGGER.info("++registerNewUser(): " + IGUIRequest.REQUEST_PASSWORD_LOGIN);
		String password = this.SCANNER.next();
		login.registerNewUserToDtabase(username, password);
		userLoginPage("Welcome, User Register Successfully");
	}
	
}