package main.ie.murph.network.domain;

public class Message
{
	private String subject;
	private String recipientEmailAddress;
	private String senderEmailAddress;
	private String message;
	
	private Message(MessageBuilder messageBuilder)
	{
		this.subject = messageBuilder.subject;
		this.recipientEmailAddress = messageBuilder.recipientEmailAddress;
		this.senderEmailAddress = messageBuilder.senderEmailAddress;
		this.message = messageBuilder.message;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getRecipientEmailAddress()
	{
		return recipientEmailAddress;
	}

	public void setRecipientEmailAddress(String recipientEmailAddress)
	{
		this.recipientEmailAddress = recipientEmailAddress;
	}

	public String getSenderEmailAddress()
	{
		return senderEmailAddress;
	}

	public void setSenderEmailAddress(String senderEmailAddress)
	{
		this.senderEmailAddress = senderEmailAddress;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public static class MessageBuilder
	{
		private String subject;
		private String recipientEmailAddress;
		private String senderEmailAddress;
		private String message;

		public MessageBuilder(){}

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
