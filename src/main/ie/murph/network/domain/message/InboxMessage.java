package main.ie.murph.network.domain.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class InboxMessage implements Message, Serializable
{
	private static final Logger LOGGER = LogManager.getLogger(InboxMessage.class.getName());
	private static final long serialVersionUID = 1L;
    private List<Message> childMessages;
 
    public InboxMessage() {
        this.childMessages = new ArrayList<>();
    }
 
    @Override
	public void showMessageToString() {
    	LOGGER.info("++removeDepartment()");
    	childMessages.forEach(Message::showMessageToString);
    }
    
	@Override
	public void printessageName()
	{
		LOGGER.info("++removeDepartment()");
		childMessages.forEach(Message::printessageName);
	}
 
    public void addDepartment(InboxMessage department) {
    	LOGGER.info("++removeDepartment()");
    	childMessages.add(department);
    }
 
    public void removeDepartment(InboxMessage department) {
    	LOGGER.info("++removeDepartment()");
    	childMessages.remove(department);
    }

}
