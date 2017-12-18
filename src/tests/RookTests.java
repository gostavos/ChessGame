package tests;

import static org.junit.Assert.*;
import game.*;
import pieces.*;

import org.junit.Test;

public class RookTests {
	
	@Test
	public void horizontalMovement() throws Exception {
		Piece rook = new Rook(Color.WHITE, Type.ROOK);
		boolean validMove = rook.isValidMovementForPiece(2, 1, 2, 5);
		assertEquals(validMove, true);
	}

	@Test
	public void verticalMovement() throws Exception{
		Piece rook = new Rook(Color.BLACK, Type.ROOK);
		boolean validMove = rook.isValidMovementForPiece(1, 5, 6, 5);
		assertEquals(validMove, true);
	}
	
	@Test
	public void diagonalMovement() throws Exception{
		Piece rook = new Rook(Color.WHITE, Type.ROOK);
		boolean validMove = rook.isValidMovementForPiece(1, 1, 3, 3);
		assertEquals(validMove, false);
	}

}
