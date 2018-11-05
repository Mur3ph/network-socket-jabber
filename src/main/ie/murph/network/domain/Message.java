package main.ie.murph.network.domain;

import java.lang.reflect.Type;

import com.google.gson.annotations.SerializedName;

public class Message implements Type
{
//	private final User user;
	private final String subject;
	private final String recipientEmailAddress;
	private final String senderEmailAddress;
	
	@SerializedName("getMessage")
	private final String message;
	
	private Message(MessageBuilder messageBuilder)
	{
		this.subject = messageBuilder.subject;
		this.recipientEmailAddress = messageBuilder.recipientEmailAddress;
		this.senderEmailAddress = messageBuilder.senderEmailAddress;
		this.message = messageBuilder.message;
//		this.user = messageBuilder.user;
	}
	

	public String getSubject()
	{
		return subject;
	}

	public String getRecipientEmailAddress()
	{
		return recipientEmailAddress;
	}

	public String getSenderEmailAddress()
	{
		return senderEmailAddress;
	}

	
	public String getMessage()
	{
		return message;
	}
	
	public String toString() {
		return  "Message Subject: " + getSubject() + "\n" + 
			    "Message Sender: " + getSenderEmailAddress() + "\n" +
				"Message Recipient: " + getRecipientEmailAddress() + "\n" +
				"Message: " + getMessage() + "\n";
	}
	
	public static class MessageBuilder
	{
		// Required parameters
		private String subject;
		private String recipientEmailAddress;
		private String senderEmailAddress;
		private String message;
		
		// Optional parameters
//		private String attachment;

		public MessageBuilder sendMessageTo(String recipientEmailAddress)
		{
			this.recipientEmailAddress = recipientEmailAddress;
			return this;
		}

		public MessageBuilder messageFrom(String senderEmailAddress)
		{
			this.senderEmailAddress = senderEmailAddress;
			return this;
		}

		public MessageBuilder messageSubject(String subject)
		{
			this.subject = subject;
			return this;
		}
		
		public MessageBuilder message(String message)
		{
			this.message = message;
			return this;
		}

		public Message build()
		{
			return new Message(this);
		}
	}

}
