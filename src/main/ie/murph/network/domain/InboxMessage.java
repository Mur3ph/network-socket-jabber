package main.ie.murph.network.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InboxMessage implements Message, Serializable
{
	private static final long serialVersionUID = 1L;
 
    private List<Message> childMessages;
 
    public InboxMessage() {
        this.childMessages = new ArrayList<>();
    }
 
    @Override
	public void showMessageToString() {
    	childMessages.forEach(Message::showMessageToString);
    }
    
	@Override
	public void printessageName()
	{
		childMessages.forEach(Message::printessageName);
	}
 
    public void addDepartment(InboxMessage department) {
    	childMessages.add(department);
    }
 
    public void removeDepartment(InboxMessage department) {
    	childMessages.remove(department);
    }

}
