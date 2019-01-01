package main.ie.murph.network.domain;

public class Login
{
	private String username;
	private byte[] password;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public byte[] getPassword()
	{
		return password;
	}
	public void setPassword(byte[] password)
	{
		this.password = password;
	}
	
	public void resetPassword(byte[] password)
	{
		this.password = password;
	}
}
