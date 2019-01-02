package main.ie.murph.network.domain;

import java.util.HashMap;
import java.util.Map;

public class Login
{
	private String username;
	private char[] password;
	private Map<String, char[]> databaseOfUsers;
	
	public Login()
	{
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
		System.out.println("Password: " + login.retrieveUserFromDtabase(usernameKey));
	}
}
