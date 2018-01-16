package board;

import pieces.Piece;

import java.awt.Color;

import javax.swing.BorderFactory;

import GUI.CustomJButton;

public class Tile {

	private	int x;
	private int y;
	private boolean isOccupied;
	private boolean isSelected = false;
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
	
	public void setBackground(Color color) {
//		tileButton.setBorder(BorderFactory.createLineBorder(color));
		tileButton.setBackground(color);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Tile clone() {
		int cloneX = this.x;
		int cloneY = this.y;
		Piece clonePiece = this.piece;
		return new Tile(cloneY, cloneX, clonePiece);
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
