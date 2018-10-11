package main.ie.murph.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GreetClient
{
	private final int PORT = 2012;
	private BufferedReader RESPONSE_FROM_SERVER = null;
	private PrintWriter output = null;
	private Socket link = null;
	private Scanner SCANNER = new Scanner(System.in);

	public static void main(String[] args)
	{
		new GreetClient();
	}

	private GreetClient()
	{
		try
		{
			link = new Socket("localhost", PORT);

			RESPONSE_FROM_SERVER = new BufferedReader(new InputStreamReader(link.getInputStream()));
			output = new PrintWriter(link.getOutputStream(), true);

			String message = null, response = null;
			System.out.println("Please enter your username & press enter: ");

			do
			{
				// To server
				message = SCANNER.nextLine();
				output.println("OUTPUT: " + message);

				if (!message.equalsIgnoreCase("exit"))
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
			while (!message.equalsIgnoreCase("exit"));

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

	private void closeConnection()
	{
		System.out.println("Closing connection.");

		try
		{
			if (RESPONSE_FROM_SERVER != null)
				RESPONSE_FROM_SERVER.close();

			if (output != null)
				output.close();

			if (link != null)
				link.close();

			System.out.println("Closed...");
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect..");
			System.exit(1);
		}
	} // End of close connection method...

}
