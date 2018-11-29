package main.ie.murph.network.gui;

public enum EClientGUIRequest
{
	WELCOME("Hello, Welcome to Spudy Email Messager"),
	QUESTIONTOPLAYER("Would you like to read your inbox messages!?"),
	QUESTIONTODEALER("Would you like to sned a message!?"),
	GOODBYE("Thank you, good bye!");
	
	private final String text;

    private EClientGUIRequest(final String text) 
    {
        this.text = text;
    }

    @Override
    public String toString() 
    {
        return this.text;
}
}
