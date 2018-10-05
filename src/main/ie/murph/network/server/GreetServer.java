package main.ie.murph.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer
{
	private static final int PORT = 2012;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;

	public static void main(String[] args)
	{
		new GreetServer();
	}

	private GreetServer()
	{
		try
		{
			serverSocket = new ServerSocket(PORT);

			while (true)
			{
				clientSocket = serverSocket.accept();
				Provider connect = new Provider(clientSocket);
				connect.run();
//				Thread thread = new Thread(connect);
//				thread.start();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: Won't close. " + e);
		}
		finally
		{
			closeConnection();
		}
	} // End of myRun method.

	private static void closeConnection()
	{
		try
		{
			System.out.println("Closing connection.");
			clientSocket.close();
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect.");
			System.exit(1);
		}
	}

}
