package board;

import pieces.Piece;

public class Tile {

	private	int x;
	private int y;
	private boolean isOccupied;
	private Piece piece;
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
		isOccupied = false;
	}
	
	public Tile(int x, int y, Piece piece){
		this.x = x;
		this.y = y;
		this.piece = piece;
		isOccupied = true;
	}
	
	public void setOccupied(boolean bool){
		isOccupied = bool;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	
}
