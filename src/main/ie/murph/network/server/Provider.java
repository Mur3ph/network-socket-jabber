package main.ie.murph.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Provider implements Runnable
{
	private Socket link;

	public Provider(Socket socket)
	{
		link = socket;
	}

	public void run()
	{
		System.out.println("Now interacting with the client..");

		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			PrintWriter out = new PrintWriter(link.getOutputStream(), true);

			String input = in.readLine();

			while (!input.equalsIgnoreCase("exit"))
			{
				if (input.equals("hello"))
				{
					out.println("Hello from Server, innit bruv");
				}
				else
				{
					out.println("Sorry, incoorect, Type hello");
				}
			}
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
				link.close();
			}
			catch (IOException e)
			{
				System.out.println("Unable to disconnect");
				System.exit(1);
			}
		}
	} // End of Thread inherited method run()..........
}
