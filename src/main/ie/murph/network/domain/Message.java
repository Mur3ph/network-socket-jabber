package main.ie.murph.network.domain;

public interface Message
{
	public String getSubject();
	public String getRecipientEmailAddress();
	public String getSenderEmailAddress();
	public String getMessage();

}
