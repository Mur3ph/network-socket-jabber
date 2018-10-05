package main.ie.murph.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Provider //implements Runnable
{
	private Socket clientSocket;

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
			try
			{
				System.out.println("Closing connection");
				clientSocket.close();
			}
			catch (IOException e)
			{
				System.out.println("Unable to disconnect");
				System.exit(1);
			}
		}
	} // End of Thread inherited method run()..........

	private void startRespondingToClient() throws IOException
	{
		BufferedReader responseFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		PrintWriter requestFromClient = new PrintWriter(clientSocket.getOutputStream(), true);

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
		
	}
}
