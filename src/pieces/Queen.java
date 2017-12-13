package pieces;

import game.Color;
import game.Type;

public class Queen extends Piece {
	
	private Color color;
	
	public Queen(Color color, Type type) {
		super(color, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isValidPath(int finalX, int finalY) {
		// TODO Auto-generated method stub
		return false;
	}

}
