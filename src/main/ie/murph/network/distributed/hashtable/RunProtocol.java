package main.ie.murph.network.distributed.hashtable;

import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import main.ie.murph.network.distributed.hashtable.domain.Point;
import main.ie.murph.network.distributed.hashtable.domain.User;

public class RunProtocol {

	private static final int SET_SIZE = 10;
	private static Hashtable<User, Point> table;
	
	public RunProtocol(){
		table = new Hashtable<User, Point>();
	}

	public static void main(String[] args){
		learningREsources();
		
	}
	private static void learningREsources(){
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
