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
	private Board board;

	public Piece(Color color, Type type, Board board) {
		this.color = color;
		this.type = type;
		this.board = board;
	}

	public abstract boolean isValidPath(Tile startTile, Tile endTile);
	
	public abstract boolean isValidDestination(Tile startTile, Tile endTile);
	
	public abstract boolean isValidMove(Tile startTile, Tile endTile);
	
	public abstract Color getColor();
	
	public abstract Type getType();
}
