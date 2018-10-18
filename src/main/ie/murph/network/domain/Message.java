package main.ie.murph.network.domain;

public class Message
{
	private final String subject;
	private final String recipientEmailAddress;
	private final String senderEmailAddress;
	private final String message;
	
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
	
	private Message(MessageBuilder messageBuilder)
	{
		this.subject = messageBuilder.subject;
		this.recipientEmailAddress = messageBuilder.recipientEmailAddress;
		this.senderEmailAddress = messageBuilder.senderEmailAddress;
		this.message = messageBuilder.message;
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
