package main.ie.murph.network.communicator.domain.message;

import java.io.Serializable;

public class SentMessage implements Message, Serializable
{
	private static final long serialVersionUID = 1L;
	private String messageSubject;
	private String messageBody;

	public SentMessage(){}

	public SentMessage(String messageSubject, String messageBody)
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
		return "\nSubject: " + messageSubject + "\nMessage: " + messageBody;
	}

	@Override
	public void showMessageToString()
	{
		System.out.println("\nCategory: " + getClass().getSimpleName() + "\nSubject: " + messageSubject + "\nMessage: "
				+ messageBody);
	}

	@Override
	public void printessageName()
	{
		System.out.println(getClass().getSimpleName());
	}
}
