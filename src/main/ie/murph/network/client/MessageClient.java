package main.ie.murph.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.constants.text.EDebugMessage;
import main.ie.murph.network.constants.text.IGUIRequest;
import main.ie.murph.network.constants.text.INetwork;
import main.ie.murph.network.external.api.streams.ObjectStream;
import main.ie.murph.network.gui.ClientCommunication;

public class MessageClient {
	private static final Logger LOGGER = LogManager.getLogger(MessageClient.class.getName());
	private ObjectStream REQUEST_TO_SERVER;
	private ObjectStream RESPONSE_FROM_SERVER;
	private BufferedReader BUFFERED_INPUT_RESPONSE_FROM_SERVER;
	private PrintWriter WRITER_REQUEST_TO_SERVER;
	private final Scanner SCANNER = new Scanner(System.in);
	
	private ClientCommunication clientCommunication;

	public static void main(String[] args) throws ClassNotFoundException {
		new MessageClient(new ClientCommunication());
	}

	private MessageClient(ClientCommunication clientCommunication) throws ClassNotFoundException {
		LOGGER.info("++MessageClient()");
		this.clientCommunication = clientCommunication;
		run();
	} // End of my Run method

	public void run() throws ClassNotFoundException {
		LOGGER.info("++run()");
		LOGGER.info("++run(): communicateWithServer()");
		communicateWithServer();
	}

	private void communicateWithServer() throws ClassNotFoundException {
		LOGGER.info("++communicateWithServer()");
		try (Socket socket = createConnection();) {
			
			REQUEST_TO_SERVER = createObjectOutputStream(socket);
			RESPONSE_FROM_SERVER = createObjectInputStream(socket);
			
			WRITER_REQUEST_TO_SERVER = createWriterRequest(socket);
			BUFFERED_INPUT_RESPONSE_FROM_SERVER = createReaderResponse(socket);

			LOGGER.info("++communicateWithServer(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
			clientCommunication.sendMessages(REQUEST_TO_SERVER, REQUEST_TO_SERVER);
			LOGGER.info(IGUIRequest.GOODBYE);
		} catch (IOException e) {
			logExceptionMessage(e);
		} finally {
			closeConnection();
		}
		LOGGER.info("--communicateWithServer(): " + EDebugMessage.ENDING_CHAT);
	}

	private Socket createConnection() throws UnknownHostException, IOException {
		LOGGER.info("++createConnection()");
		return new Socket(INetwork.SPECIFIED_IP_ADDRESS, INetwork.SPECIFIED_PORT_NUMBER);
	}

	private ObjectStream createObjectOutputStream(Socket socket) throws IOException {
		LOGGER.info("++createObjectOutputStream()");
		return new ObjectStream(socket.getOutputStream());
	}

	private ObjectStream createObjectInputStream(Socket socket) throws IOException {
		LOGGER.info("++createObjectInputStream()");
		return new ObjectStream(socket.getInputStream());
	}
	
	private PrintWriter createWriterRequest(Socket socket) throws IOException {
		LOGGER.info("++createWriterRequest()");
		return new PrintWriter(socket.getOutputStream(), true);
	}
	
	private BufferedReader createReaderResponse(Socket socket) throws IOException {
		LOGGER.info("++createReaderResponse()");
		return new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void userLoginInstructions() throws IOException, ClassNotFoundException {
		String messageRequest, messageResponse = null;
		do {
			clientCommunication.userLoginInstruction("++navigateInstructions() - Let's begin from Message Client.!.");
			
			// To server
			messageRequest = SCANNER.nextLine();
			WRITER_REQUEST_TO_SERVER.write(messageRequest);

			if (!IGUIRequest.EXIT.equalsIgnoreCase(messageRequest)) {
				messageResponse = BUFFERED_INPUT_RESPONSE_FROM_SERVER.readLine();
				
				// From server
				LOGGER.info("++communicateWithServer(): " + IGUIRequest.SERVER_RESPONSE + messageResponse.toString());
			}
		} while (!IGUIRequest.EXIT.equalsIgnoreCase(messageRequest));
	}

	private void logExceptionMessage(IOException e) {
		LOGGER.info("--communicateWithServer(): " + EDebugMessage.CONNECTION_LOST);
		LOGGER.error("--communicateWithServer(): " + EDebugMessage.SERVER_ERROR + e.getMessage());
		LOGGER.error("--communicateWithServer(): " + EDebugMessage.LOCALIZED_ERROR + e.getLocalizedMessage());
		LOGGER.error("--communicateWithServer(): " + EDebugMessage.STACK_TRACE + " " + e.getStackTrace());
		LOGGER.error("--communicateWithServer(): " + EDebugMessage.EXCEPTION_STRING + e.toString());
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
		LOGGER.info("++flushDataBeforeClosingObjectStream()");
		REQUEST_TO_SERVER.flushOutputStream();
	}

	private void closeBufferedReaderRequestStream() throws IOException {
		LOGGER.info("++closeBufferedReaderRequestStream()");
		if (RESPONSE_FROM_SERVER != null)
			RESPONSE_FROM_SERVER.closeInputStream();
	}

	private void closePrinterWriterResponseStream() throws IOException {
		LOGGER.info("++closePrinterWriterResponseStream()");
		if (REQUEST_TO_SERVER != null)
			REQUEST_TO_SERVER.closeOutputStream();
	}

}
