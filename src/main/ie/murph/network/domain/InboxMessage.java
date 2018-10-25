package main.ie.murph.network.domain;

public class InboxMessage implements Message
{
//	private final User user;
	private final String subject;
	private final String recipientEmailAddress;
	private final String senderEmailAddress;
	private final String message;
	
	private InboxMessage(InboxMessageBuilder inboxMessageBuilder)
	{
		this.subject = inboxMessageBuilder.subject;
		this.recipientEmailAddress = inboxMessageBuilder.recipientEmailAddress;
		this.senderEmailAddress = inboxMessageBuilder.senderEmailAddress;
		this.message = inboxMessageBuilder.message;
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
	
	public static class InboxMessageBuilder
	{
		// Required parameters
		private String subject;
		private String recipientEmailAddress;
		private String senderEmailAddress;
		private String message;
		
		// Optional parameters
//		private String attachment;

		public InboxMessageBuilder sendMessageTo(String recipientEmailAddress)
		{
			this.recipientEmailAddress = recipientEmailAddress;
			return this;
		}

		public InboxMessageBuilder messageFrom(String senderEmailAddress)
		{
			this.senderEmailAddress = senderEmailAddress;
			return this;
		}

		public InboxMessageBuilder messageSubject(String subject)
		{
			this.subject = subject;
			return this;
		}
		
		public InboxMessageBuilder message(String message)
		{
			this.message = message;
			return this;
		}

		public InboxMessage build()
		{
			return new InboxMessage(this);
		}
	}
}
