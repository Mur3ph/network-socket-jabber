package main.ie.murph.network.external.api.streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import main.ie.murph.network.domain.message.MessageDefault;

public class ObjectStream
{
	private OutputStream objectOutputStream;
	private InputStream objectInputStream;

	public ObjectStream(OutputStream outputStream) throws IOException
	{
		objectOutputStream = new ObjectOutputStream(outputStream);
	}

	public ObjectStream(InputStream inputStream) throws IOException
	{
		objectInputStream = new ObjectInputStream(inputStream);
	}

	public void sendObjectRequest(Object object) throws IOException
	{
		((ObjectOutputStream) objectOutputStream).writeObject(object);
	}

	public MessageDefault receiveObjectResponse() throws ClassNotFoundException, IOException
	{
		return (MessageDefault) ((ObjectInputStream) objectInputStream).readObject();
	}
	
	public void flushOutputStream() throws IOException
	{
		objectOutputStream.flush();
	}

	public void closeOutputStream() throws IOException
	{
		objectOutputStream.close();
	}

	public void closeInputStream() throws IOException
	{
		objectInputStream.close();
	}
}
