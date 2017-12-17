package pieces;

import board.Tile;
import game.Color;
import game.Player;
import game.Type;
import board.Board;

public class Rook extends Piece{

	private Color color;
	private Board board;
	
	public Rook(Color color, Type type, Board board) {
		super(color, type, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidPath(Tile startTile, Tile endTile) {
		int endY = endTile.getY();
		int endX = endTile.getX();
		int startY = startTile.getY();
		int startX = startTile.getX();
		Tile[][] chessBoard = board.getBoard();
		if(startY == endY && startX != endX){
			return moveLeftRight(startX, endX, startY, chessBoard);
		}else if(startX == endX && startY != endY){
			return moveUpDown(startY, endY, startX, chessBoard);
		}
		return false;
	}
	
	public boolean moveUpDown(int startY, int endY, int startX, Tile[][] chessBoard){
		if(startY > endY){ //Going up
			for(int i = endY; i < startY; i++){
				Tile traversedTile = chessBoard[i][startX];
				if(traversedTile.getPiece() != null)
					return false;
			}
		}else if(startY < endY){ //Going down
			for(int i = startY; i < endY; i++){
				Tile traversedTile = chessBoard[i][startX];
				if(traversedTile.getPiece() != null)
					return false;
			}
		}
		return true;
	}
	
	public boolean moveLeftRight(int startX, int endX, int startY, Tile[][] chessBoard){
		if(startX > endX){
			for(int i = endX; i < startX; i++){ //Right to left
				Tile traversedTile = chessBoard[startY][i];
				if(traversedTile.getPiece() != null)
					return false;
			}
		}else if(startX < endX){ //Left to right
			for(int i = startX; i < endX; i++){
				Tile traversedTile = chessBoard[startY][i];
				if(traversedTile.getPiece() != null)
					return false;
			}
		}
		return true;
	}

	@Override
	public boolean isValidDestination(Tile startTile, Tile endTile) {
		int endY = endTile.getY();
		int endX = endTile.getX();
		int startY = startTile.getY();
		int startX = startTile.getX();
		if(endTile.getPiece() == null || !isAlly(endTile)){ //Checks if endTile is empty or occupied by enemy
			if(startY == endY && startX != endX ||
				startX == endX && startY != endY){
				//Checks that movement is either straight up/down or left/right
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isValidMove(Tile startTile, Tile endTile) {
		boolean validDestination = isValidDestination(startTile, endTile);
		boolean validPath = isValidPath(startTile, endTile);
		return validDestination && validPath;
	}

	public boolean isAlly(Tile otherTile){
		if(otherTile.getPiece() != null){
			if(color == Color.WHITE && otherTile.getPiece().getColor() == Color.WHITE ||
					color == Color.BLACK && otherTile.getPiece().getColor() == Color.BLACK){
				return true;
			}
		}
		return false;
	}
	
	public Color getColor(){
		return color;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
