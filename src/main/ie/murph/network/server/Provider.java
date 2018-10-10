package main.ie.murph.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Provider implements Runnable
{
	private Socket CLIENT_SOCKET;
	private BufferedReader REQUEST_FROM_CLIENT;
	private PrintWriter REPONSE_TO_CLIENT;

	public Provider(Socket socket)
	{
		CLIENT_SOCKET = socket;
	}

	public void run()
	{
		System.out.println("Now interacting with the client..");

		try
		{
			startRespondingToClient();
		}
		catch (IOException e)
		{
			System.out.println("Connection to client lost");
		}
		finally
		{
			System.out.println("Connection closed..");
		}
	} // End of Thread inherited method run()..........

	private void startRespondingToClient() throws IOException
	{
		REQUEST_FROM_CLIENT = createInputStream();
		REPONSE_TO_CLIENT = createOutputStream();

		String input = REQUEST_FROM_CLIENT.readLine();

		while (!input.equalsIgnoreCase("exit"))
		{
			if (input.equals("hello"))
			{
				REPONSE_TO_CLIENT.println("Hello from Server, innit bruv");
				this.startRespondingToClient();
			}
			else
			{
				REPONSE_TO_CLIENT.println("Say hello, to my little friend");
				this.startRespondingToClient();
			}
		}
		System.out.println("You requested session to end.");
		closeConnection();
	}

	private BufferedReader createInputStream() throws IOException
	{
		return new BufferedReader(new InputStreamReader(CLIENT_SOCKET.getInputStream()));
	}
	
	private PrintWriter createOutputStream() throws IOException
	{
		return new PrintWriter(CLIENT_SOCKET.getOutputStream(), true);
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
		if (REQUEST_FROM_CLIENT != null)
			REQUEST_FROM_CLIENT.close();
	}
	
	private void closePrinterWriterResponseStream()
	{
		if (REPONSE_TO_CLIENT != null)
			REPONSE_TO_CLIENT.close();
	}
	
	private void closeSocketStreamConnection() throws IOException
	{
		if (CLIENT_SOCKET != null)
			CLIENT_SOCKET.close();
	}
}
