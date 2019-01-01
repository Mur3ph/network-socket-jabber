package main.ie.murph.network.domain;

import java.util.Map;

public class Login
{
	private String username;
	private Byte[] password;
	private Map<String, Byte[]> databaseOfUsers;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public Byte[] getPassword()
	{
		return password;
	}
	public void setPassword(Byte[] password)
	{
		this.password = password;
	}
	
	public void resetPassword(Byte[] password)
	{
		this.password = password;
	}
	
	public void addUserToDtabase(String usernameKey, Byte[] passwordValue)
	{
		databaseOfUsers.put(usernameKey, passwordValue);
	}
	
	public Byte[] retrieveUserFromDtabase(String usernameKey)
	{
		return databaseOfUsers.get(usernameKey);
	}
	
	public boolean isUserExist(String usernameKey)
	{
		return databaseOfUsers.containsKey(usernameKey);
	}
}
