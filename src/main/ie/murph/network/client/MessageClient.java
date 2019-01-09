package main.ie.murph.network.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.domain.message.MessageDefault;
import main.ie.murph.network.external.api.streams.ObjectStream;
import main.ie.murph.network.gui.EDebugMessage;
import main.ie.murph.network.gui.IGUIRequest;
import main.ie.murph.network.gui.INetwork;

public class MessageClient {
	private static final Logger LOGGER = LogManager.getLogger(MessageClient.class.getName());
	private ObjectStream REQUEST_TO_SERVER;
	private ObjectStream RESPONSE_FROM_SERVER;
	private final Scanner SCANNER = new Scanner(System.in);
	private MessageDefault messageREQUEST, messageResponse;

	public static void main(String[] args) throws ClassNotFoundException {
		new MessageClient();
	}

	private MessageClient() throws ClassNotFoundException {
		communicateWithServer();
	} // End of my Run method

	private void communicateWithServer() throws ClassNotFoundException {

		try (Socket socket = createConnection();) {
			REQUEST_TO_SERVER = createObjectOutputStream(socket);
			RESPONSE_FROM_SERVER = createObjectInputStream(socket);

			String messageScannerInput = null;
			LOGGER.info("++MessageClient(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);

			do {
				// To server
				messageScannerInput = SCANNER.nextLine();
				messageREQUEST = new MessageDefault(IGUIRequest.GREETINGS, messageScannerInput);
				REQUEST_TO_SERVER.sendObjectRequest(messageREQUEST);

				// if (!messageREQUEST.getMessageBody().equalsIgnoreCase(IGUIRequest.EXIT))
				if (!IGUIRequest.EXIT.equalsIgnoreCase(messageREQUEST.getMessageBody())) {
					messageResponse = RESPONSE_FROM_SERVER.receiveObjectResponse();

					// From server
					LOGGER.info("++communicateWithServer(): " + IGUIRequest.SERVER_RESPONSE + messageResponse.toString());
				}
			} while (!IGUIRequest.EXIT.equalsIgnoreCase(messageREQUEST.getMessageBody()));

			LOGGER.info(IGUIRequest.GOODBYE);
		} catch (IOException e) {
			LOGGER.info("--communicateWithServer(): " + EDebugMessage.CONNECTION_LOST);
			LOGGER.error("--communicateWithServer(): " + EDebugMessage.SERVER_ERROR + e.getMessage());
			LOGGER.error("--communicateWithServer(): " + EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			LOGGER.error("--communicateWithServer(): " + EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
			LOGGER.error("--communicateWithServer(): " + EDebugMessage.EXCEPTION_STRING + e.toString());
		} finally {
			closeConnection();
		}

		LOGGER.info("--communicateWithServer(): " + EDebugMessage.ENDING_CHAT);

	}

	private Socket createConnection() throws UnknownHostException, IOException {
		return new Socket(INetwork.SPECIFIED_IP_ADDRESS, INetwork.SPECIFIED_PORT_NUMBER);
	}

	private ObjectStream createObjectOutputStream(Socket socket) throws IOException {
		return new ObjectStream(socket.getOutputStream());
	}

	private ObjectStream createObjectInputStream(Socket socket) throws IOException {
		return new ObjectStream(socket.getInputStream());
	}

	private void closeConnection() {
		LOGGER.info("++closeConnection(): " + EDebugMessage.CONNECTION_CLOSING);
		try {
			flushDataBeforeClosingObjectStream();
			closeBufferedReaderRequestStream();
			closePrinterWriterResponseStream();
		} catch (IOException e) {
			LOGGER.info("--closeConnection(): " + EDebugMessage.CANNOT_DISCONNECT);
			LOGGER.error("--closeConnection(): " + EDebugMessage.SERVER_ERROR + e.getMessage());
			LOGGER.error("--closeConnection(): " + EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
			LOGGER.error("--closeConnection(): " + EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
			LOGGER.error("--closeConnection(): " + EDebugMessage.EXCEPTION_STRING + e.toString());
			System.exit(1);
		}
	} // End of close connection method...

	private void flushDataBeforeClosingObjectStream() throws IOException {
		REQUEST_TO_SERVER.flushOutputStream();
	}

	private void closeBufferedReaderRequestStream() throws IOException {
		if (RESPONSE_FROM_SERVER != null)
			RESPONSE_FROM_SERVER.closeInputStream();
	}

	private void closePrinterWriterResponseStream() throws IOException {
		if (REQUEST_TO_SERVER != null)
			REQUEST_TO_SERVER.closeOutputStream();
	}

}
