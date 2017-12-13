package pieces;

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

	public abstract boolean isValidPath(Tile startTile, Tile endTile);
	
	public abstract boolean isValidDestination(Tile startTile, Tile endTile);
	
	public abstract boolean isValidMove(Tile startTile, Tile endTile);
	
	public abstract Color getColor();
}
