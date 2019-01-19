package main.ie.murph.network.file.reader;

import java.io.FileInputStream;
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
	}
	
	private void run() throws NoSuchAlgorithmException, IOException{
		FileInputStream readInFile = new FileInputStream(FILENAME);
		MessageDigest secureHashAlgorithm = MessageDigest.getInstance("SHA-256");
		DigestInputStream digestInputStream = new DigestInputStream(readInFile, secureHashAlgorithm);
		
		while(digestInputStream.read() != -1); //Read entire file..
		
		digestInputStream.close();
		
		byte[] digest = secureHashAlgorithm.digest();
//		LOGGER.info(input + ": ");
		LOGGER.info(digest);
		LOGGER.info("Testing baby.. ");
	}

}
