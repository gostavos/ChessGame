package pieces;

import board.Board;
import board.Tile;
import game.Color;
import game.Player;
import game.Type;

public abstract class Piece {
	

	public Player player;
	private Color color;
	private Type type;

	public Piece(Color color, Type type) {
		this.color = color;
		this.type = type;
	}

	public abstract boolean isValidMovementForPiece(int startY, int startX, int endY, int endX);
	
	public abstract void setFirstMove(boolean bool);
	
	public abstract Color getColor();
	
	public abstract Type getType();
}
