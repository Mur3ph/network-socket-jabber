package main.ie.murph.network.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectStream
{
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	public ObjectStream(InputStream inputStream, OutputStream outputStream) throws IOException
	{
		objectOutputStream = new ObjectOutputStream(outputStream);
		objectInputStream = new ObjectInputStream(inputStream);
	}
	
	public void sendObject(Object object) throws IOException
	{
		objectOutputStream.writeObject(object);
	}
	
	public void receiveObject() throws ClassNotFoundException, IOException
	{
		objectInputStream.readObject();
	}
}
