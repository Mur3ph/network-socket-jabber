package main.ie.murph.network.domain;

public class User
{
	private int unique_user_id;
	private String username;
	private String email;
	private String password;
	
	public int getUnique_user_id()
	{
		return unique_user_id;
	}
	public void setUnique_user_id(int unique_user_id)
	{
		this.unique_user_id = unique_user_id;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
}
