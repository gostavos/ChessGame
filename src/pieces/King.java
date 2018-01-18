package pieces;

import board.Board;
import game.Color;
import game.Type;

public class King extends Piece {

	private Color color;
	private Type type;
	private boolean isFirstMove;

	public King(Color color, Type type) {
		super(color, type);
		this.color = color;
		this.type = type;
		this.isFirstMove = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		if(isFirstMove && endX == 7 || isFirstMove && endX == 0)
			return true;
		int xDif = Math.abs(startX - endX);
		int yDif = Math.abs(startY - endY);
		return xDif < 2 && xDif >= 0 && yDif < 2 && yDif >= 0;
	}
	
	public void setFirstMove(boolean bool) {
		isFirstMove = bool;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Piece clone() {
		Piece king = new King(this.color, this.type);
		king.setFirstMove(this.isFirstMove);
		return king;
	}
	
	@Override
	public boolean getIsFirstMove() {
		return isFirstMove;
	}
	
	public String toString() {
		return color.toString() + " " + type.toString() + " " + isFirstMove;
	}
}
