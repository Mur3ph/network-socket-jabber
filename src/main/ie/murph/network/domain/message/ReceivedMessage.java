package main.ie.murph.network.domain.message;

import java.io.Serializable;

public class ReceivedMessage implements Message, Serializable
{
	private static final long serialVersionUID = 1L;
	private String messageSubject;
	private String messageBody;
	
	public ReceivedMessage(){}
	
	public ReceivedMessage(String messageBody){}
	
	public ReceivedMessage(String messageSubject, String messageBody)
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

	@Override
	public void showMessageToString()
	{
		System.out.println("\nSubject: " + messageSubject + 
				   "\nMessage: " + messageBody );
	}
	
	@Override
	public void printessageName() {
        System.out.println(getClass().getSimpleName());
    }
}
