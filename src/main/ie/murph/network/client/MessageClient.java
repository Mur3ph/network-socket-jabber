package main.ie.murph.network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import main.ie.murph.network.domain.message.MessageDefault;
import main.ie.murph.network.gui.EDebugMessage;
import main.ie.murph.network.gui.IGUIRequest;
import main.ie.murph.network.gui.INetwork;

public class MessageClient
{
	private ObjectOutputStream REQUEST_TO_SERVER;
	private ObjectInputStream RESPONSE_FROM_SERVER;
	private Socket SOCKET_LINK = null;
	private final Scanner SCANNER = new Scanner(System.in);
	private MessageDefault messageREQUEST, messageResponse;

	public static void main(String[] args) throws ClassNotFoundException
	{
		new MessageClient();
	}

	private MessageClient() throws ClassNotFoundException
	{
		try
		{
			SOCKET_LINK = createConnection();
			RESPONSE_FROM_SERVER = createObjectInputStream();
			REQUEST_TO_SERVER = createObjectOutputStream();

			String messageScannerInput = null;
			System.out.println(IGUIRequest.REQUEST_USERNAME_LOGIN);

			do
			{
				// To server
				messageScannerInput = SCANNER.nextLine();
				messageREQUEST = new MessageDefault(IGUIRequest.GREETINGS, messageScannerInput);
				REQUEST_TO_SERVER.writeObject(messageREQUEST);

				if (!messageREQUEST.getMessageBody().equalsIgnoreCase(IGUIRequest.EXIT))
				{
					messageResponse = (MessageDefault) RESPONSE_FROM_SERVER.readObject();

					// From server
					System.out.println(IGUIRequest.SERVER_RESPONSE + messageResponse.toString());
				}
			}
			while (!IGUIRequest.EXIT.equalsIgnoreCase(messageREQUEST.getMessageBody()));

			System.out.println(IGUIRequest.GOODBYE);
		}
		catch (IOException e)
		{
			System.out.println(EDebugMessage.CONNECTION_LOST);
			System.err.println(EDebugMessage.SERVER_ERROR + e.getMessage());
			System.err.println(EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			System.err.println(EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
			System.err.println(EDebugMessage.EXCEPTION_STRING + e.toString());
		}
		finally
		{
			closeConnection();
		}

		System.out.println(EDebugMessage.ENDING_CHAT);
	} // End of my Run method

	private Socket createConnection() throws UnknownHostException, IOException
	{
		return new Socket(INetwork.SPECIFIED_IP_ADDRESS, INetwork.SPECIFIED_PORT_NUMBER);
	}

	private ObjectInputStream createObjectInputStream() throws IOException
	{
		return new ObjectInputStream(SOCKET_LINK.getInputStream());
	}

	private ObjectOutputStream createObjectOutputStream() throws IOException
	{
		return new ObjectOutputStream(SOCKET_LINK.getOutputStream());
	}

	private void closeConnection()
	{
		System.out.println(EDebugMessage.CONNECTION_CLOSING);
		try
		{
			closeBufferedReaderRequestStream();
			closePrinterWriterResponseStream();
			closeSocketStreamConnection();
			System.out.println("Closed...");
		}
		catch (IOException e)
		{
			System.out.println(EDebugMessage.CANNOT_DISCONNECT);
			System.err.println(EDebugMessage.SERVER_ERROR + e.getMessage());
			System.err.println(EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			System.err.println(EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
			System.err.println(EDebugMessage.EXCEPTION_STRING + e.toString());
			System.exit(1);
		}
	} // End of close connection method...

	private void closeBufferedReaderRequestStream() throws IOException
	{
		if (RESPONSE_FROM_SERVER != null)
			RESPONSE_FROM_SERVER.close();
	}

	private void closePrinterWriterResponseStream() throws IOException
	{
		if (REQUEST_TO_SERVER != null)
			REQUEST_TO_SERVER.close();
	}

	private void closeSocketStreamConnection() throws IOException
	{
		if (SOCKET_LINK != null)
			SOCKET_LINK.close();
	}

}
