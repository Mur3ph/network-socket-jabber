package main.ie.murph.network.utility;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import main.ie.murph.network.communicator.domain.message.MessageDefault;

public class StringUtil {
	private static final Logger LOGGER = Logger.getLogger(StringUtil.class.getSimpleName());
	public final static String CODE_RESOURCE = "https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa";
	
//	private static ObjectMapper mapper = new ObjectMapper();
	
	// Applies SHA256 to a string and returns the result. 
	public static String applySHA256CryptoAlgorithm(String inputBlockData){
		LOGGER.info("++applySHA256CryptoAlgorithm()");
		try {
			MessageDigest messageDigest = initializeSHA256AlgorithmInstance();
			LOGGER.info("++removeDepartment(): messageDigest: "  + messageDigest);
	        
			// Applies SHA256 to our input
			byte[] encodedhash = messageDigest.digest(inputBlockData.getBytes("UTF-8"));
			LOGGER.info("++removeDepartment(): " + encodedhash);
	        
			StringBuffer hexString = bytesToHex(encodedhash);
			
			return hexString.toString();
		}
		catch(Exception e) {
			LOGGER.error("++applySHA256CryptoAlgorithm()" + e.getMessage());
		}
		return "".toString();
	}
	
	private static MessageDigest initializeSHA256AlgorithmInstance() throws NoSuchAlgorithmException {
		LOGGER.info("++initializeSHA256AlgorithmInstance()");
		return MessageDigest.getInstance("SHA-256");
	}

	private static StringBuffer bytesToHex(byte[] encodedhash) {
		LOGGER.info("++bytesToHex()");
		StringBuffer hexString = new StringBuffer(); // This will contain hash as Hex-i-decimal
		for (int i = 0; i < encodedhash.length; i++) {
//			System.out.println("hash2: " + encodedhash);
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if(hex.length() == 1) hexString.append('0');
//			System.out.println("hex: " + hex);
			hexString.append(hex);
//			System.out.println("hexString: " + hexString);
		}
		LOGGER.info("++bytesToHex()");
		return hexString;
	}

	// Short hand helper to turn Object into a JSON string
	public static String getJson(Object o) {
		LOGGER.info("++getJson()");
		return null; //new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	public static MessageDefault jsonToObj(String jsonInString) throws IOException {
		LOGGER.info("++jsonToObj()" + "From Json: " ); //+ mapper.readValue(jsonInString, MessageDefault.class));
		return null; //mapper.readValue(jsonInString, MessageDefault.class);   
	}
	
//	Create a char array of length 'difficultyLength', populated by default with 0, or '\u0000' or \0 (which are null characters)
//	new String(...) turns that into a string of length difficulty (where each character still has the default value of 0
//	.replace('\0', '0') replaces each of the characters with '0' (the Unicode 0 character) to give you a string of difficulty characters which are all 0.
//	https://stackoverflow.com/questions/49588347/what-is-the-meaning-of-replace-0-0#
	//Returns difficulty string target, to compare to hash. e.g difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficultyLength) {
		LOGGER.info("++getDificultyString()");
		return new String(new char[difficultyLength]).replace('\0', '0');
	}
}
