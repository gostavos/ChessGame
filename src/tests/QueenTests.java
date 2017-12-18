package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.Color;
import game.Type;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;


public class QueenTests {

	@Test
	public void horizontalMovement() throws Exception {
		Piece queen = new Queen(Color.WHITE, Type.QUEEN);
		boolean validMove = queen.isValidMovementForPiece(3, 1, 3, 5);
		assertEquals(validMove, true);
	}
	
	@Test
	public void verticalMovement() throws Exception{
		Piece queen = new Queen(Color.BLACK, Type.QUEEN);
		boolean validMove = queen.isValidMovementForPiece(1, 5, 6, 5);
		assertEquals(validMove, true);
	}
	
	@Test
	public void diagonalMovement() throws Exception{
		Piece queen = new Queen(Color.WHITE, Type.QUEEN);
		boolean validMove = queen.isValidMovementForPiece(1, 1, 3, 3);
		assertEquals(validMove, true);
	}
	
	@Test
	public void wrongDiagonalMovement() throws Exception{
		Piece queen = new Queen(Color.WHITE, Type.QUEEN);
		boolean validMove = queen.isValidMovementForPiece(2, 2, 3, 4);
		assertEquals(validMove, false);
	}

}
