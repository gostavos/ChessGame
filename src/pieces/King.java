package pieces;

import board.Board;
import game.Color;
import game.Type;

public class King extends Piece {

	private Color color;

	public King(Color color, Type type, Board board) {
		super(color, type, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

}
