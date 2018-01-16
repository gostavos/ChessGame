package pieces;

import board.Board;
import board.Tile;
import game.Color;
import game.Player;
import game.Type;

public class Pawn extends Piece{

	private boolean isFirstMove;
	private Color color;
	private Type type;
	
	public Pawn(Color color, Type type) {
		super(color, type);
		this.color = color;
		this.type = type;
		this.isFirstMove = true;
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Returns true if movement is 2 steps up/down (depending on color) and it is pawn's first move.
	 * Also true if movement is 1 step up/down (depending on color) and sideways movement is 0, or if 
	 * sideways movement is 1 in either direction (for attack)
	 */
	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		//because the pawn's special rules regarding when it can attack 
		//this method is more useful in the Chess class
		return true;
	}

	public void setFirstMove(boolean bool) {
		isFirstMove = bool;
	}
	
	public boolean isFirstMove() {
		return isFirstMove;
	}
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Type getType() {
		return type;
	}



//	@Override
//	public boolean isValidPath(Tile startTile, Tile endTile) {
//		Tile chessBoard[][] = board.getBoard();
//		int yDistance = startTile.getY() - endTile.getY();
//		if(yDistance > 1){
//			int tempY = startTile.getY();
//			tempY++;
//			Tile traversedTile = chessBoard[tempY][startTile.getX()];
//			return traversedTile.getPiece() == null;
//		}else if(yDistance < -1){
//			int tempY = startTile.getY();
//			tempY--;
//			Tile traversedTile = chessBoard[tempY][startTile.getX()];
//			return traversedTile.getPiece() == null;
//		}else{
//			return false;
//		}
//	}
//
//	@Override
//	public boolean isValidDestination(Tile startTile, Tile endTile) {
//		int xDistance = startTile.getX() - endTile.getX();
//		int yDistance;
//		boolean tileIsUnoccupied = (endTile.getPiece() == null);
//		
//		//Checks which way the pawn is supposed to go (up or down)
//		if(color == Color.WHITE){
//			yDistance = startTile.getY() - endTile.getY();
//		}else{
//			yDistance = endTile.getY() - startTile.getY();
//		}
//		
//		if(yDistance > 1 && !isFirstMove || yDistance < 1){ //Checks if distance is too great/small
//			return false;
//		}else if(yDistance == 2 && yDistance == 0 && isFirstMove && 
//				tileIsUnoccupied && isValidPath(startTile, endTile)){ //Allows two steps if it is pawns first move
//			isFirstMove = false;
//			return true;
//		}else if(yDistance == 1 && yDistance == 0 && tileIsUnoccupied){ //Normal move
//			isFirstMove = false;
//			return true;
//		}else if(xDistance == 1 &&  yDistance == 1 && !tileIsUnoccupied  ||
//				xDistance == -1 && yDistance == 1 && !tileIsUnoccupied){ //Attack move left / right
//			isFirstMove = false;
//			return true;
//		}else{
//			return false;
//		}
//	}

}
