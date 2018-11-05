package main.ie.murph.blockchain.algorithm;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import main.ie.murph.network.domain.Message;

public class StringUtil {
	
	public final static String CODE_RESOURCE = "https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa";
	
	// Applies SHA256 to a string and returns the result. 
	public static String applySHA256CryptoAlgorithm(String inputBlockData){
		try {
			MessageDigest messageDigest = initializeSHA256AlgorithmInstance();
//			System.out.println("messageDigest: " + messageDigest);
	        
			// Applies SHA256 to our input
			byte[] encodedhash = messageDigest.digest(inputBlockData.getBytes("UTF-8"));
//			System.out.println("hash1: " + encodedhash);
	        
			StringBuffer hexString = bytesToHex(encodedhash);
			
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static MessageDigest initializeSHA256AlgorithmInstance() throws NoSuchAlgorithmException {
		return MessageDigest.getInstance("SHA-256");
	}

	private static StringBuffer bytesToHex(byte[] encodedhash) {
		StringBuffer hexString = new StringBuffer(); // This will contain hash as Hex-i-decimal
		for (int i = 0; i < encodedhash.length; i++) {
//			System.out.println("hash2: " + encodedhash);
			String hex = Integer.toHexString(0xff & encodedhash[i]);
			if(hex.length() == 1) hexString.append('0');
//			System.out.println("hex: " + hex);
			hexString.append(hex);
//			System.out.println("hexString: " + hexString);
		}
		return hexString;
	}

	// Short hand helper to turn Object into a JSON string
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	
	public static Message jsonToObj(String jsonInString, Type o) {
		return new Gson().fromJson(jsonInString, o);   
	}
	
//	Create a char array of length 'difficultyLength', populated by default with 0, or '\u0000' or \0 (which are null characters)
//	new String(...) turns that into a string of length difficulty (where each character still has the default value of 0
//	.replace('\0', '0') replaces each of the characters with '0' (the Unicode 0 character) to give you a string of difficulty characters which are all 0.
//	https://stackoverflow.com/questions/49588347/what-is-the-meaning-of-replace-0-0#
	//Returns difficulty string target, to compare to hash. e.g difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficultyLength) {
		return new String(new char[difficultyLength]).replace('\0', '0');
	}
	
	
}
