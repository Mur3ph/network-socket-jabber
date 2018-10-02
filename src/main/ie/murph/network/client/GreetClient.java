package main.ie.murph.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GreetClient {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
//    private final String IP_ADDRESS = "127.0.0.1";
    private final String MESSAGE_TO_SERVER = "hello server: ";
    private Scanner SCANNER = new Scanner(System.in);
    
    public void start(int port, String ipAddress) throws IOException {
    	this.startConnection(ipAddress, port);
	    String messageResponse = this.sendMessage(MESSAGE_TO_SERVER);
	    System.out.println("Server Reply: " + messageResponse);

	    String userInput;
	    
	    do {
			// From server
	    	messageResponse = in.readLine();
			while (in.ready()) {
				messageResponse += "\n" + in.readLine();
			}
			System.out.println(messageResponse);
			
			// To server
			userInput = SCANNER.nextLine();
			out.println(userInput);
	} while (messageIsNotExit(userInput));
    }
 
    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
    
    private boolean messageIsNotExit(String response)
	{
		return !response.equalsIgnoreCase("exit");
	}

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

}
