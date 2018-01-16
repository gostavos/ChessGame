package pieces;

import board.Board;
import game.Color;
import game.Type;

public class Bishop extends Piece {

	private Type type;
	private Color color;
	private Board board;

	
	public Bishop(Color color, Type type) {
		super(color, type);
		this.type = type;
		this.color = color;
		// TODO Auto-generated constructor stub
	}
	
	//Returns true if movement is diagonal
	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		return Math.abs(startY - endY) == Math.abs(startX - endX);
	}

	@Override
	public void setFirstMove(boolean bool) {

	}
	
	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public Type getType() {
		return type;
	}

}
