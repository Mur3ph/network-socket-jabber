package main.ie.murph.network.distributed.hashtable;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import main.ie.murph.network.distributed.hashtable.domain.History;
import main.ie.murph.network.distributed.hashtable.domain.Point;
import main.ie.murph.network.distributed.hashtable.domain.User;

public class RunProtocol {

	private static final int SET_SIZE = 9;
	private static Set<Point> points;
//	For Map below String will be date and History will be Users history :)
	private static Hashtable<User, Map<String, History>> table;
	
	public RunProtocol(){
		points = new HashSet<>(SET_SIZE);
		table = new Hashtable<User, Map<String, History>>();
	}

	public static void main(String[] args){
//		learningResources();
		
	}
	
	public static void learningResources(){
		System.out.println(Resources.CHORD_PEER_TO_PEER);
		System.out.println(Resources.CHORD_PEER_TO_PEER_IMAGE);
		System.out.println();
	}
	
	public Hashtable<User, Map<String, History>> getHashTable(){
		return table;
	}
	
	public Set<Point> getPoints(){
		return points;
	}
	
}
