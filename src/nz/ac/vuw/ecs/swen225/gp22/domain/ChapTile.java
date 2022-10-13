package nz.ac.vuw.ecs.swen225.gp22.domain;

import java.util.*;


public class ChapTile extends CharacterTile{
	
	private ArrayList<KeyTile> keys;
	private boolean alive;
	private String file = "chap";
	
	//private Stack<Direction> previousMoves = new Stack<Direction>();
	//private Stack<Direction> nextMoves = new Stack<Direction>();

	public ChapTile() {
		super("chap");
		keys = new ArrayList<KeyTile>();
		alive = true;
	}
	
	public void addKey(KeyTile k) {
		keys.add(k);
	}
	
	public void removeKey(KeyTile k) {
		keys.remove(k);
	}
	
	public void removeKey(String k) {
		KeyTile remove = null;
		for (KeyTile key : keys) {
	    	if (key.getColor().equals(k)) remove = key;
	    }
		keys.remove(remove);
	}
	
	public boolean hasKey(KeyTile k) {
	    for (KeyTile key : keys) {
	    	if (key.getColor().equals(k.getColor())) return true;
	    }
	    return false;
	}
	
	public ArrayList<KeyTile> getKeys() {
		return keys;
	}
	
	public void addPreviousMove(Direction d) {
		previousMoves.push(d);
	}
	
	public void addNextMove(Direction d) {
		nextMoves.push(d);
	}
	
	public Stack<Direction> getPreviousMoves() {
		return previousMoves;
	}
	
	public Stack<Direction> getNextMoves() {
		return nextMoves;
	}
	
	public void getFLeft() {
		file = "chap_left";
	}
	
	public void getFRight() {
		file = "chap_right";
	}
	
	public void getFUp() {
		file = "chap_up";
	}
	
	public void getFDown() {
		file = "chap_down";
	}

	public void getFIdle() {
		file = "chap";
	}

	
	
	@Override
	public void draw() {
		
		
	}
	
	@Override
	public String toString() {
		return "Chap";
	}

	@Override
	public String getFileName() {
		// TODO Auto-generated method stub
		return file;
	}
	

}
