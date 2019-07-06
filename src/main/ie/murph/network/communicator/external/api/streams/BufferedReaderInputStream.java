package main.ie.murph.network.communicator.external.api.streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class BufferedReaderInputStream
{
	private BufferedReader bufferedReader;
	
	public BufferedReaderInputStream(){}

	public BufferedReader getBufferedReader()
	{
		return bufferedReader;
	}

	public void setBufferedReader(Socket socketConnection) throws IOException
	{
		this.bufferedReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
	}
}
