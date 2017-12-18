package pieces;

import game.Color;
import game.Type;

public class Queen extends Piece {
	
	private Color color;
	private Type type;
	
	public Queen(Color color, Type type) {
		super(color, type);
		this.color = color;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	//Returns true if movements is vertical, horizontal or diagonal
	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		return (startY == endY && startX != endX ||
				startX == endX && startY != endY ||
				Math.abs(startY - endY) == Math.abs(startX - endX));
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
