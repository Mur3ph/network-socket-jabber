package main.ie.murph.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer
{
	private static final int PORT = 2012;
	private static ServerSocket SERVER_SOCKET_LISTENER;
	private static Socket CLIENT_SOCKET;

	public static void main(String[] args)
	{
		new MessageServer();
	}

	private MessageServer()
	{
		establishedConnection();
	} // End of myRun method.

	private void establishedConnection()
	{
		try
		{
			SERVER_SOCKET_LISTENER = connectToMachinePort();
			while (true)
			{
				CLIENT_SOCKET = acceptConnection();
				Provider connect = startCommunication();
				createThreadForEachClientCommunication(connect);
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
	}

	private ServerSocket connectToMachinePort() throws IOException
	{
		return new ServerSocket(PORT);
	}

	private Socket acceptConnection() throws IOException
	{
		return SERVER_SOCKET_LISTENER.accept();
	}
	
	private Provider startCommunication() throws IOException
	{
		return new Provider(CLIENT_SOCKET);
	}
	
	private void createThreadForEachClientCommunication(Provider connect)
	{
		Thread thread = new Thread(connect);
		thread.start();
	}

	private static void closeConnection()
	{
		try
		{
			System.out.println("Closing connection.");
			CLIENT_SOCKET.close();
		}
		catch (IOException e)
		{
			System.out.println("Unable to disconnect.");
			System.exit(1);
		}
	}

}
