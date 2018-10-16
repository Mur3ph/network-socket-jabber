package main.ie.murph.network.domain;

public class Message
{
	private String subject;
	private String messageRecipient;
	private String messageSender;
	private String message;
	
	public Message(){}
	
	public Message(String subject, String messageRecipient, String messageSender, String message)
	{
//		Builder pattern: https://stackoverflow.com/questions/5007355/builder-pattern-in-effective-java
	}
	
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
	public String getMessageRecipient()
	{
		return messageRecipient;
	}
	public void setMessageRecipient(String messageRecipient)
	{
		this.messageRecipient = messageRecipient;
	}
	public String getMessageSender()
	{
		return messageSender;
	}
	public void setMessageSender(String messageSender)
	{
		this.messageSender = messageSender;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	
}
