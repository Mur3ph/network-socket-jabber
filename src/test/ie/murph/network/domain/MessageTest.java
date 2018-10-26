package test.ie.murph.network.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ie.murph.network.domain.Message;

public class MessageTest
{
	private Message inboxMessage;
	private Message sentMessage;
	
	@Before
	public void setUp() throws Exception
	{
		inboxMessage = new Message.MessageBuilder().message("Hi Hanna et al..").messageFrom("Pop@mail.ie").sendMessageTo("Hanna@mail.ie").build();
		sentMessage = new Message.MessageBuilder().message("Hi Hanna et al..").messageFrom("Pop@mail.ie").sendMessageTo("Hanna@mail.ie").build();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void messageTest()
	{
		String expectedMessage = "Hi Hanna et al..";
		assertEquals(expectedMessage, inboxMessage.getMessage());
	}
	
	@Test
	public void inboxMessageTest()
	{
		String expectedMessage = "Hi Hanna et al..";
		assertEquals(expectedMessage, sentMessage.getMessage());
	}
	
	@Test
	public void objectInstanceTest()
	{
//		Assert.isInstanceOf(message, inboxMessage);
//		assertThat(message, instanceOf(inboxMessage));
//		assertTrue(Message.class, instanceOf(inboxMessage));
	}
	
	

}
