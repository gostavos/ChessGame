package board;

import pieces.Piece;
import GUI.CustomJButton;

public class Tile {

	private	int x;
	private int y;
	private boolean isOccupied;
	private Piece piece;
	private CustomJButton tileButton;
	
	public Tile(int y, int x){
		this.x = x;
		this.y = y;
		isOccupied = false;
	}
	
	public Tile(int y, int x, Piece piece){
		this.x = x;
		this.y = y;
		this.piece = piece;
		isOccupied = true;
	}
	
	public void setOccupied(boolean bool){
		isOccupied = bool;
	}
	
	public void setTileButton(CustomJButton tileButton){
		this.tileButton = tileButton;
	}
	
	public CustomJButton getTileButton(){
		return tileButton;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public boolean isOccupied(){
		return isOccupied;
	}
	
	public void setPiece(Piece piece){
		this.piece = piece;
	}
	
	public Piece getPiece(){
		return piece;
	}
	
	
}
