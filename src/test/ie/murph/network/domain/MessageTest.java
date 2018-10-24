package test.ie.murph.network.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ie.murph.network.domain.InboxMessage;
import main.ie.murph.network.domain.Message;

public class MessageTest
{
	private Message message;
	private InboxMessage inboxMessage;
	
	@Before
	public void setUp() throws Exception
	{
		message = new Message.MessageBuilder().message("Hi Hanna et al..").messageFrom("Daddy").sendMessageTo("Hanna").build();
		inboxMessage = new InboxMessage.InboxMessageBuilder().message("Hi Hanna et al..").messageFrom("Daddy").sendMessageTo("Hanna").build();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void messageTest()
	{
		String expectedMessage = "Hi Hanna et al..";
		assertEquals(expectedMessage, message.getMessage());
	}
	
	@Test
	public void inboxMessageTest()
	{
		String expectedMessage = "Hi Hanna et al..";
		assertEquals(expectedMessage, inboxMessage.getMessage());
	}
	
	@Test
	public void objectInstanceTest()
	{
//		Assert.isInstanceOf(message, inboxMessage);
//		assertThat(message, instanceOf(inboxMessage));
	}
	
	

}
