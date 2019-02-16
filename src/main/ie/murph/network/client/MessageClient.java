package main.ie.murph.network.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import main.ie.murph.network.domain.Login;
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
	private Login login = new Login();

	public static void main(String[] args) throws ClassNotFoundException {
		new MessageClient();
	}

	private MessageClient() throws ClassNotFoundException {
		LOGGER.info("++MessageClient()");
		userLoginPage("\nLETS ROCK.!");
		communicateWithServer();
	} // End of my Run method

	private void communicateWithServer() throws ClassNotFoundException {
		LOGGER.info("++communicateWithServer()");
		try (Socket socket = createConnection();) {
			REQUEST_TO_SERVER = createObjectOutputStream(socket);
			RESPONSE_FROM_SERVER = createObjectInputStream(socket);

			LOGGER.info("++communicateWithServer(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
			sendMessages();
			LOGGER.info(IGUIRequest.GOODBYE);
		} catch (IOException e) {
			logExceptionMessage(e);
		} finally {
			closeConnection();
		}

		LOGGER.info("--communicateWithServer(): " + EDebugMessage.ENDING_CHAT);

	}

	private void sendMessages() throws IOException, ClassNotFoundException {
		String messageScannerInput = null;
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
	
	public void userLoginPage(String userRegisteredSuccessMessage)
	{
		LOGGER.info("++userLoginPage(): \n" + userRegisteredSuccessMessage + "\n" + IGUIRequest.LOGIN_MENU);
		switch (SCANNER.nextInt())
	    {
	      case 1: this.login();
	      case 2: this.registerNewUser();
	      case 3: // Forgot Password method
	      case 4: // Forgot User name method
	      case 5: // Send Messages..
	               break;
	      default:	System.out.println("What day is it?");;
	    }
	}
	
	private void login()
	{
		LOGGER.info("++login(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
		String username = this.SCANNER.next();
		if(login.isUserExist(username))
		{
			validateUserLogin(username);
		}
		else
		{
			LOGGER.info("++login(); Register new user account");
			this.registerNewUser();
		}
	}
	
	private void validateUserLogin(String username) {
		LOGGER.info("++validateUserLogin(): " + IGUIRequest.REQUEST_PASSWORD_LOGIN);
		String userPassword = this.SCANNER.next();
		if(loginResultSuccessful(username, userPassword))
		{
			// Redirect to home/Email/Message page
			LOGGER.info("++validateUserLogin(): Login Successful ");
		}
		else {
			// RETURN_FAILED_LOGIN_MESSAGE
			LOGGER.info("++validateUserLogin(): Login Failure ");
		}
	}

	private boolean loginResultSuccessful(String username, String userPassword) {
		return login.validateCredentialsUserFromDtabase(username, userPassword);
	}

	private void registerNewUser()
	{
		LOGGER.info("++registerNewUser(): " + IGUIRequest.REQUEST_USERNAME_LOGIN);
		String username = this.SCANNER.next();
		LOGGER.info("++registerNewUser(): " + IGUIRequest.REQUEST_PASSWORD_LOGIN);
		String password = this.SCANNER.next();
		login.registerNewUserToDtabase(username, password);
		userLoginPage("Welcome, User Register Successfully");
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
