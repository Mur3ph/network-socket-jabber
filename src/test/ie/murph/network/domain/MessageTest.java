package test.ie.murph.network.domain;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.ie.murph.network.domain.Message;
import main.ie.murph.network.domain.Message.MessageBuilder;

public class MessageTest
{

	private Message message;
	private MessageBuilder messageBuilder;
	
	@Before
	public void setUp() throws Exception
	{
		message = new Message.MessageBuilder().message("Hi Hanna et al..").build();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void test()
	{
		fail("Not yet implemented");
	}

}
