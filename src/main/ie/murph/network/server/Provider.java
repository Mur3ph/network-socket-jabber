package main.ie.murph.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import main.ie.murph.network.domain.Message;

public class Provider implements Runnable
{
	private Socket CLIENT_SOCKET;
	private Message OBJECT_PASSED_IN, OBJECT_PASSED_OUT;
	private ObjectInputStream STREAM_IN_FROM_CLIENT;
	private ObjectOutputStream STREAM_OUT_TO_CLIENT;

	public Provider(Socket socket) throws IOException
	{
		CLIENT_SOCKET = socket;
		STREAM_OUT_TO_CLIENT = createObjectOutputStream();
		STREAM_IN_FROM_CLIENT = createObjectInputStream();
	}

	public void run()
	{
		System.out.println("Now interacting with the client..");
		try
		{
			startRespondingToClient();
		}
		catch (IOException | ClassNotFoundException e)
		{
			System.out.println("Connection to client lost");
			System.err.println("Server Error: " + e.getMessage());
			System.err.println("Localized: " + e.getLocalizedMessage());
			System.err.println("Stack Trace: " + e.getStackTrace());
			System.err.println("To String: " + e.toString());
		}
		finally
		{
			System.out.println("Connection closed...");
			closeConnection();
		}
	} // End of Thread inherited method run()..........

	private void startRespondingToClient() throws IOException, ClassNotFoundException
	{
		OBJECT_PASSED_IN = readObjRequestFromClient();

		while (inputNotEqualToExit(OBJECT_PASSED_IN.getMessageBody()))
		{
			if (inputEqualsHello(OBJECT_PASSED_IN.getMessageBody()))
			{
				OBJECT_PASSED_OUT = new Message("Live long, and prosper", OBJECT_PASSED_IN.getMessageBody() + " is correct, innit bruv");
				STREAM_OUT_TO_CLIENT.writeObject(OBJECT_PASSED_OUT);
			}
			else
			{
				OBJECT_PASSED_OUT = new Message("Say hello to my little friend", OBJECT_PASSED_IN.getMessageBody() + " is the wrong message, innit bruv");
				STREAM_OUT_TO_CLIENT.writeObject(OBJECT_PASSED_OUT);
			}
			this.startRespondingToClient();
		}
		System.out.println("You requested session to end.");
		closeConnection();
	}

	private ObjectOutputStream createObjectOutputStream() throws IOException
	{
		return new ObjectOutputStream(CLIENT_SOCKET.getOutputStream());
	}

	private ObjectInputStream createObjectInputStream() throws IOException
	{
		return new ObjectInputStream(CLIENT_SOCKET.getInputStream());
	}

	private Message readObjRequestFromClient() throws IOException, ClassNotFoundException
	{
		return (Message) STREAM_IN_FROM_CLIENT.readObject();
	}

	private boolean inputNotEqualToExit(String input)
	{
		return !input.equalsIgnoreCase("exit");
	}

	public boolean inputEqualsHello(String input)
	{
		return input.equals("hello");
	}

	private void closeConnection()
	{
		System.out.println("Closing connection...");
		try
		{
			closeBufferedReaderRequestStream();
			closePrinterWriterResponseStream();
			closeSocketStreamConnection();
			System.out.println("Connection closing...");
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect..");
			System.exit(1);
		}
	} // End of close connection method...

	private void closeBufferedReaderRequestStream() throws IOException
	{
		if (STREAM_IN_FROM_CLIENT != null)
			STREAM_IN_FROM_CLIENT.close();
	}

	private void closePrinterWriterResponseStream() throws IOException
	{
		if (STREAM_OUT_TO_CLIENT != null)
			STREAM_OUT_TO_CLIENT.close();
	}

	private void closeSocketStreamConnection() throws IOException
	{
		if (CLIENT_SOCKET != null)
			CLIENT_SOCKET.close();
	}
}
