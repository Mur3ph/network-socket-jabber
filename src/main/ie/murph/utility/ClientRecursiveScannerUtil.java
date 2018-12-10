package main.ie.murph.utility;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.ie.murph.network.client.MessageClient;
import main.ie.murph.network.gui.IGUIRequest;

public class ClientRecursiveScannerUtil
{
	private static final Logger LOGGER = Logger.getLogger(ClientRecursiveScannerUtil.class);
	private static final Scanner READ_IN_USER_INPUT = new Scanner(System.in);
	
	public static void askUserToContinue() throws ClassNotFoundException
	{
		LOGGER.debug("++askUserToContinue()\n");
		System.out.println(IGUIRequest.DO_YOU_WANT_TO_CONTINUE_EMAILING);

		String s_continue = READ_IN_USER_INPUT.nextLine();
		if (s_continue.equalsIgnoreCase("y") || s_continue.equalsIgnoreCase("yes"))
		{
			resetMainThread();
		}
		else
			if (s_continue.equalsIgnoreCase("n") || s_continue.equalsIgnoreCase("no"))
			{
				exit();
			}
			else
				askUserToContinue();
		LOGGER.debug("--askUserToContinue()\n");
	}
	
	private static void resetMainThread() throws ClassNotFoundException
	{
		LOGGER.debug("++resetMainThread()\n");
		MessageClient.main(new String[0]);
		LOGGER.debug("--resetMainThread()\n");
	}
	
	private static void exit()
	{
		LOGGER.debug("++exit()\n");
		System.out.println(IGUIRequest.GOODBYE);
		System.exit(0);
		LOGGER.debug("--exit()\n");
	}

}
