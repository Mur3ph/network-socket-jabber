package main.ie.murph.network.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.constants.text.EDebugMessage;
import main.ie.murph.network.constants.text.IGUIRequest;
import main.ie.murph.network.domain.message.MessageDefault;

public class GreetingProvider implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger(GreetingProvider.class.getName());
	private Socket CLIENT_SOCKET;
	private MessageDefault OBJECT_PASSED_IN, OBJECT_PASSED_OUT;
	private ObjectInputStream STREAM_IN_FROM_CLIENT;
	private ObjectOutputStream STREAM_OUT_TO_CLIENT;

	public GreetingProvider(Socket socket) throws IOException {
		CLIENT_SOCKET = socket;
		STREAM_OUT_TO_CLIENT = createObjectOutputStream();
		STREAM_IN_FROM_CLIENT = createObjectInputStream();
	}

	public void run() {
		communicateWithClient();
	} // End of Thread inherited method run()..........

	private void communicateWithClient() {
		LOGGER.info("++communicateWithClient(): " + IGUIRequest.INTERACTION_WITH_SERVER);
		try {
			startRespondingToClient();
		} catch (IOException | ClassNotFoundException e) {
			LOGGER.info("++communicateWithClient(): " + EDebugMessage.CONNECTION_LOST);
			LOGGER.error("++communicateWithClient(): " + EDebugMessage.SERVER_ERROR + e.getMessage());
			LOGGER.error("++communicateWithClient(): " + EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			LOGGER.error("++communicateWithClient(): " + EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
			LOGGER.error("++communicateWithClient(): " + EDebugMessage.EXCEPTION_STRING + e.toString());
		} finally {
			LOGGER.info("--communicateWithClient(): " + EDebugMessage.CONNECTION_CLOSED);
			closeConnection();
		}

	}

	private void startRespondingToClient() throws IOException, ClassNotFoundException {
		OBJECT_PASSED_IN = readObjRequestFromClient();

		while (inputNotEqualToExit(OBJECT_PASSED_IN.getMessageBody())) {
			greetingsGame();
			this.startRespondingToClient();
		}
		LOGGER.info("--startRespondingToClient(): " + EDebugMessage.REQUEST_TO_END_SESSION);
		closeConnection();
	}

	private void greetingsGame() throws IOException {
		if (inputEqualsHello(OBJECT_PASSED_IN.getMessageBody())) {
			OBJECT_PASSED_OUT = new MessageDefault(IGUIRequest.STAR_TREK_QUOTE,
					OBJECT_PASSED_IN.getMessageBody() + IGUIRequest.CORRECT);
			STREAM_OUT_TO_CLIENT.writeObject(OBJECT_PASSED_OUT);
			STREAM_OUT_TO_CLIENT.flush();
		} else {
			OBJECT_PASSED_OUT = new MessageDefault(IGUIRequest.SCARFACE_QUOTE,
					OBJECT_PASSED_IN.getMessageBody() + IGUIRequest.INCORRECT);
			STREAM_OUT_TO_CLIENT.writeObject(OBJECT_PASSED_OUT);
			STREAM_OUT_TO_CLIENT.flush();
		}
	}

	private ObjectOutputStream createObjectOutputStream() throws IOException {
		return new ObjectOutputStream(CLIENT_SOCKET.getOutputStream());
	}

	private ObjectInputStream createObjectInputStream() throws IOException {
		return new ObjectInputStream(CLIENT_SOCKET.getInputStream());
	}

	private MessageDefault readObjRequestFromClient() throws IOException, ClassNotFoundException {
		return (MessageDefault) STREAM_IN_FROM_CLIENT.readObject();
	}

	private boolean inputNotEqualToExit(String input) {
		return !IGUIRequest.EXIT.equalsIgnoreCase(input);
	}

	public boolean inputEqualsHello(String input) {
		// return input.equals(IGUIRequest.HELLO);
		return IGUIRequest.HELLO.equalsIgnoreCase(input);
	}

	private void closeConnection() {
		LOGGER.info("++closeConnection(): " + EDebugMessage.CONNECTION_CLOSING);
		try {
			flushOutputStream();
			closeBufferedReaderRequestStream();
			closePrinterWriterResponseStream();
			closeSocketStreamConnection();
			LOGGER.info("--closeConnection(): " + EDebugMessage.CONNECTION_CLOSING);
		} catch (IOException e) {
			LOGGER.info("--closeConnection(): " + EDebugMessage.UNABLE_TO_DISCONNECT);
			System.exit(1);
		}
	} // End of close connection method...

	public void flushOutputStream() throws IOException {
		STREAM_OUT_TO_CLIENT.flush();
	}

	private void closeBufferedReaderRequestStream() throws IOException {
		if (STREAM_IN_FROM_CLIENT != null)
			STREAM_IN_FROM_CLIENT.close();
	}

	private void closePrinterWriterResponseStream() throws IOException {
		if (STREAM_OUT_TO_CLIENT != null)
			STREAM_OUT_TO_CLIENT.close();
	}

	private void closeSocketStreamConnection() throws IOException {
		if (CLIENT_SOCKET != null)
			CLIENT_SOCKET.close();
	}
}
