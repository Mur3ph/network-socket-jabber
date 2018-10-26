package main.ie.murph.network.domain;

import java.util.List;

public class SentMessage 
{
	private User user;
	private List<Message> messaeges;
	
	public int getNumberOfMessagesInbox()
	{
		return messaeges.size()+1;
	}
	
	public void getMessages()
	{
//		forEach()
	}
}
