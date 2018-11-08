package main.ie.murph.network.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import main.ie.murph.network.domain.Message;

public class MessageClient
{
	private final int PORT = 2012;
	ObjectOutputStream OUTPUT_TO_SERVER;
	ObjectInputStream INPUT_FROM_SERVER;
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
			System.out.println("Please enter your username & press enter: ");

			do
			{
				// To server
				messageScannerInput = SCANNER.nextLine();
				messageREQUEST = new Message("Greetings", messageScannerInput);
				OUTPUT_TO_SERVER.writeObject(messageREQUEST);

				if (!messageREQUEST.getMessageBody().equalsIgnoreCase("exit"))
				{
					messageResponse = (Message) INPUT_FROM_SERVER.readObject();

					// From server
					System.out.println("RESONSE: " + messageResponse.toString());
				}
			}
			while (!messageREQUEST.getMessageBody().equalsIgnoreCase("exit"));

			System.out.println("You requested session to end.");
		}
		catch (IOException e)
		{
			System.out.println("Connection to server lost.");
			System.err.println("Server Error: " + e.getMessage());
			System.err.println("Localized: " + e.getLocalizedMessage());
			System.err.println("Stack Trace: " + e.getStackTrace());
			System.err.println("To String: " + e.toString());
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
			System.out.println("Unable to disconnect..");
			System.err.println("Server Error: " + e.getMessage());
			System.err.println("Localized: " + e.getLocalizedMessage());
			System.err.println("Stack Trace: " + e.getStackTrace());
			System.err.println("To String: " + e.toString());
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
