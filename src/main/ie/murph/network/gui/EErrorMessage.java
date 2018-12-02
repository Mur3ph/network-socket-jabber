package main.ie.murph.network.gui;

public enum EErrorMessage
{
	CONNECTION_LOST("Connection to server lost: "),
	SERVER_ERROR("Server Error: "),
	LOCALIZED_ERROR("Localized Error: "),
	STACK_TRACE("Stack Trace Error: "),
	EXCEPTION_STRING("Exception ToString(): "),
	CANNOT_DISCONNECT("Unable to disconnect: ");
	
	private final String text;

    private EErrorMessage(final String text) 
    {
        this.text = text;
    }

    @Override
    public String toString() 
    {
        return this.text;
}
}
