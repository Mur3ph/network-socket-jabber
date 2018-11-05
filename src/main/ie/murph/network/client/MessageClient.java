package main.ie.murph.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import main.ie.murph.network.domain.Message;

public class MessageClient
{
	private final int PORT = 2012;
	private BufferedReader RESPONSE_FROM_SERVER = null;
	private PrintWriter MESSAGE_TO_SERVER = null;
	private Socket SOCKET_LINK = null;
	private final Scanner SCANNER = new Scanner(System.in);
	private Message message;

	public static void main(String[] args)
	{
		new MessageClient();
	}

	private MessageClient()
	{
		try
		{
			SOCKET_LINK = createConnection();
			RESPONSE_FROM_SERVER = createOutputStream();
			MESSAGE_TO_SERVER = createInputStream();

			String messageInput, response;
			System.out.println("Please enter your username & press enter: ");

			do
			{
				// To server
				messageInput = SCANNER.nextLine();
				message = new Message.MessageBuilder().message(messageInput).build();
				MESSAGE_TO_SERVER.println(message.getMessage());

				if (!message.getMessage().equalsIgnoreCase("exit"))
				{
					response = RESPONSE_FROM_SERVER.readLine();

					while (RESPONSE_FROM_SERVER.ready())
					{
						response += "\n" + RESPONSE_FROM_SERVER.readLine();
					}

					// From server
					System.out.println("RESONSE: " + response);
				}
			}
			while (!message.getMessage().equalsIgnoreCase("exit"));

			System.out.println("You requested session to end.");
		}
		catch (IOException e)
		{
			System.out.println("Connection to server lost.");
		}
		finally
		{
			closeConnection();
		}

		System.out.println("Ending chat.");
	} // End of my Run method

	private Socket createConnection() throws UnknownHostException, IOException
	{
		return new Socket("localhost", PORT);
	}
	
	private BufferedReader createOutputStream() throws IOException
	{
		return new BufferedReader(new InputStreamReader(SOCKET_LINK.getInputStream()));
	}

	private PrintWriter createInputStream() throws IOException
	{
		return new PrintWriter(SOCKET_LINK.getOutputStream(), true);
	}
	
	private void closeConnection()
	{
		System.out.println("Closing connection.");
		try
		{
			closeBufferedReaderRequestStream();
			closePrinterWriterResponseStream();
			closeSocketStreamConnection();
			System.out.println("Closed...");
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect..");
			System.exit(1);
		}
	} // End of close connection method...
	
	private void closeBufferedReaderRequestStream() throws IOException
	{
		if (RESPONSE_FROM_SERVER != null)
			RESPONSE_FROM_SERVER.close();
	}
	
	private void closePrinterWriterResponseStream()
	{
		if (MESSAGE_TO_SERVER != null)
			MESSAGE_TO_SERVER.close();
	}
	
	private void closeSocketStreamConnection() throws IOException
	{
		if (SOCKET_LINK != null)
			SOCKET_LINK.close();
	}

}
