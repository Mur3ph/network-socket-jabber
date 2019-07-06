package main.ie.murph.network.communicator.external.api.streams;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import main.ie.murph.network.communicator.constants.text.INetwork;

public class MessageSocket
{
	private Socket socket;
	
	public void setSocket() throws UnknownHostException, IOException
	{
		socket =  new Socket(INetwork.SPECIFIED_IP_ADDRESS, INetwork.SPECIFIED_PORT_NUMBER);
	}
	
	public Socket getSocket()
	{
		return this.socket;
	}
	
	public void closeSocket() throws IOException
	{
		if(this.socket != null)
		{
			this.socket.close();
		}
	}
}
