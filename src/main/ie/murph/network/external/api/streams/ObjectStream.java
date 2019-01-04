package main.ie.murph.network.external.api.streams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
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
//		FileOutputStream file = new FileOutputStream("myobject.ser");
//	    BufferedOutputStream bout = new BufferedOutputStream(file);
//	    ObjectOutputStream out = new ObjectOutputStream(bout);
//		objectOutputStream = new ObjectOutputStream(outputStream);
//		objectOutputStream = new BufferedOutputStream(objectOutputStream);
		
//		objectOutputStream = new BufferedOutputStream(outputStream);
//		objectOutputStream = new ObjectOutputStream(objectOutputStream);
		
		objectOutputStream = new ObjectOutputStream(outputStream);
	}

	public ObjectStream(InputStream inputStream) throws IOException
	{
//		objectInputStream = new ObjectInputStream(inputStream);
//		objectInputStream = new BufferedInputStream(objectInputStream);
		
//		objectInputStream = new BufferedInputStream(inputStream);
//		objectInputStream = new ObjectInputStream(objectInputStream);
		
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
