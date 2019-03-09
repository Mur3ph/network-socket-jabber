package main.ie.murph.network.gui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.constants.text.EDebugMessage;
import main.ie.murph.network.constants.text.IGUIRequest;

public class ServerCommunication {

	private static final Logger LOGGER = LogManager.getLogger(ServerCommunication.class.getName());
	private Socket link;
	private BufferedReader in;
	private PrintWriter out;

	public void userInteraction() {
		System.out.println("Now interacting with the client..");
		try {
		     in = new BufferedReader(new InputStreamReader(
			    link.getInputStream()));
		     out = new PrintWriter(link.getOutputStream(), true);

		    String input = "";

		    while (!IGUIRequest.EXIT.equalsIgnoreCase(input = in.readLine())) {
				if (input.equals("login")) {
				    out.println("Type your username to start");
//				    input = in.readLine();
			    } 
		    }
	} catch (IOException e) {
		LOGGER.info("++communicateWithClient(): " + EDebugMessage.CONNECTION_LOST);
		LOGGER.error("++communicateWithClient(): " + EDebugMessage.SERVER_ERROR + e.getMessage());
		LOGGER.error("++communicateWithClient(): " + EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
		LOGGER.error("++communicateWithClient(): " + EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
		LOGGER.error("++communicateWithClient(): " + EDebugMessage.EXCEPTION_STRING + e.toString());
	} finally {
		LOGGER.info("--communicateWithClient(): " + EDebugMessage.CONNECTION_CLOSED);
	}
	}

}
