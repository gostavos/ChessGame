package pieces;

import board.Board;
import game.Color;
import game.Type;

public class King extends Piece {

	private Color color;
	private Type type;

	public King(Color color, Type type) {
		super(color, type);
		this.color = color;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		int xDif = Math.abs(startX - endX);
		int yDif = Math.abs(startY - endY);
		return xDif < 2 && xDif >= 0 && yDif < 2 && yDif >= 0;
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
