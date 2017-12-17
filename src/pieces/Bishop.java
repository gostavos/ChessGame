package pieces;

import board.Board;
import game.Color;
import game.Type;

public class Bishop extends Piece {

	private Type type;
	private Color color;
	private Board board;
	
	public Bishop(Color color, Type type, Board board) {
		super(color, type, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

}
