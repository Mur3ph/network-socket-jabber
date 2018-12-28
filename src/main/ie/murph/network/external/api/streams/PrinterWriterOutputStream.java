package main.ie.murph.network.external.api.streams;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class PrinterWriterOutputStream
{
	private PrintWriter printWriter = null;
	private Socket socketConnection;

	public PrinterWriterOutputStream(Socket socketConnection)
	{
		this.socketConnection = socketConnection;
	}
	
	public PrintWriter getPrintWriter()
	{
		return printWriter;
	}

	public void setPrintWriter() throws IOException
	{
		this.printWriter = new PrintWriter(this.socketConnection.getOutputStream(), true);
	}
	
	public void connectToServer(String ip) throws IOException
	{
		int Port_Number = 9999;
		String Ip_Address = "localhost";
		System.out.println("Attempting connection... \n");
		socketConnection = new Socket(InetAddress.getByName(Ip_Address), Port_Number);
		System.out.println("Connected to:" + socketConnection.getInetAddress().getHostName());
	}

}
