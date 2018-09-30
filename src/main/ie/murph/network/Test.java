package main.ie.murph.network;

import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws IOException {
	    GreetClient client = new GreetClient();
	    client.start(6666);
	}

}
