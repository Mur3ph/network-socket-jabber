package ie.murph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GreetClient {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private final String IP_ADDRESS = "127.0.0.1";
    
    public void start(int port) throws IOException {
    	this.startConnection(IP_ADDRESS, port);
	    String response = this.sendMessage("hello server: ");
	    System.out.println("hello client: " + response);
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
 
    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

}
