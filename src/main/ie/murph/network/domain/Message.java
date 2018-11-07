package main.ie.murph.network.domain;



import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageSubject;
	private String messageBody;
	
	public Message(){}
	
	public Message(String messageBody){}
	
	public Message(String messageSubject, String messageBody)
	{
		this.messageSubject = messageSubject;
		this.messageBody = messageBody;
	}

//	@JsonProperty("subject")
	public String getMessageSubject()
	{
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject)
	{
		this.messageSubject = messageSubject;
	}

//	@JsonProperty("message")
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
		return "Subject: " + messageSubject + "\n" + 
			   "Message: " + messageBody;
	}
}
