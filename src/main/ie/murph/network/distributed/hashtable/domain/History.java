package main.ie.murph.network.distributed.hashtable.domain;

import java.util.HashMap;
import java.util.Map;

public class History {
	private int gamesWon;
	private int gamesLost;
	private Map<String, Integer> games;
	
	public History(){
		games = new HashMap<String, Integer>();
	}
	
}
