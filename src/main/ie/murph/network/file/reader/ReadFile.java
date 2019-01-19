package main.ie.murph.network.file.reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class ReadFile {
	private static final Logger LOGGER = LogManager.getLogger(ReadFile.class.getName());
	private final static String FILENAME = "src//log4j.properties";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException  {
		System.out.println(System.getProperty("user.dir"));
		ReadFile readFile = new ReadFile();
		readFile.run();
		readFile.runNext();
	}
	
	private void run() throws NoSuchAlgorithmException, IOException{
		FileInputStream readInFile = new FileInputStream(FILENAME);
		MessageDigest secureHashAlgorithm = MessageDigest.getInstance("SHA-256");
		DigestInputStream digestInputStream = new DigestInputStream(readInFile, secureHashAlgorithm);
		
		while(digestInputStream.read() != -1); //Read entire file..
		
		LOGGER.info("Log 1: " + digestInputStream.read());
		LOGGER.info("Log 1.1: " + digestInputStream.toString());
		
		digestInputStream.close();
		
		byte[] digest = secureHashAlgorithm.digest();
//		LOGGER.info(input + ": ");
		LOGGER.info("Log 2: " + digest);
	}
	
	private void runNext() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		MessageDigest messageDigestAlgorithm = MessageDigest.getInstance("MD5");
		try (FileInputStream readInFile = new FileInputStream(FILENAME);
		     DigestInputStream digestInputStream = new DigestInputStream(readInFile, messageDigestAlgorithm)) 
		{
		  /* Read decorated stream (dis) to EOF as normal... */
			while(digestInputStream.read() != -1); //Read entire file..
			LOGGER.info("Log 1: " + digestInputStream.read());
			LOGGER.info("Log 1.1: " + digestInputStream.toString());
		}
		byte[] digest = messageDigestAlgorithm.digest();
		LOGGER.info("Log 2: " + digest);
	}

}
