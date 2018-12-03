package main.ie.murph.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import main.ie.murph.network.gui.EErrorMessage;
import main.ie.murph.network.gui.INetwork;

public class MessageServer
{
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
			System.out.println(EErrorMessage.CANNOT_DISCONNECT + " " + e);
			System.err.println(EErrorMessage.SERVER_ERROR + e.getMessage());
			System.err.println(EErrorMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			System.err.println(EErrorMessage.STACK_TRACE + " " + e.getStackTrace());
			System.err.println(EErrorMessage.EXCEPTION_STRING + e.toString());
		}
		finally
		{
			closeConnection();
		}
	}

	private ServerSocket connectToMachinePort() throws IOException
	{
		return new ServerSocket(INetwork.SPECIFIED_PORT_NUMBER);
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
			System.out.println(EErrorMessage.CONNECTION_CLOSING);
			CLIENT_SOCKET.close();
		}
		catch (IOException e)
		{
			System.out.println(EErrorMessage.UNABLE_TO_DISCONNECT);
			System.exit(1);
		}
	}

}
