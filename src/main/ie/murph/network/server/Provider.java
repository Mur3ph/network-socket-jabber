package main.ie.murph.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Provider implements Runnable
{
	private Socket clientSocket;
	private BufferedReader responseFromClient;
	private PrintWriter requestFromClient;

	public Provider(Socket socket)
	{
		clientSocket = socket;
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
			System.out.println("Closing connection");
			closeConnection();
		}
	} // End of Thread inherited method run()..........

	private void startRespondingToClient() throws IOException
	{
		responseFromClient = createInputStream();
		requestFromClient = createOutputStream();

		String input = responseFromClient.readLine();

		while (!input.equalsIgnoreCase("exit"))
		{
			if (input.equals("hello"))
			{
				requestFromClient.println("Hello from Server, innit bruv");
				this.startRespondingToClient();
			}
			else
			{
				requestFromClient.println("Say hello, to my little friend");
				this.startRespondingToClient();
			}
		}
		System.out.println("You requested session to end.");
		closeConnection();
	}

	private BufferedReader createInputStream() throws IOException
	{
		return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}
	
	private PrintWriter createOutputStream() throws IOException
	{
		return new PrintWriter(clientSocket.getOutputStream(), true);
	}
	
	private void closeConnection()
	{
		System.out.println("Closing connection.");

		try
		{
			if (responseFromClient != null)
				responseFromClient.close();

			if (requestFromClient != null)
				requestFromClient.close();

			if (clientSocket != null)
				clientSocket.close();

			System.out.println("Closed...");
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect..");
			System.exit(1);
		}
	} // End of close connection method...
}
