package main.ie.murph.network.distributed.hashtable;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import main.ie.murph.network.distributed.hashtable.domain.History;
import main.ie.murph.network.distributed.hashtable.domain.Point;
import main.ie.murph.network.distributed.hashtable.domain.User;

public class RunProtocol {

	private static final int SET_SIZE = 9;
	private static Set<Point> points;
//	History might have to change to list as Set won't allow duplication 
	private static Hashtable<User, Set<History>> table;
	
	public RunProtocol(){
		points = new HashSet<>(SET_SIZE);
		table = new Hashtable<User, Set<History>>();
	}

	public static void main(String[] args){
//		learningREsources();
		
	}
	
	public static void learningREsources(){
		System.out.println(Resources.CHORD_PEER_TO_PEER);
		System.out.println(Resources.CHORD_PEER_TO_PEER_IMAGE);
		System.out.println();
	}
	
}
