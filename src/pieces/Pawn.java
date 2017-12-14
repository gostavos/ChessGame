package pieces;

import board.Board;
import board.Tile;
import game.Color;
import game.Player;
import game.Type;

public class Pawn extends Piece{

	private boolean isFirstMove = true;
	private Color color;
	private Board board;
	
	public Pawn(Color color, Type type) {
		super(color, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidPath(Tile startTile, Tile endTile) {
		Tile chessBoard[][] = board.getBoard();
		int yDistance = startTile.getY() - endTile.getY();
		if(yDistance > 1){
			int tempY = startTile.getY();
			tempY++;
			Tile traversedTile = chessBoard[tempY][startTile.getX()];
			return traversedTile.getPiece() == null;
		}else if(yDistance < -1){
			int tempY = startTile.getY();
			tempY--;
			Tile traversedTile = chessBoard[tempY][startTile.getX()];
			return traversedTile.getPiece() == null;
		}else{
			return false;
		}
	}

	@Override
	public boolean isValidDestination(Tile startTile, Tile endTile) {
		int xDistance = startTile.getX() - endTile.getX();
		int yDistance;
		boolean tileIsUnoccupied = (endTile.getPiece() == null);
		
		//Checks which way the pawn is supposed to go (up or down)
		if(color == Color.WHITE){
			yDistance = startTile.getY() - endTile.getY();
		}else{
			yDistance = endTile.getY() - startTile.getY();
		}
		
		if(yDistance > 1 && !isFirstMove || yDistance < 1){ //Checks if distance is too great/small
			return false;
		}else if(yDistance == 2 && yDistance == 0 && isFirstMove && 
				tileIsUnoccupied && isValidPath(startTile, endTile)){ //Allows two steps if it is pawns first move
			isFirstMove = false;
			return true;
		}else if(yDistance == 1 && yDistance == 0 && tileIsUnoccupied){ //Normal move
			isFirstMove = false;
			return true;
		}else if(xDistance == 1 &&  yDistance == 1 && !tileIsUnoccupied  ||
				xDistance == -1 && yDistance == 1 && !tileIsUnoccupied){ //Attack move left / right
			isFirstMove = false;
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isEmptyTile(Tile endTile){
		Piece piece = endTile.getPiece();
		if(piece == null)
			return true;
		return false;
	}

	@Override
	public boolean isValidMove(Tile startTile, Tile endTile) {	
		return isValidDestination(startTile, endTile);
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}
