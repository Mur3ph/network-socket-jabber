package main.ie.murph.network.distributed.hashtable;

import java.util.Set;
import java.util.UUID;

public class RunProtocol {

	private static final int SET_SIZE = 10;

	public static void main(String[] args){
		System.out.println(Resources.CHORD_PEER_TO_PEER);
		System.out.println(Resources.CHORD_PEER_TO_PEER_IMAGE);
		System.out.println();
	}
	
	public static void run()
	{
		Set<String> javaSet = new java.util.HashSet<>(SET_SIZE);
        for (int i = 0; i < SET_SIZE; i++) {
            String randomHexString = UUID.randomUUID().toString().replaceAll("-", "");
            javaSet.add(randomHexString);
        }
         
	}
	
}
