package main.ie.murph.network;

import java.io.IOException;

import main.ie.murph.network.client.GreetClient;

public class run {
	
	public static void main(String[] args) throws IOException {
	    GreetClient client = new GreetClient();
	    client.start(6666);
	}

}
