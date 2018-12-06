package main.ie.murph.network.domain;

import java.io.Serializable;

public class MessageDefault implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String messageSubject;
	private String messageBody;
	
	public MessageDefault(){}
	
	public MessageDefault(String messageBody){}
	
	public MessageDefault(String messageSubject, String messageBody)
	{
		this.messageSubject = messageSubject;
		this.messageBody = messageBody;
	}

	public String getMessageSubject()
	{
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

	public String getMessageBody()
	{
		return messageBody;
	}

	public void setMessageBody(String messageBody)
	{
		this.messageBody = messageBody;
	}
	
	@Override
	public String toString()
	{
		return "\nSubject: " + messageSubject + 
			   "\nMessage: " + messageBody;
	}
}
