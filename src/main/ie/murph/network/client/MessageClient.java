package main.ie.murph.network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import main.ie.murph.network.domain.Message;
import main.ie.murph.network.gui.EErrorMessage;
import main.ie.murph.network.gui.IGUIRequest;
import main.ie.murph.network.gui.INetwork;

public class MessageClient
{
	private ObjectOutputStream OUTPUT_TO_SERVER;
	private ObjectInputStream INPUT_FROM_SERVER;
	private Socket SOCKET_LINK = null;
	private final Scanner SCANNER = new Scanner(System.in);
	private Message messageREQUEST, messageResponse;

	public static void main(String[] args) throws ClassNotFoundException
	{
		new MessageClient();
	}

	private MessageClient() throws ClassNotFoundException
	{
		try
		{
			SOCKET_LINK = createConnection();
			INPUT_FROM_SERVER = createObjectInputStream();
			OUTPUT_TO_SERVER = createObjectOutputStream();

			String messageScannerInput = null;
			System.out.println(IGUIRequest.WELCOME);

			do
			{
				// To server
				messageScannerInput = SCANNER.nextLine();
				messageREQUEST = new Message("Greetings", messageScannerInput);
				OUTPUT_TO_SERVER.writeObject(messageREQUEST);

				if (!messageREQUEST.getMessageBody().equalsIgnoreCase(IGUIRequest.EXIT))
				{
					messageResponse = (Message) INPUT_FROM_SERVER.readObject();

					// From server
					System.out.println(IGUIRequest.SERVER_RESPONSE + messageResponse.toString());
				}
			}
			while (!messageREQUEST.getMessageBody().equalsIgnoreCase(IGUIRequest.EXIT));

			System.out.println(IGUIRequest.GOODBYE);
		}
		catch (IOException e)
		{
			System.out.println(EErrorMessage.CONNECTION_LOST);
			System.err.println(EErrorMessage.SERVER_ERROR + e.getMessage());
			System.err.println(EErrorMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			System.err.println(EErrorMessage.STACK_TRACE + " " + e.getStackTrace());
			System.err.println(EErrorMessage.EXCEPTION_STRING + e.toString());
		}
		finally
		{
			closeConnection();
		}

		System.out.println("Ending chat.");
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
			System.out.println(EErrorMessage.CANNOT_DISCONNECT);
			System.err.println(EErrorMessage.SERVER_ERROR + e.getMessage());
			System.err.println(EErrorMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			System.err.println(EErrorMessage.STACK_TRACE + " " + e.getStackTrace());
			System.err.println(EErrorMessage.EXCEPTION_STRING + e.toString());
			System.exit(1);
		}
	} // End of close connection method...

	private void closeBufferedReaderRequestStream() throws IOException
	{
		if (INPUT_FROM_SERVER != null)
			INPUT_FROM_SERVER.close();
	}

	private void closePrinterWriterResponseStream() throws IOException
	{
		if (OUTPUT_TO_SERVER != null)
			OUTPUT_TO_SERVER.close();
	}

	private void closeSocketStreamConnection() throws IOException
	{
		if (SOCKET_LINK != null)
			SOCKET_LINK.close();
	}

}
