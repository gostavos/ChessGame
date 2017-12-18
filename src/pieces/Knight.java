package pieces;

import game.Color;
import game.Type;

public class Knight extends Piece{
	
	private Color color;
	private Type type;

	public Knight(Color color, Type type) {
		super(color, type);
		this.color = color;
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	//Returns true if movement is 2 on first axis and 1 on second axis
	@Override
	public boolean isValidMovementForPiece(int startY, int startX, int endY, int endX) {
		int xDif = Math.abs(startX - endX);
		int yDif = Math.abs(startY - endY);	
		return xDif == 2 && yDif == 1 || yDif == 2 && xDif == 1;
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
