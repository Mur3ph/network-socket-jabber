package main.ie.murph.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import main.ie.murph.blockchain.algorithm.StringUtil;
import main.ie.murph.network.domain.Message;

public class Provider implements Runnable
{
	private Socket CLIENT_SOCKET;
//	private BufferedReader REQUEST_FROM_CLIENT;
//	private PrintWriter REPONSE_TO_CLIENT;
	private Message objInput, objOutput;

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
		catch (IOException | JSONException | ClassNotFoundException e)
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

	private void startRespondingToClient() throws IOException, JSONException, ClassNotFoundException
	{
		ObjectOutputStream outToClient = new ObjectOutputStream(CLIENT_SOCKET.getOutputStream());
//		REQUEST_FROM_CLIENT = createInputStream();
//		REPONSE_TO_CLIENT = createOutputStream();
//		// String input = readRequestFromClient();
		objInput = readObjRequestFromClient2();

		// JSONObject json = new JSONObject(REQUEST_FROM_CLIENT.readLine());
		// System.out.println("Please GOD: " + json.toString());
//		System.out.println("toString(): " + objInput.toString());
//		System.out.println("Message: " + objInput.getMessageBody());

		while (inputNotEqualToExit(objInput.getMessageBody()))
		{
			if (inputEqualsHello(objInput.getMessageBody()))
			{
//				REPONSE_TO_CLIENT.println("Hello from Server, innit bruv");
				objOutput = new Message("Live long, and prosper", "Server, correct innit bruv");
				outToClient.writeObject(objOutput);
			}
			else
			{
//				REPONSE_TO_CLIENT.println("Say hello, to my little friend");
				objOutput = new Message("Say hello to my little friend", "Server, wrong message innit bruv");
				outToClient.writeObject(objOutput);
			}
			this.startRespondingToClient();
		}
		System.out.println("You requested session to end.");
		closeConnection();
	}

	private BufferedReader createInputStream() throws IOException
	{
		return new BufferedReader(new InputStreamReader(CLIENT_SOCKET.getInputStream(), "UTF-8"));
	}

	private PrintWriter createOutputStream() throws IOException
	{
		return new PrintWriter(CLIENT_SOCKET.getOutputStream(), true);
	}

	// private String readRequestFromClient() throws IOException
	// {
	// return REQUEST_FROM_CLIENT.readLine();
	// }

//	private Message readObjRequestFromClient() throws IOException, JSONException
//	{
//		System.out.println("readObjRequestFromClient(): " + StringUtil.jsonToObj(REQUEST_FROM_CLIENT.readLine()));
//		// JSONObject json = new JSONObject(REQUEST_FROM_CLIENT.readLine());
//		// System.out.println("Please GOD: " + json.toString());
//		return StringUtil.jsonToObj(REQUEST_FROM_CLIENT.readLine());
//	}
	
	private Message readObjRequestFromClient2() throws IOException, JSONException, ClassNotFoundException
	{
		ObjectInputStream inFromClient = new ObjectInputStream(CLIENT_SOCKET.getInputStream());
		return (Message) inFromClient.readObject();
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
//			closeBufferedReaderRequestStream();
//			closePrinterWriterResponseStream();
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
//		if (REQUEST_FROM_CLIENT != null)
//			REQUEST_FROM_CLIENT.close();
	}

	private void closePrinterWriterResponseStream()
	{
//		if (REPONSE_TO_CLIENT != null)
//			REPONSE_TO_CLIENT.close();
	}

	private void closeSocketStreamConnection() throws IOException
	{
		if (CLIENT_SOCKET != null)
			CLIENT_SOCKET.close();
	}
}
