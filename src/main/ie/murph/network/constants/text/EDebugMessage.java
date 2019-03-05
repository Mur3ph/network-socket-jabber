package main.ie.murph.network.constants.text;

public enum EDebugMessage
{
	CONNECTION_LOST("Connection to server lost: "),
	SERVER_ERROR("Server Error: "),
	LOCALIZED_ERROR("Localized Error: "),
	STACK_TRACE("Stack Trace Error: "),
	EXCEPTION_STRING("Exception ToString(): "),
	CANNOT_DISCONNECT("Unable to disconnect: "),
	CONNECTION_CLOSED("Connection closed."),
	CONNECTION_CLOSING("Closing connection..."),
	UNABLE_TO_DISCONNECT("Unable to disconnect.."),
	ENDING_CHAT("Ending chat."),
	REQUEST_TO_END_SESSION("You requested session to end");
	
	private final String text;

    private EDebugMessage(final String text) 
    {
        this.text = text;
    }

    @Override
    public String toString() 
    {
        return this.text;
}
}
