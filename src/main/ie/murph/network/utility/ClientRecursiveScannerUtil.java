package main.ie.murph.network.utility;

import java.util.Scanner;

import org.apache.log4j.Logger;

import main.ie.murph.network.client.MessageClient;
import main.ie.murph.network.constants.text.IGUIRequest;

public class ClientRecursiveScannerUtil
{
	private static final Logger LOGGER = Logger.getLogger(ClientRecursiveScannerUtil.class.getSimpleName());
	private static final Scanner READ_IN_USER_INPUT = new Scanner(System.in);
	
	
	public static void askUserToContinue() throws ClassNotFoundException
	{
		LOGGER.debug("++askUserToContinue()\n");
		System.out.println(IGUIRequest.DO_YOU_WANT_TO_CONTINUE_EMAILING);

		String s_continue = READ_IN_USER_INPUT.nextLine();
		if (IGUIRequest.LETTER_Y_CONSTANT.equalsIgnoreCase(s_continue) || IGUIRequest.YES_CONSTANT.equalsIgnoreCase(s_continue))
		{
			resetMainThread();
		}
		else
			if (IGUIRequest.LETTER_N_CONSTANT.equalsIgnoreCase(s_continue) || IGUIRequest.NO_CONSTANT.equalsIgnoreCase(s_continue))
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
