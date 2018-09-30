package main.ie.murph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
	
	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    private final String CLIENT_VALIDATION_MESSAGE_FROM_CLIENT = "hello server: ";
 
    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        replyToClient();
    }
 
    private void replyToClient() throws IOException {
    	if (CLIENT_VALIDATION_MESSAGE_FROM_CLIENT.equals(getReplyMessage(input))) {
            output.println("hello client, from server xx");
        }
        else {
            output.println("unrecognised greeting");
        }
	}

	private String getReplyMessage(BufferedReader in2) throws IOException {
		return input.readLine();
	}

	public void stop() throws IOException {
        input.close();
        output.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        GreetServer server=new GreetServer();
        server.start(6666);
    }

}
