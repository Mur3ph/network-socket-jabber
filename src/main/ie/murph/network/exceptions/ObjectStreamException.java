package main.ie.murph.network.exceptions;

public class ObjectStreamException extends Exception
{
	private static final long serialVersionUID = 7718828512143293558L;

	public ObjectStreamException()
	{
		super();
	}

	public ObjectStreamException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ObjectStreamException(String message)
	{
		super(message);
	}

	public ObjectStreamException(Throwable cause)
	{
		super(cause);
	}
}
