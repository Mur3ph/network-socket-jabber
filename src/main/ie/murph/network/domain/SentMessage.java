package main.ie.murph.network.domain;

public class SentMessage implements Message
{
	// private final User user;
	private final String subject;
	private final String recipientEmailAddress;
	private final String senderEmailAddress;
	private final String message;

	private SentMessage(SentMessageBuilder sentMessageBuilder)
	{
		this.subject = sentMessageBuilder.subject;
		this.recipientEmailAddress = sentMessageBuilder.recipientEmailAddress;
		this.senderEmailAddress = sentMessageBuilder.senderEmailAddress;
		this.message = sentMessageBuilder.message;
//		this.user = messageBuilder.user;
	}

	@Override
	public String getSubject()
	{
		return subject;
	}

	@Override
	public String getRecipientEmailAddress()
	{
		return recipientEmailAddress;
	}

	@Override
	public String getSenderEmailAddress()
	{
		return senderEmailAddress;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	public static class SentMessageBuilder
	{
		// Required parameters
		private String subject;
		private String recipientEmailAddress;
		private String senderEmailAddress;
		private String message;
		
		// Optional parameters
//		private String attachment;

		public SentMessageBuilder sendMessageTo(String recipientEmailAddress)
		{
			this.recipientEmailAddress = recipientEmailAddress;
			return this;
		}

		public SentMessageBuilder messageFrom(String senderEmailAddress)
		{
			this.senderEmailAddress = senderEmailAddress;
			return this;
		}

		public SentMessageBuilder messageSubject(String subject)
		{
			this.subject = subject;
			return this;
		}
		
		public SentMessageBuilder message(String message)
		{
			this.message = message;
			return this;
		}

		public SentMessage build()
		{
			return new SentMessage(this);
		}
	}
	
}
