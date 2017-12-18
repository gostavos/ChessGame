package board;

import pieces.Piece;

public class Board {

	private Tile tile;
	private Tile[][] gameBoard;
	
	public Board(){
		gameBoard = new Tile[8][8];
	}
	
	public Tile[][] getBoard(){
		return gameBoard;
	}
	
	public Tile[][] moveTo(Tile startTile, Tile endTile){
		int endX = endTile.getX();
		int endY = endTile.getY();
		int startX = startTile.getX();
		int startY = startTile.getY();
		if(!isOutOfBounds(endX, endY)){
			//Moves piece from start tile to end tile, clears piece from start tile
			Piece piece = startTile.getPiece();
			endTile = new Tile(endX, endY, piece);
			startTile = new Tile(startTile.getX(), startTile.getY());
			gameBoard[startX][startY] = startTile;
			gameBoard[endX][endY] = endTile;
		}
		return gameBoard;
	}
	
	public boolean isOutOfBounds(int x, int y){
		return(x < 0 || y < 0 || x > 7 || y > 7);	
	}
}
