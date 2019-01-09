package main.ie.murph.network.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Login
{
	private static final Logger LOGGER = LogManager.getLogger(Login.class.getName());
	private String username;
	private char[] password;
	private Map<String, char[]> databaseOfUsers;
	
	public Login()
	{
		System.getProperty("user.dir");
		databaseOfUsers = new HashMap<String, char[]>();
	}
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public char[] getPassword()
	{
		return password;
	}
	public void setPassword(char[] password)
	{
		this.password = password;
	}
	
	public void resetPassword(char[] password)
	{
		this.password = password;
	}
	
	public void addUserToDtabase(String usernameKey, char[] passwordValue)
	{
		databaseOfUsers.put(usernameKey, passwordValue);
	}
	
	public char[] retrieveUserFromDtabase(String usernameKey)
	{
		return databaseOfUsers.get(usernameKey);
	}
	
	public boolean isUserExist(String usernameKey)
	{
		return databaseOfUsers.containsKey(usernameKey);
	}
	
	public static void main(String[] args)
	{
		Login login = new Login();
		String usernameKey = "Minnie";
		char[] passwordValue = new char[]{'p','a','s','s','w','d'};
		login.addUserToDtabase(usernameKey, passwordValue);
		String convertCharToString = convertCharToString(login, usernameKey);
		LOGGER.info("Password: " + convertCharToString);
		log();
	}
	
	private static String convertCharToString(Login login, String usernameKey) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("++convertCharToString()"); 
		}
		return Arrays.toString(login.retrieveUserFromDtabase(usernameKey));// Convert char to string..;
	}

	private static void log()
	{
		LOGGER.trace("trace");
		LOGGER.debug("debug");
		LOGGER.info("info");
        LOGGER.warn("warn");
        LOGGER.error("error");
        LOGGER.fatal("fatal");
	}
}
