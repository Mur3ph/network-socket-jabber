package main.ie.murph.network.domain;

import java.util.List;

public class SentMessage 
{
	private User user;
	private List<Message> messaeges;
	
	public String viewSenderEmail()
	{
		return user.getEmail();
	}
	
	public int getNumberOfMessagesInbox()
	{
		return messaeges.size()+1;
	}
	
	public List<Message> getMessages()
	{
		return messaeges;
	}
	
	public void viewMessages()
	{
		messaeges.forEach(System.out::println);
	}
}
