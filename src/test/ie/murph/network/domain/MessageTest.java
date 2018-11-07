package test.ie.murph.network.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ie.murph.network.domain.MessageUsingCreationalPattern;

public class MessageTest
{
	private MessageUsingCreationalPattern inboxMessage;
	private MessageUsingCreationalPattern sentMessage;
	
	@Before
	public void setUp() throws Exception
	{
		inboxMessage = new MessageUsingCreationalPattern.MessageBuilder().message("Hi Hanna et al..").messageFrom("Pop@mail.ie").sendMessageTo("Hanna@mail.ie").build();
		sentMessage = new MessageUsingCreationalPattern.MessageBuilder().message("Hi Hanna et al..").messageFrom("Pop@mail.ie").sendMessageTo("Hanna@mail.ie").build();
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
